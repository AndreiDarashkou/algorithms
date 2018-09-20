package com.study;

import java.util.Arrays;

public class Test {


    public static void main(String[] args) {
        System.out.println(Arrays.hashCode(new int[]{1, 2}));
        //"(2**2)(3**3)(5)(7)(11**2)(17)"
        //System.out.println(shorterReverseLonger("asd", "ABCD"));
    }

    public static String shorterReverseLonger(String a, String b) {
        if (a.length() > b.length()) {
            return b + new StringBuilder(a).reverse().append(b);
        }
        return a + new StringBuilder(b).reverse().append(a);
    }

    private static final int[] PATTERN = new int[]{1, 10, 9, 12, 3, 4};

    public static long thirt(long n) {
        if (n < 100) {
            return n;
        }
        int[] digits = toArray(n);
        long temp = 0;
        while (digits.length > 2) {
            temp = 0;
            for (int i = digits.length - 1, cur = 0; i >= 0; i--, cur++) {
                int mul = PATTERN[cur % PATTERN.length];
                temp += mul * digits[i];
            }
            digits = toArray(temp);
        }
        return temp;
    }

    private static int[] toArray(long value) {
        return String.valueOf(value).chars().map(val -> val - 48).toArray();
    }


}

