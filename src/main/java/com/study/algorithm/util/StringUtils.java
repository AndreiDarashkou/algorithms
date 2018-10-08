package com.study.algorithm.util;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

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

    /**
     * From https://www.codewars.com/kata/human-readable-duration-format/train/java
     */
    public static String formatDuration(int seconds) {
        System.out.println(seconds);
        int sec = seconds % 60;
        int min = seconds / 60;
        int hours = min / 60;
        int days = hours / 24;
        int years = days / 365;

        Map<String, Integer> dateMap = new LinkedHashMap<>();
        dateMap.put("year", years);
        dateMap.put("day", days % 365);
        dateMap.put("hour", hours % 24);
        dateMap.put("minute", min % 60);
        dateMap.put("second", sec);
        String date = dateMap.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(e -> e.getValue() + " " + e.getKey() + (e.getValue() == 1 ? "" : "s"))
                .collect(joining(", "));

        int index = date.lastIndexOf(',');
        if (index > 0) {
            return new StringBuilder(date).replace(index, index + 1, " and").toString();
        }
        return date.isEmpty() ? "now" : date;
    }

}
