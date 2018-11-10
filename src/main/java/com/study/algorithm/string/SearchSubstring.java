package com.study.algorithm.string;

import java.util.Arrays;

public final class SearchSubstring {

    private SearchSubstring() {
    }


    public static int search(String text, String substring) {
        if (substring.length() > text.length()) {
            return -1;
        }
        boolean isFound;
        char[] textArray = text.toCharArray();
        char[] subArray = substring.toCharArray();
        for (int i = 0; i <= textArray.length - subArray.length; i++) {
            isFound = true;
            for (int j = 0; j < subArray.length; j++) {
                if (textArray[i + j] != subArray[j]) {
                    isFound = false;
                    break;
                }
            }
            if (isFound) {
                return i;
            }
        }
        return -1;
    }

    public static int searchBoyerMoore(String text, String substring) {
        if (substring.length() > text.length()) {
            return -1;
        }
        boolean isFound;
        char[] textArray = text.toCharArray();
        char[] subArray = substring.toCharArray();
        char[] subSorted = substring.toCharArray();
        Arrays.sort(subSorted);

        int lastIndex = subArray.length - 1;

        for (int i = lastIndex; i < textArray.length; i++) {
            if (Arrays.binarySearch(subSorted, textArray[i]) == -1) {
                i += lastIndex;
                continue;
            }
            if (subArray[lastIndex] != textArray[i]) {
                continue;
            }
            isFound = true;
            for (int j = lastIndex; j >= 0; j--) {
                if (subArray[j] != textArray[i - (lastIndex - j)]) {
                    isFound = false;
                    break;
                }
            }
            if (isFound) {
                return i - subArray.length + 1;
            }
        }
        return -1;
    }

}
