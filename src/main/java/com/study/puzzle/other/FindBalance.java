package com.study.puzzle.other;

//https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/
public final class FindBalance {

    public static int find(int[] arr) {
        return find(arr, 0, arr.length - 1, arr[0], arr[arr.length - 1]);
    }

    private static int find(int[] arr, int left, int right, int leftSum, int rightSum) {
        while (right - left > 1) {
            int leftTemp = leftSum + arr[left + 1];
            int rightTemp = rightSum + arr[right - 1];

            int leftMove = Math.abs(leftTemp - rightSum);
            int rightMove =  Math.abs(rightTemp -leftSum);

            if (leftMove == rightMove) {
                int goLeft = find(arr, left, right - 1, leftSum, rightTemp);
                int goRight = find(arr, left + 1, right, leftTemp, rightSum);
                return Math.min(goLeft, goRight);
            }
            if (leftMove < rightMove) {
                leftSum = leftTemp;
                left++;
            } else {
                rightSum = rightTemp;
                right--;
            }
        }
        return Math.abs(leftSum - rightSum);
    }
}
