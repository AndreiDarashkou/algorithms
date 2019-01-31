package com.study.puzzle.chess;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

//https://www.codewars.com/kata/check-and-mate/
public final class CheckAndMate {

    private static final int[][] KNIGHT_MOVE = new int[][]{{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};
    private static final int[] WHITE_PAWN_MOVE = new int[]{-1, -2};
    private static final int[] BLACK_PAWN_MOVE = new int[]{1, 2};

    private static final String PAWN = "pawn";
    private static final String ROOK = "rook";
    private static final String KNIGHT = "knight";
    private static final String BISHOP = "bishop";
    private static final String QUEEN = "queen";
    private static final String KING = "king";

    public static Set<PieceConfig> isCheck(final PieceConfig[] pieces, int player) {
        return isCheck(Arrays.stream(pieces).collect(toList()), player);
    }

    public static boolean isMate(final PieceConfig[] pieces, int player) {
        Set<PieceConfig> underAttack = isCheck(pieces, player);
        return !underAttack.isEmpty() && !checkMate(pieces, player, underAttack);
    }

    private static Set<PieceConfig> isCheck(List<PieceConfig> list, int player) {
        int[][] board = board(list, player);
        List<PieceConfig> enemyPieces = list.stream().filter(p -> p.getOwner() != player).collect(toList());
        PieceConfig king = getKing(list.stream(), player);

        return enemyPieces.stream().filter(enemy -> isIntersect(king, enemy, board)).collect(Collectors.toSet());
    }

    private static PieceConfig getKing(Stream<PieceConfig> stream, int player) {
        return stream.filter(p -> p.getOwner() == player && p.getPiece().equals(KING))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isIntersect(PieceConfig aim, PieceConfig chessman, int[][] board) {
        int aCol = aim.getX();
        int aRow = aim.getY();

        int col = chessman.getX();
        int row = chessman.getY();

        switch (chessman.getPiece()) {
            case PAWN:
                return abs(aCol - col) == 1 && (row + (chessman.getOwner() == 0 ? -1 : 1)) == aRow;
            case ROOK:
                return intersectRook(aCol, aRow, board, col, row);
            case KNIGHT:
                return (abs(col - aCol) == 2 && abs(row - aRow) == 1) || (abs(col - aCol) == 1 && abs(row - aRow) == 2);
            case BISHOP:
                return intersectBishop(aCol, aRow, board, col, row);
            case QUEEN:
                return intersectRook(aCol, aRow, board, col, row) || intersectBishop(aCol, aRow, board, col, row);
            case KING:
                return false;
            default:
                break;
        }
        return false;
    }

    private static boolean intersectRook(int kCol, int kRow, int[][] board, int col, int row) {
        int[] colArr = Arrays.stream(board).map(line -> line[col]).mapToInt(v -> v).toArray();
        return (col == kCol && checkLine(colArr, row, kRow)) || (row == kRow && checkLine(board[row], col, kCol));
    }

    private static boolean checkLine(int[] line, int start, int end) {
        for (int i = min(start, end) + 1; i < max(start, end); i++) {
            if (line[i] != 0) {
                return false;
            }

        }
        return true;
    }

    private static boolean intersectBishop(int kCol, int kRow, int[][] board, int col, int row) {
        return abs(col - kCol) == abs(row - kRow) && checkDiagonal(col, row, kCol, kRow, board);
    }

    private static boolean checkDiagonal(int col, int row, int kCol, int kRow, int[][] board) {
        List<Integer> rows = interval(row, kRow);
        List<Integer> cols = interval(col, kCol);
        for (int i = 1; i < (rows.size() - 1); i++) {
            if (board[rows.get(i)][cols.get(i)] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkMate(final PieceConfig[] pieces, int player, Set<PieceConfig> attackerSet) {
        boolean canRemoveAttacker = attackerSet.size() == 1;
        PieceConfig attacker = attackerSet.iterator().next();
        PieceConfig king = getKing(Arrays.stream(pieces), player);
        List<PieceConfig> list = Arrays.stream(pieces).collect(toList());
        int[][] board = board(list, player);
        List<Point> crossfireCells = findCrossfireCells(king, attackerSet, board);

        for (PieceConfig piece : pieces) {
            if (piece.getOwner() != player) {
                continue;
            }
            if (KING.equals(piece.getPiece()) && tryMoveKing(piece, list, player)) {
                return true;
            }
            if (canRemoveAttacker) {
                if (isIntersect(attacker, piece, board)) {
                    list.remove(attacker);
                    if (moveAndCheck(player, list, piece, attacker.getX(), attacker.getY())) return true;
                    list.add(attacker);
                } else if (isIntersectPawn(attacker, piece, board)) {
                    list.remove(attacker);
                    if (moveAndCheck(player, list, piece, attacker.getX(), (attacker.getY() + attacker.getPrevY()) / 2))
                        return true;
                    list.add(attacker);
                }
            }
            List<Point> intersectList = findIntersectCells(piece, crossfireCells, board);
            if (!intersectList.isEmpty()) {
                for (Point cell : intersectList) {
                    if (moveAndCheck(player, list, piece, cell.x, cell.y)) return true;
                }
            }
        }
        return false;
    }

    //check En passant
    private static boolean isIntersectPawn(PieceConfig pawn1, PieceConfig pawn2, int[][] board) {
        if (pawn1.getPiece().equals(PAWN) && pawn2.getPiece().equals(PAWN) && pawn1.hasPrevious() && Math.abs(pawn1.getY() - pawn1.getPrevY()) == 2) {
            PieceConfig pawn = new PieceConfig(pawn1.getPiece(), pawn1.getOwner(), pawn1.getX(), (pawn1.getY() + pawn1.getPrevY()) / 2);
            return isIntersect(pawn, pawn2, board);
        }
        return false;
    }

    private static boolean moveAndCheck(int player, List<PieceConfig> list, PieceConfig piece, int x, int y) {
        list.remove(piece);
        PieceConfig newPiece = new PieceConfig(piece.getPiece(), piece.getOwner(), x, y);
        list.add(newPiece);
        if (isCheck(list, player).isEmpty()) {
            return true;
        }
        list.remove(newPiece);
        list.add(piece);
        return false;
    }

    private static List<Point> findIntersectCells(PieceConfig piece, List<Point> crossfireCells, int[][] board) {
        List<Point> availableForPiece = findAvailableMovements(piece, board);
        return availableForPiece.stream().filter(crossfireCells::contains).collect(toList());
    }

    private static List<Point> findAvailableMovements(PieceConfig chessman, int[][] board) {
        int col = chessman.getX();
        int row = chessman.getY();

        switch (chessman.getPiece()) {
            case PAWN:
                int[] moves = chessman.getOwner() == 0 ? WHITE_PAWN_MOVE : BLACK_PAWN_MOVE;
                return Arrays.stream(moves).mapToObj(p -> new Point(col, row + p))
                        .filter(p -> board[p.y][p.x] == 0)
                        .collect(toList());
            case ROOK:
                return concat(IntStream.range(0, 8).mapToObj(i -> new Point(i, row)), IntStream.range(0, 8).mapToObj(i -> new Point(col, i)))
                        .filter(p -> board[p.y][p.x] == 0)
                        .collect(toList());
            case KNIGHT:
                return Arrays.stream(KNIGHT_MOVE).map(coord -> new Point(col + coord[1], row + coord[0]))
                        .filter(p -> p.getX() < 8 && p.getX() >= 0 && p.getY() < 8 && p.getY() >= 0)
                        .collect(toList());
            case BISHOP:
                return concat(concat(IntStream.range(1, 8).mapToObj(i -> new Point(col + i, row + i)), IntStream.range(1, 8).mapToObj(i -> new Point(col - i, row - i))),
                        concat(IntStream.range(1, 8).mapToObj(i -> new Point(col + i, row - i)), IntStream.range(1, 8).mapToObj(i -> new Point(col - i, row + i))))
                        .filter(p -> p.getX() < 8 && p.getX() >= 0 && p.getY() < 8 && p.getY() >= 0)
                        .filter(p -> board[p.y][p.x] == 0)
                        .collect(toList()); //TODO not correct
            case QUEEN:
                //TODO rook + bishop
                break;
        }
        return Collections.emptyList();
    }


    private static List<Point> findCrossfireCells(PieceConfig king, Set<PieceConfig> attackerSet, int[][] board) {
        return attackerSet.stream()
                .map(pc -> findCrossfireCells(king, pc, board))
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private static List<Point> findCrossfireCells(PieceConfig king, PieceConfig attacker, int[][] board) {
        switch (attacker.getPiece()) {
            case BISHOP:
                return getDiagonal(king.getX(), king.getY(), attacker.getX(), attacker.getY());
            case ROOK:
                return getLine(king, attacker);
            case QUEEN:
                return concat(getDiagonal(king.getX(), king.getY(), attacker.getX(), attacker.getY()).stream(),
                        getLine(king, attacker).stream()).collect(toList());
        }
        return Collections.emptyList();
    }

    private static List<Point> getLine(PieceConfig king, PieceConfig attacker) {
        List<Point> result = new ArrayList<>();
        if (king.getX() == attacker.getX()) { //the same column
            for (int i = min(king.getY(), attacker.getY()) + 1; i < max(king.getY(), attacker.getY()); i++) {
                result.add(new Point(i, king.getX()));
            }
        } else { //the same row
            for (int j = min(king.getX(), attacker.getX()) + 1; j < max(king.getX(), attacker.getX()); j++) {
                result.add(new Point(king.getY(), j));
            }
        }
        return result;
    }

    private static List<Point> getDiagonal(int col, int row, int kCol, int kRow) {
        List<Integer> rows = interval(row, kRow);
        List<Integer> cols = interval(col, kCol);
        List<Point> result = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            result.add(new Point(cols.get(i), rows.get(i)));
        }
        return result;
    }

    private static List<Integer> interval(int a, int b) {
        return IntStream.rangeClosed(min(a, b), max(a, b)).boxed()
                .sorted(a < b ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .collect(toList());
    }

    private static boolean tryMoveKing(PieceConfig king, List<PieceConfig> list, int player) {
        list.remove(king);
        int[][] board = board(list, player);

        int col1 = max(king.getX() - 1, 0);
        int col2 = min(king.getX() + 1, 7);
        int row1 = max(king.getY() - 1, 0);
        int row2 = min(king.getY() + 1, 7);

        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                PieceConfig newKing = new PieceConfig(KING, player, j, i);
                list.add(newKing);
                if (board[i][j] == 0) {
                    Set<PieceConfig> underAttack = isCheck(list, player);
                    if (underAttack.isEmpty()) {
                        return true;
                    }
                } else if (board[i][j] == 2 && !isUnderProtection(j, i, list, board)) {
                    PieceConfig piece = get(j, i, list);
                    list.remove(piece);
                    Set<PieceConfig> underAttack = isCheck(list, player);
                    if (underAttack.isEmpty()) {
                        return true;
                    }
                    list.add(piece);
                }
                list.remove(newKing);
            }
        }
        list.add(king);
        return false;
    }

    private static boolean isUnderProtection(int x, int y, List<PieceConfig> list, int[][] board) {
        PieceConfig piece = get(x, y, list);
        List<PieceConfig> allies = list.stream().filter(p -> p.getOwner() == piece.getOwner()).collect(toList());
        allies.remove(piece);
        for (PieceConfig ally : allies) {
            if (isIntersect(piece, ally, board)) {
                return true;
            }
        }
        return false;
    }

    private static PieceConfig get(int x, int y, List<PieceConfig> list) {
        return list.stream().filter(p -> p.getY() == y && p.getX() == x)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private static int[][] board(List<PieceConfig> pieces, int player) {
        int[][] board = new int[8][8];
        for (PieceConfig piece : pieces) {
            board[piece.getY()][piece.getX()] = piece.getOwner() == player ? 1 : 2;
        }
        return board;
    }

}
