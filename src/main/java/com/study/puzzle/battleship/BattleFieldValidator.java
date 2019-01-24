package com.study.puzzle.battleship;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.codewars.com/kata/battleship-field-validator/train/java
 */
public class BattleFieldValidator {

    public static boolean fieldValidator(int[][] field) {
        return checkShip(field, 1, 4)
                && checkShip(field, 2, 3)
                && checkShip(field, 3, 2)
                && checkShip(field, 4, 1);
    }

    private static boolean checkShip(int[][] field, int count, int size) {
        int found = 0;
        while (search(field, size, true) || search(field, size, false)) {
            found++;
        }
        return found == count;
    }

    private static boolean search(int[][] field, int shipSize, boolean isHorizontal) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int row = 0; row < 10; row++) {
            int found = 0;
            rows.clear();
            cols.clear();
            for (int col = 0; col < 10; col++) {
                if (isHorizontal ? field[row][col] != 1 : field[col][row] != 1) {
                    found = 0;
                    rows.clear();
                    cols.clear();
                    continue;
                }
                found++;
                rows.add(isHorizontal ? row : col);
                cols.add(isHorizontal ? col : row);
                if (found == shipSize) {
                    return markShip(field, rows, cols);
                }
            }
        }
        return false;
    }

    private static boolean markShip(int[][] field, Set<Integer> rows, Set<Integer> cols) {
        for (int row : rows) {
            for (int col : cols) {
                field[row][col] = 2;
            }
        }
        for (int row : rows) {
            for (int col : cols) {
                int minRow = row > 0 ? row - 1 : row;
                int maxRow = row < 9 ? row + 1 : row;
                int minCol = col > 0 ? col - 1 : col;
                int maxCol = col < 9 ? col + 1 : col;

                for (int rowNum = minRow; rowNum <= maxRow; rowNum++) {
                    for (int colNum = minCol; colNum <= maxCol; colNum++) {
                        if (field[rowNum][colNum] == 1) {
                            return false;
                        }
                        field[rowNum][colNum] = 2;
                    }
                }
            }
        }
        return true;
    }

}
