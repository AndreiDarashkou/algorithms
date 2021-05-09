package com.study.algorithm.string;

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
        int lastIndex = subArray.length - 1;

        for (int i = lastIndex; i < textArray.length; ) {
            isFound = true;
            for (int j = lastIndex; j >= 0; j--) {
                int currIndex = i - (lastIndex - j);
                if (subArray[j] != textArray[currIndex]) {
                    int deltaIndex = search(subArray, textArray[currIndex]);
                    if (deltaIndex == -1) {
                        i += subArray.length;
                    } else {
                        i = i > currIndex + deltaIndex ? i + 1 : currIndex + deltaIndex;
                    }
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

    private static int search(char[] array, char element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i]) {
                return array.length - i - 1;
            }
        }
        return -1;
    }
}
