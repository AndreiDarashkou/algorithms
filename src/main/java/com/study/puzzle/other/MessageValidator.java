package com.study.puzzle.other;

import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;

//https://www.codewars.com/kata/5fc7d2d2682ff3000e1a3fbc/train/java
public class MessageValidator {

    public static boolean isAValidMessage(String message) {
        if (message == null || message.isEmpty() || message.charAt(0) == '0') {
            return true;
        }
        if(!isDigit(message.charAt(0)) || isDigit(message.charAt(message.length() - 1))) {
            return false;
        }

        List<Integer> words = Arrays.stream(message.split("\\d+"))
                .filter(word -> !word.isEmpty())
                .map(String::length).collect(toList());

        List<Integer> digits = Arrays.stream(message.split("\\D+"))
                .map(Integer::valueOf).collect(toList());

        return words.equals(digits);
    }
}
