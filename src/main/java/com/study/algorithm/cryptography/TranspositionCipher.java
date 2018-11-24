package com.study.algorithm.cryptography;

import java.util.ArrayList;
import java.util.List;

public final class TranspositionCipher {

    private TranspositionCipher() {
    }

    public static String encrypt(String message, String key) {
        byte[] mapping = mapping(key);
        int cols = mapping.length;

        char[] chars = message.toCharArray();
        int messSize = chars.length;
        int rows = messSize % cols == 0 ? messSize / cols : messSize / cols + 1;

        StringBuilder result = new StringBuilder();
        for (byte col : mapping) {
            for (int row = 0; row < rows; row++) {
                int index = row * cols + col;
                result.append(index < messSize ? chars[index] : 'x');
            }
        }
        return result.toString();
    }

    public static String decrypt(String message, String key) {
        byte[] mapping = inverse(mapping(key));
        int cols = mapping.length;

        char[] chars = message.toCharArray();
        int messSize = chars.length;
        int rows = messSize % cols == 0 ? messSize / cols : messSize / cols + 1;

        StringBuilder result = new StringBuilder();
        for (int row = 0; row < rows; row++) {
            for (byte col : mapping) {
                int index = col * rows + row;
                result.append(index < messSize ? chars[index] : 'x');
            }
        }
        return result.toString();
    }

    static byte[] mapping(String key) {
        byte[] bytes = key.getBytes();
        List<Byte> symbols = new ArrayList<>();
        for (byte symbol : bytes) {
            symbols.add(symbol);
        }
        symbols.sort(Byte::compareTo);

        byte[] mapping = new byte[bytes.length];
        for (int i = 0; i < mapping.length; i++) {
            mapping[i] = (byte) symbols.indexOf(bytes[i]);
        }
        return mapping;
    }

    static byte[] inverse(byte[] mapping) {
        byte[] inverse = new byte[mapping.length];
        List<Byte> symbols = new ArrayList<>();
        for (byte symbol : mapping) {
            symbols.add(symbol);
        }
        for (byte i = 0; i < inverse.length; i++) {
            inverse[i] = (byte) symbols.indexOf(i);
        }
        return inverse;
    }

}
