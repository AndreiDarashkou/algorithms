package com.study.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SudokuSolver {

    private int[][] grid;
    private int[][] initGrid;
    private int placed = 0;

    public SudokuSolver(int[][] grid) {
        this.grid = grid;
        this.initGrid = new int[9][9];
        initPlacedCount();
        placed--;
    }

    public int[][] solve() {
        System.out.println(isCorrect());
        System.out.println(solve(0, 0, 0));
        return grid;
    }

    private int count = 0;

    private boolean solve(int x, int y, int value) {
        grid[x][y] = value;
        placed++;

        System.out.println(placed + " attempts: " + ++count);
        if (placed == 81) {
            return true;
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                List<Integer> numbers = getAvailableNumbers(row, col);
                for (int num : numbers) {
                    if (solve(row, col, num)) {
                        return true;
                    }
                }
            }
        }
        grid[x][y] = 0;
        placed--;
        return false;
    }

    private List<Integer> getAvailableNumbers(int row, int col) {
        List<Integer> numbers = new ArrayList<>();
        if (grid[row][col] == 0) {
            for (int num = 1; num < 10; num++) {
                if (lineNotContain(grid[row], num) && columnNotContain(col, num) &&
                        squareNotContain(row, col, num)) {
                    numbers.add(num);
                }
            }
        }

        return numbers;
    }

    private boolean squareNotContain(int row, int col, int num) {
        int sqRow = row / 3;
        int sqCol = col / 3;
        for (int i = sqRow * 3; i < sqRow * 3 + 3; i++) {
            for (int j = sqCol * 3; j < sqCol * 3 + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean columnNotContain(int column, int val) {
        int line[] = new int[9];
        int index = 0;
        for (int row = 0; row < 9; row++) {
            line[index++] = grid[row][column];
        }
        return lineNotContain(line, val);
    }

    private boolean lineNotContain(int[] line, int val) {
        for (int v : line) {
            if (v == val) {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrect() {
        for (int row = 0; row < 9; row++) {
            if (!checkLine(grid[row].clone())) {
                return false;
            }
        }
        for (int row = 0; row < 9; row++) {
            int column[] = new int[9];
            int index = 0;
            for (int col = 0; col < 9; col++) {
                column[index++] = grid[col][row];
            }
            if (!checkLine(column)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLine(int[] clone) {
        Arrays.sort(clone);
        for (int i = 0; i < 8; i++) {
            if (clone[i] == 0) {
                continue;
            }
            if (clone[i] == clone[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void initPlacedCount() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] != 0) {
                    placed++;
                    initGrid[row][col] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] puzzle = {
                {1, 5, 2, 4, 8, 9, 3, 7, 6},
                {7, 3, 9, 2, 5, 0, 0, 4, 0},
                {0, 6, 8, 0, 0, 1, 2, 9, 5},
                {0, 0, 7, 1, 2, 0, 6, 0, 0},
                {5, 9, 0, 7, 0, 3, 0, 0, 8},
                {2, 0, 6, 8, 9, 5, 7, 1, 0},
                {9, 1, 4, 6, 0, 0, 0, 0, 2},
                {0, 2, 0, 0, 0, 0, 0, 3, 7},
                {8, 0, 0, 5, 1, 2, 9, 0, 4}};
        int[][] result = new SudokuSolver(puzzle).solve();
        print(result);
    }

    private static void print(int[][] result) {
        for (int i = 0; i < 9; i++) {
            System.out.println();
            for (int j = 0; j < 9; j++) {
                System.out.print(result[i][j] + " ");
            }
        }
    }
}