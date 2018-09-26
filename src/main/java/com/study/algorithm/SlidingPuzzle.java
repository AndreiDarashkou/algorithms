package com.study.algorithm;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.study.algorithm.dijkstra.DijkstraAlgorithm.findLowestCostWay;

public class SlidingPuzzle {

    private final static int MIN_SIZE = 3;
    private final static int MAX_SIZE = 10;

    private final int[][] puzzle;
    private final int[][] grid;
    private final int size;

    public SlidingPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
        this.size = puzzle.length;
        this.grid = new int[puzzle.length][puzzle[0].length];
    }

    public static void main(String[] args) {
        int test[][] = new int[][]{{2, 7, 3}, {5, 8, 0}, {1, 6, 4}};
        SlidingPuzzle puzzle = new SlidingPuzzle(generate(5));
        puzzle.printPuzzle();
        puzzle.solve();
    }

    @SuppressWarnings("ConstantConditions")
    public static int[][] generate(int size) {
        size = Math.max(MIN_SIZE, Math.min(MAX_SIZE, size));
        int[][] puzzle = new int[size][size];
        LinkedList<Integer> values = IntStream.range(0, size * size).boxed().collect(Collectors.toCollection(LinkedList::new));
        Collections.shuffle(values);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                puzzle[row][col] = values.poll();
            }
        }
        return puzzle;
    }

    public void solve() {
        int element = 1;
        int max = size * size;

        while (element <= max - (size * 2)) {
            if (element % size == size - 1) { //если предпоследний
                placeLastTwoInRow(element);
                element += 2;
            } else {
                placeElement(element, element);
                element++;
            }
        }
        System.out.println("element: " + element);
        print(grid);
    }

    private void placeElement(int element, int position) {
        moveZeroToNext(element);
        Point actual = actualPlace(element);
        Point correct = correctPosition(position);
        move(actual, correct);
        grid[correct.x][correct.y] = 1;
    }

    private void placeLastTwoInRow(int element) {
        placeElement(element + 1, element);
        placeElement(element, element + size);
        moveZeroToNext(element + 1);
        Point underPreLastInRowPosition = correctPosition(element + size);
        swap(getZero(),underPreLastInRowPosition);
        grid[underPreLastInRowPosition.x][underPreLastInRowPosition.y] = 0;

        Point preLastInRowPosition = correctPosition(element);
        grid[preLastInRowPosition.x][preLastInRowPosition.y] = 1;
        Point lastInRowPosition = correctPosition(element + 1);
        grid[lastInRowPosition.x][lastInRowPosition.y] = 1;
    }

    private void moveZeroToNext(int nextToPlace) {
        Point actualPlace = actualPlace(nextToPlace);
        Point next = fixPosition(actualPlace);

        grid[actualPlace.x][actualPlace.y] = 1; //freeze element
        moveZero(next); //move zero to target
        grid[actualPlace.x][actualPlace.y] = 0; //unfreeze

        swap(getZero(), actualPlace);
    }

    private void move(Point actual, Point correct) {
        System.out.println("Move element from: " + actual + " to: " + correct);
        List<Point> way = findLowestCostWay(grid, actual.x, actual.y, correct.x, correct.y);
        System.out.println("Way: " + way);
        for (Point p : way) {
            grid[actual.x][actual.y] = 1;//freeze
            moveZero(p);
            grid[actual.x][actual.y] = 0;//unfreeze
            swap(actual, p);
            actual = p;
        }
    }

    private Point getZero() {
        return actualPlace(0);
    }

    private void moveZero(Point to) {
        Point zero = getZero();
        System.out.println("Move zero from: " + zero + " to: " + to);
        List<Point> way = findLowestCostWay(grid, zero.x, zero.y, to.x, to.y);
        System.out.println("Way: " + way);
        for (Point toPoint : way) {
            swap(zero, toPoint);
            zero = toPoint;
        }
    }

    private void swap(Point from, Point to) {
        int temp = puzzle[from.x][from.y];
        puzzle[from.x][from.y] = puzzle[to.x][to.y];
        puzzle[to.x][to.y] = temp;
        printPuzzle();
    }

    private Point fixPosition(Point point) {
        int x = point.x;
        int y = point.y;
        if (x - 1 >= 0 && grid[x - 1][y] == 0) {//top
            return new Point(x - 1, y);
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 0) {//left
            return new Point(x, y - 1);
        }
        if (y + 1 < size && grid[x][y + 1] == 0) {//right
            return new Point(x, y + 1);
        }
        if (x + 1 < size && grid[x + 1][y] == 0) {//bottom
            return new Point(x + 1, y);
        }
        return point;
    }

    private Point correctPosition(int bar) {
        return new Point((bar - 1) / size, (bar - 1) % size);
    }

    private boolean isPlaced(int element, int position) {
        return actualPlace(element).equals(correctPosition(position));
    }

    private boolean nextPlaced(int row, int col, int nextToPlace) {
        int val = (row * puzzle.length) + col + 1;
        return puzzle[row][col] == val && nextToPlace >= val;
    }

    private Point actualPlace(int bar) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (puzzle[row][col] == bar) {
                    return new Point(row, col);
                }
            }
        }
        throw new IllegalArgumentException("invalid puzzle");
    }

    private static boolean sorted(int[][] puzzle) {
        int max = puzzle.length * puzzle.length;
        for (int row = 0, value = 1; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle.length && value < max; col++, value++) {
                if (puzzle[row][col] != value) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printPuzzle() {
        print(puzzle);
    }

    private static void print(int[][] puzzle) {
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
