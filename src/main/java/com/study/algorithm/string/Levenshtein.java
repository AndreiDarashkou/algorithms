package com.study.algorithm.string;

public final class Levenshtein {

    private Levenshtein() {
    }

    public static int levenshteinDistance(String first, String second) {
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();
        int n = firstChars.length + 1;
        int m = secondChars.length + 1;

        int[][] matrix = new int[n][m];
        int deletionCost = 1;
        int insertionCost = 1;

        for (var i = 0; i < n; i++) {
            matrix[i][0] = i;
        }
        for (var j = 0; j < m; j++) {
            matrix[0][j] = j;
        }

        for (var i = 1; i < n; i++) {
            for (var j = 1; j < m; j++) {
                var substitutionCost = firstChars[i - 1] == secondChars[j - 1] ? 0 : 1;

                matrix[i][j] = min(matrix[i - 1][j] + deletionCost,
                        matrix[i][j - 1] + insertionCost,
                        matrix[i - 1][j - 1] + substitutionCost);
            }
        }
        return matrix[n - 1][m - 1];
    }

    private static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
