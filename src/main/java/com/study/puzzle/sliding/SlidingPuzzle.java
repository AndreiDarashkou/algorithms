package com.study.puzzle.sliding;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.study.algorithm.network.finding_path.DijkstraAlgorithm.findLowestCostWay;
import static com.study.puzzle.sliding.SlidingPuzzleGenerator.print;

public class SlidingPuzzle {

    private List<Integer> moveHistory = new ArrayList<>();

    public int[][] getPuzzle() {
        return puzzle;
    }

    private final int[][] puzzle;
    private final int[][] grid;
    private final int height;
    private final int width;

    public SlidingPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
        this.height = puzzle.length;
        this.width = puzzle[0].length;
        this.grid = new int[height][width];
    }

    public List<Integer> solve() {
        int element = 1;
        int max = width * (height - 2);

        while (element <= max) {
            if (element % width == width - 1) {
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
        int max = width * (height - 1) - 1;
        while (true) {
            if (element == max) {
                placeLastThreeElements(element);
                break;
            }
            placeElement(element, width * height);
            grid[height - 1][width - 1] = 0;

            int underElement = element + width;
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
        int maxXIndex = height - 1;
        int maxYIndex = width - 1;
        int attempts = 0;
        while (!(puzzle[maxXIndex][maxYIndex] == 0 && puzzle[maxXIndex][maxYIndex - 1] == height * width - 1
                && puzzle[maxXIndex - 1][maxYIndex - 1] == element)) {
            Point zero = getZero();
            int place = element + 1; //right
            if (zero.x == maxXIndex && zero.y == maxYIndex) { //left
                place = element + width;
            } else if (zero.x == maxXIndex - 1 && zero.y == maxYIndex) {//down
                place = element + width + 1;
            } else if (zero.x == maxXIndex && zero.y == maxYIndex - 1) {//up
                place = element;  //up
            }
            swap(zero, correctPosition(place));
            if (++attempts > 10) {
                moveHistory = null;
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
        placeElement(element, element + width * 2);
        Point pos = correctPosition(element + width * 2);
        grid[pos.x][pos.y] = 0;

        placeElement(element + 1, element);
        placeElement(element, element + width);
        moveZeroToNext(element + 1);

        Point preLastInRowPosition = correctPosition(element);
        swap(getZero(), preLastInRowPosition);
        Point underPreLastInRowPosition = correctPosition(element + width);
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
        print(puzzle);
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
        if (y + 1 < width && grid[x][y + 1] == 0) {//right
            return new Point(x, y + 1);
        }
        if (x + 1 < height && grid[x + 1][y] == 0) {//bottom
            return new Point(x + 1, y);
        }
        return point;
    }

    private Point correctPosition(int tile) {
        return new Point((tile - 1) / width, (tile - 1) % width);
    }

    private Point actualPlace(int tile) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (puzzle[row][col] == tile) {
                    return new Point(row, col);
                }
            }
        }
        throw new IllegalArgumentException("invalid puzzle");
    }

}
