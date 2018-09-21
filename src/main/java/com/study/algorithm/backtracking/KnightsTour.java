package com.study.algorithm.backtracking;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final class KnightsTour {

    private static final int[] deltaX = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] deltaY = new int[]{2, 1, -1, -2, -2, -1, 1, 2};

    private final int[][] board;
    private final Comparator<Point> comparator;

    KnightsTour(int boardSize) {
        board = new int[boardSize][boardSize];
        comparator = new PlaceComparator();
    }

    int[][] findWay() {
        move(board, new Point(0, 0), 0);
        return board;
    }


    private boolean move(int[][] board, Point moveTo, int placed) {
        board[moveTo.x][moveTo.y] = ++placed;

        if (placed == board.length * board[0].length) {
            return true;
        }
        for (Point p : findNextPositions(board, moveTo)) {
            if (move(board, p, placed)) {
                return true;
            }
        }
        board[moveTo.x][moveTo.y] = 0;

        return false;
    }

    private List<Point> findNextPositions(int[][] board, Point currPosition) {
        List<Point> availablePlaces = getPoints(board, currPosition);
        availablePlaces.sort(comparator);
        return availablePlaces;
    }

    private static List<Point> getPoints(int[][] board, Point currPosition) {
        List<Point> availablePlaces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = currPosition.x + deltaX[i];
            int y = currPosition.y + deltaY[i];
            if (x >= 0 && x < board.length && y >= 0 && y < board.length && board[x][y] == 0) {
                availablePlaces.add(new Point(x, y));
            }
        }
        return availablePlaces;
    }

    private class PlaceComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return getPoints(board, o1).size() - getPoints(board, o2).size();
        }
    }
}
