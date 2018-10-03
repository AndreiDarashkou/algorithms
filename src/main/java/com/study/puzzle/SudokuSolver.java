package com.study.puzzle;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public final class SudokuSolver {

    private int[][] grid;
    private int placed = 0;
    private Map<Point, List<Integer>> available = new LinkedHashMap<>();

    public SudokuSolver(int[][] grid) {
        this.grid = grid;
        updateAvailableMap();
    }

    public int[][] solve() {
        System.out.println(isCorrect());
        System.out.println(solve(0, 0, 0));
        return grid;
    }

    private int count = 0;

    private boolean solve(int x, int y, int value) {
        grid[x][y] = value;

        updateAvailableMap();
        System.out.println(placed + " attempts: " + ++count);
        if (placed == 81) {
            return true;
        }

        Map.Entry<Point, List<Integer>> entry = available.entrySet().iterator().next();
        Point p = entry.getKey();
        List<Integer> numbers = entry.getValue();
        for (int num : numbers) {
            if (solve(p.x, p.y, num)) {
                return true;
            }
        }

        grid[x][y] = 0;
        updateAvailableMap();
        return false;
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
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkSquare(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSquare(int row, int col) {
        int sqRow = row / 3 * 3;
        int sqCol = col / 3 * 3;
        int square[] = new int[9];
        int index = 0;
        for (int i = sqRow; i < sqRow + 3; i++) {
            for (int j = sqCol; j < sqCol + 3; j++) {
                square[index++] = grid[i][j];
            }
        }
        return checkLine(square);
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

    private void updateAvailableMap() {
        placed = 0;
        available.clear();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    available.put(new Point(row, col), getAvailableNumbers(row, col));
                } else {
                    placed++;
                }
            }
        }
        available = available.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(o -> o.getValue().size()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
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

    public static void main(String[] args) {
        int[][] puzzle = {
                {7, 0, 1, 0, 0, 0, 0, 2, 0},
                {0, 0, 6, 0, 0, 0, 0, 0, 4},
                {0, 0, 0, 7, 6, 0, 8, 0, 0},
                {0, 0, 0, 9, 0, 0, 0, 6, 0},
                {1, 9, 0, 0, 0, 0, 0, 3, 0},
                {0, 3, 0, 0, 1, 7, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 9, 0, 3},
                {0, 8, 0, 0, 2, 0, 5, 0, 0},
                {0, 0, 5, 0, 0, 4, 0, 0, 0}};
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
        System.out.println();
    }
}