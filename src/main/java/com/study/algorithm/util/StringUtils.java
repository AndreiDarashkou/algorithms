package com.study.algorithm.util;

public final class StringUtils {

    private StringUtils() {
    }

    /**
     * From http://www.codewars.com
     */
    public static String reverseWords(final String original) {
        char[] chars = original.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            }
            int startWord = i;
            for (int j = i; j < chars.length; j++) {
                if (chars[j] == ' ') {
                    break;
                }
                i++;
            }
            for (int k = startWord, index = 0; k < (startWord + i) / 2; k++, index++) {
                char temp = chars[k];
                chars[k] = chars[i - index - 1];
                chars[i - index - 1] = temp;
            }
        }
        return String.valueOf(chars);
    }


}
