package com.study.puzzle.sudoku;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toMap;

public final class SudokuSolver {

    private int[][] grid;
    private int[][] result;
    private int placed = 0;
    private int solutions = 0;
    private Map<Point, List<Integer>> available = new LinkedHashMap<>();

    public SudokuSolver(int[][] grid) {
        if (!SudokuValidator.isCorrect(grid)) {
            throw new IllegalArgumentException("Invalid puzzle");
        }
        this.grid = grid;
    }

    public int[][] solve() {
        solve(0, 0, grid[0][0]);
        if (solutions != 1) {
            throw new IllegalArgumentException("Puzzle has: " + solutions + " solutions");
        }
        return result;
    }

    private boolean solve(int x, int y, int number) {
        setNumber(x, y, number);

        if (placed == 81) {
            solutions++;
            copyResult();
            setNumber(x, y, 0);
            return false; //we don't return true to find all solutions
        }
        Map.Entry<Point, List<Integer>> entry = available.entrySet().iterator().next();
        Point p = entry.getKey();
        List<Integer> numbers = entry.getValue();
        for (int num : numbers) {
            if (solve(p.x, p.y, num)) {
                return true;
            }
        }
        setNumber(x, y, 0);
        return false;
    }

    private void setNumber(int x, int y, int number) {
        grid[x][y] = number;
        updateAvailableMap();
    }

    private void copyResult() {
        result = new int[9][9];
        for (int row = 0; row < 9; row++) {
            System.arraycopy(grid[row], 0, result[row], 0, 9);
        }
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
                if (lineNotContain(grid[row], num) && columnNotContain(col, num) && squareNotContain(row, col, num)) {
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
                {4, 0, 5, 0, 1, 0, 7, 0, 8},
                {0, 0, 7, 0, 0, 5, 0, 0, 0},
                {0, 3, 0, 7, 0, 0, 0, 5, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 5},
                {0, 4, 0, 2, 0, 8, 0, 6, 0},
                {5, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 7, 0, 0, 2, 3, 0, 1, 0},
                {0, 0, 0, 4, 0, 0, 2, 0, 0},
                {9, 0, 6, 0, 7, 0, 4, 0, 3}};
        int[][] result = new SudokuSolver(puzzle).solve();
        print(result);
    }

    private static void print(int[][] result) {
        for (int[] row : result) {
            System.out.println();
            for (int num : row) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}