package com.study.algorithm.backtracking;

public final class EightQueensProblem {

    private EightQueensProblem() {
    }

    public static boolean[][] findSolution() {
        boolean[][] board = new boolean[8][8];
        checkPosition(board, 0);
        return board;
    }

    private static boolean checkPosition(boolean[][] chessBoard, int queensPositioned) {
        if (isInvalid(chessBoard)) {
            return false;
        }
        if (queensPositioned == 8) {
            return true;
        }
        for (int row = queensPositioned; row < 8; row++) { //starts with next row because in current we already have a queen
            for (int col = 0; col < 8; col++) {
                if (chessBoard[row][col]) {
                    continue;
                }
                chessBoard[row][col] = true;
                if (checkPosition(chessBoard, queensPositioned + 1)) {
                    return true;
                }
                chessBoard[row][col] = false;
            }
        }
        return false;
    }

    public static boolean isInvalid(boolean[][] chessBoard) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (chessBoard[row][col]
                        && (isRowInvalid(chessBoard[row])
                            || isColInvalid(chessBoard, col)
                            || isDiagonalInvalid(chessBoard, row, col))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isColInvalid(boolean[][] board, int col) {
        int queensInCol = 0;
        for (int i = 0; i < 8; i++) {
            if (board[i][col]) {
                queensInCol++;
            }
        }
        return queensInCol > 1;
    }

    private static boolean isRowInvalid(boolean[] row) {
        int queensInRow = 0;
        for (int i = 0; i < 8; i++) {
            if (row[i]) {
                queensInRow++;
            }
        }
        return queensInRow > 1;
    }

    private static boolean isDiagonalInvalid(boolean[][] board, int row, int col) {
        int queensInDiagonal = 0;
        for (int i = 0; i < 8; i++) {
            if ((row + i < 8 && col + i < 8 && board[row + i][col + i])
                    || (row + i < 8 && col - i >= 0 && board[row + i][col - i])
                    || (row - i >= 0 && col + i <= 0 && board[row - i][col + i])
                    || (row - i >= 0 && col - i >= 0 && board[row - i][col - i])) {
                queensInDiagonal++;
            }
        }
        return queensInDiagonal > 1;
    }

}
