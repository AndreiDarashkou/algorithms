package com.study.algorithm.backtracking;

import java.util.Stack;

public final class TowerOfHanoi {

    private TowerOfHanoi() {
    }

    public static void move(Peg fromPeg, Peg toPeg, Peg auxiliaryPeg, int count) {
        if (count > 1) {
            move(fromPeg, auxiliaryPeg, toPeg, count - 1);
        }
        toPeg.push(fromPeg.pop());
        if (count > 1) {
            move(auxiliaryPeg, toPeg, fromPeg, count - 1);
        }
    }

    static class Peg {
        Stack<Integer> disks = new Stack<>();
        String name;

        Peg(String name) {
            this.name = name;
        }

        int pop() {
            return disks.pop();
        }

        void push(int disk) {
            disks.push(disk);
        }
    }

}
