package com.study.algorithm.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public final class MatrixMultiplicator {

    private MatrixMultiplicator() {
    }

    public static int[][] mul(int[][] a, int[][] b) throws InterruptedException {
        if (a == null || b == null || a[0].length != b.length) {
            throw new IllegalArgumentException("Unsuitable matrices");
        }
        int rows = a.length;
        int cols = b[0].length;
        int res[][] = new int[rows][cols];


        Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(rows * cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final int row = i;
                final int col = j;
                semaphore.acquire();
                service.submit(() -> {
                    try {
                        for (int k = 0; k < a[0].length; k++) {
                            res[row][col] += a[row][k] * b[k][col];
                        }
                    } finally {
                        semaphore.release();
                        countDownLatch.countDown();
                    }
                });
            }
        }

        countDownLatch.await();
        service.shutdown();

        return res;
    }

}