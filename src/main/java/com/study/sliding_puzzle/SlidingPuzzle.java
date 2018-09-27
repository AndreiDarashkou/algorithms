package com.study.sliding_puzzle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.study.algorithm.dijkstra.DijkstraAlgorithm.findLowestCostWay;

public class SlidingPuzzle {

    private List<Integer> moveHistory = new ArrayList<>();

    public int[][] getPuzzle() {
        return puzzle;
    }

    private final int[][] puzzle;
    private final int[][] grid;
    private final int size;

    public SlidingPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
        this.size = puzzle.length;
        this.grid = new int[puzzle.length][puzzle[0].length];
    }

    public List<Integer> solve() {
        int element = 1;
        int max = size * (size - 2);

        while (element <= max) {
            if (element % size == size - 1) {
                placeLastTwoInRow(element);
                element += 2;
            } else {
                placeElement(element, element);
                element++;
            }
        }
        placeLastTwoRows(element);

        return moveHistory;
    }

    private void placeLastTwoRows(int element) {
        int max = size * (size - 1);
        while (true) {
            if (element == max - 1) {
                placeLastThreeElements(element);
                break;
            }
            placeElement(element, size * size);
            grid[size - 1][size - 1] = 0;

            int underElement = element + size;
            placeElement(underElement, element);
            placeElement(element, element + 1);
            Point underElementPosition = correctPosition(underElement);
            moveZero(underElementPosition);
            swap(getZero(), correctPosition(element));
            Point afterElementPosition = correctPosition(element + 1);
            swap(getZero(), afterElementPosition);
            grid[afterElementPosition.x][afterElementPosition.y] = 0;
            grid[underElementPosition.x][underElementPosition.y] = 1;
            element++;
        }
    }

    private void placeLastThreeElements(int element) {
        int maxIndex = size - 1;
        int attempts = 0;
        while (!(puzzle[maxIndex][maxIndex] == 0 && puzzle[maxIndex][maxIndex - 1] == size * size - 1
                && puzzle[maxIndex - 1][maxIndex - 1] == element)) {
            Point zero = getZero();
            int place = element + 1; //right
            if (zero.x == maxIndex && zero.y == maxIndex) { //left
                place = element + size;
            } else if (zero.x == maxIndex - 1 && zero.y == maxIndex) {//down
                place = element + size + 1;
            } else if (zero.x == maxIndex && zero.y == maxIndex - 1) {//up
                place = element;  //up
            }
            swap(zero, correctPosition(place));
            if (++attempts > 10) {
                throw new IllegalArgumentException("invalid puzzle");
            }
        }
    }

    private void placeElement(int element, int position) {
        moveZeroToNext(element);
        Point actual = actualPlace(element);
        Point correct = correctPosition(position);
        move(actual, correct);
        grid[correct.x][correct.y] = 1;
    }

    private void placeLastTwoInRow(int element) {
        placeElement(element, element + size * 2);
        Point pos = correctPosition(element + size * 2);
        grid[pos.x][pos.y] = 0;

        placeElement(element + 1, element);
        placeElement(element, element + size);
        moveZeroToNext(element + 1);

        Point preLastInRowPosition = correctPosition(element);
        swap(getZero(), preLastInRowPosition);
        Point underPreLastInRowPosition = correctPosition(element + size);
        swap(getZero(), underPreLastInRowPosition);

        grid[preLastInRowPosition.x][preLastInRowPosition.y] = 1;
        grid[underPreLastInRowPosition.x][underPreLastInRowPosition.y] = 0;

        Point lastInRowPosition = correctPosition(element + 1);
        grid[lastInRowPosition.x][lastInRowPosition.y] = 1;
    }

    private void moveZeroToNext(int nextToPlace) {
        Point actualPlace = actualPlace(nextToPlace);
        Point next = fixPosition(actualPlace);

        grid[actualPlace.x][actualPlace.y] = 1; //freeze element
        moveZero(next); //move zero to target
        grid[actualPlace.x][actualPlace.y] = 0; //unfreeze
    }

    private void move(Point actual, Point correct) {
        List<Point> way = findLowestCostWay(grid, actual.x, actual.y, correct.x, correct.y);
        for (Point p : way) {
            grid[actual.x][actual.y] = 1;//freeze
            moveZero(p);
            grid[actual.x][actual.y] = 0;//unfreeze
            swap(p, actual);
            actual = p;
        }
    }

    private Point getZero() {
        return actualPlace(0);
    }

    private void moveZero(Point to) {
        Point zero = getZero();
        List<Point> way = findLowestCostWay(grid, zero.x, zero.y, to.x, to.y);
        for (Point toPoint : way) {
            swap(getZero(), toPoint);
        }
    }

    private void swap(Point from, Point to) {
        moveHistory.add(puzzle[to.x][to.y]);

        int temp = puzzle[from.x][from.y];
        puzzle[from.x][from.y] = puzzle[to.x][to.y];
        puzzle[to.x][to.y] = temp;
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

    private Point correctPosition(int tile) {
        return new Point((tile - 1) / size, (tile - 1) % size);
    }

    private Point actualPlace(int tile) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (puzzle[row][col] == tile) {
                    return new Point(row, col);
                }
            }
        }
        throw new IllegalArgumentException("invalid puzzle");
    }

}
