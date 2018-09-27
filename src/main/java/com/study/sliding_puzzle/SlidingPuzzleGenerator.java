package com.study.sliding_puzzle;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class SlidingPuzzleGenerator {

    private final static int MIN_SIZE = 3;
    private final static int MAX_SIZE = 10;

    private SlidingPuzzleGenerator(){
    }

    public static void main(String[] args) {
        SlidingPuzzle puzzle = new SlidingPuzzle(generate(4));
        print(puzzle.getPuzzle());
        List<Integer> way = puzzle.solve();
        System.out.println(way.size());
        System.out.println(Arrays.toString(way.toArray()));
        print(puzzle.getPuzzle());
    }

    @SuppressWarnings("ConstantConditions")
    public static int[][] generate(int size) {
        size = Math.max(MIN_SIZE, Math.min(MAX_SIZE, size));
        int[][] puzzle = new int[size][size];
        LinkedList<Integer> values = IntStream.range(1, size * size).boxed().collect(Collectors.toCollection(LinkedList::new));
        values.add(0);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                puzzle[row][col] = values.poll();
            }
        }
        for (int i = 0; i < 100; i++) {
            Point zero = actualPlace(0, puzzle, size);
            swap(zero, getRandom(zero, size), puzzle);
        }
        return puzzle;
    }

    private static void swap(Point from, Point to, int[][] puzzle) {
        int temp = puzzle[from.x][from.y];
        puzzle[from.x][from.y] = puzzle[to.x][to.y];
        puzzle[to.x][to.y] = temp;
    }

    private static Point getRandom(Point current, int size) {
        int row = current.x;
        int col = current.y;
        List<Point> potential = new ArrayList<>();
        if (row + 1 < size) {
            potential.add(new Point(row + 1, col));
        }
        if (row - 1 >= 0) {
            potential.add(new Point(row - 1, col));
        }
        if (col + 1 < size) {
            potential.add(new Point(row, col + 1));
        }
        if (col - 1 >= 0) {
            potential.add(new Point(row, col - 1));
        }
        Collections.shuffle(potential);
        return potential.get(0);
    }

    private static Point actualPlace(int tile, int[][] puzzle, int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (puzzle[row][col] == tile) {
                    return new Point(row, col);
                }
            }
        }
        throw new IllegalArgumentException("invalid puzzle");
    }

    static void print(int[][] puzzle) {
        StringBuilder builder = new StringBuilder();
        String delimiter = String.join("", Collections.nCopies(puzzle.length, "---"));
        builder.append(delimiter);
        for (int[] row : puzzle) {
            builder.append("\n|");
            for (int col = 0; col < puzzle.length; col++) {
                builder.append(row[col]);
                if (row[col] < 10) {
                    builder.append(' ');
                }
                builder.append('|');
            }
            builder.append("\n").append(delimiter);
        }
        System.out.println("\n\n" + builder);
    }

}
