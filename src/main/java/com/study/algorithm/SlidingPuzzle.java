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
        SlidingPuzzle puzzle = new SlidingPuzzle(generate(7));
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
        int place = element;
        int necessaryElement = element;
        boolean isLast = false;

        while (element <= max - (size * 2)) {
            place = element;
            necessaryElement = element;
            if (element % size == size - 1) { //если предпоследний
                necessaryElement++;
            } else if (element % size == 0) { //если последний
                necessaryElement--;
                place = necessaryElement + size;
                isLast = true;
            }
            if (isPlaced(necessaryElement, place)) {
                Point pos = correctPosition(place);//freeze place
                grid[pos.x][pos.y] = 1;
                element++;
                continue;
            }

            moveZeroToNext(necessaryElement);
            Point actual = actualPlace(necessaryElement);
            Point correct = correctPosition(place);
            move(actual, correct);
            if (isLast) {
                if (necessaryElement % size == 1) {
                    necessaryElement = necessaryElement - 2;
                    element--;
                }
                actual = actualPlace(necessaryElement);
                grid[actual.x][actual.y] = 1;
                moveZeroToNext(necessaryElement + 1);
                swap(getZero(), actual);
                grid[actual.x][actual.y] = 0;
                Point elementPlace = actualPlace(necessaryElement);
                grid[elementPlace.x][elementPlace.y] = 1;
                Point last = actualPlace(element);
                grid[last.x][last.y] = 1;
                element++;
                isLast = false;
            }
        }
        System.out.println("element: " + element);
        System.out.println("place: " + place);
        System.out.println("necessaryElement: " + necessaryElement);
        print(grid);
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
