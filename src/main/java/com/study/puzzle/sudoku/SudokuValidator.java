package com.study.puzzle.sudoku;

import java.util.Arrays;

final class SudokuValidator {

    private SudokuValidator(){}

    static boolean isCorrect(int[][] puzzle) {
        if (puzzle.length != 9 || puzzle[0].length != 9) {
            return false;
        }
        for (int[] row : puzzle) {
            if (!checkLine(row.clone())) {
                return false;
            }
        }
        for (int row = 0; row < puzzle.length; row++) {
            int column[] = new int[9];
            int index = 0;
            for (int col = 0; col < puzzle[row].length; col++) {
                column[index++] = puzzle[col][row];
            }
            if (!checkLine(column)) {
                return false;
            }
        }
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkSquare(row, col, puzzle)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkSquare(int row, int col, int[][] puzzle) {
        int sqRow = row / 3 * 3;
        int sqCol = col / 3 * 3;
        int square[] = new int[9];
        int index = 0;
        for (int i = sqRow; i < sqRow + 3; i++) {
            for (int j = sqCol; j < sqCol + 3; j++) {
                square[index++] = puzzle[i][j];
            }
        }
        return checkLine(square);
    }


    private static boolean checkLine(int[] clone) {
        if (clone.length != 9) {
            return false;
        }
        Arrays.sort(clone);
        for (int i = 0; i < 8; i++) {
            if (clone[i] == 0) {
                continue;
            }
            if (clone[i] == clone[i + 1] || clone[i] < 0 || clone[i + 1] > 9) {
                return false;
            }
        }
        return true;
    }

}
