package com.study.algorithm.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class MathExpressionEvaluator {

    private MathExpressionEvaluator() {
    }

    public static double calculate(String exp) {
        if (exp.contains("(")) {
            int start = exp.indexOf("(") + 1;
            int nesting = 0;
            for (int end = start; end < exp.length(); end++) {
                if (exp.charAt(end) == '(') {
                    nesting++;
                }
                if (exp.charAt(end) == ')') {
                    if (nesting == 0) {
                        String subExp = exp.substring(start, end);
                        boolean isNegative = start > 1 && exp.charAt(start - 2) == '-';
                        return calculate(exp.replace((isNegative ? "-(" : "(") + subExp + ")",
                                String.valueOf(isNegative ? -calculate(subExp) : calculate(subExp))));
                    } else {
                        nesting--;
                    }
                }
            }
        }
        List<String> tokens = tokenize(exp);
        return convert(calculate(tokens).get(0));
    }

    private static List<String> calculate(List<String> tokens) {
        if (tokens.size() == 1) {
            return tokens;
        }
        int index;
        if (tokens.contains("*") || tokens.contains("/")) {
            index = index(tokens, "*", "/");
        } else {
            index = index(tokens, "+", "-");
        }
        String res = simpleExpression(tokens.get(index - 1), tokens.get(index + 1), tokens.get(index));
        tokens.remove(index - 1);
        tokens.remove(index - 1);
        tokens.set(index - 1, res);

        return calculate(tokens);
    }

    private static int index(List<String> tokens, String op1, String op2) {
        int mul = tokens.indexOf(op1);
        int div = tokens.indexOf(op2);
        return mul == -1 ? div : div == -1 ? mul : Math.min(mul, div);
    }

    private static List<String> tokenize(String expression) {
        List<String> res = new ArrayList<>();
        StringBuilder val = new StringBuilder();
        boolean isUnary = true;

        for (char ch : expression.toCharArray()) {
            if (ch == '+' || ch == '*' || ch == '/' || (ch == '-' && !isUnary)) {
                res.add(val.toString());
                val.setLength(0);
                res.add(String.valueOf(ch));
                isUnary = true;
            } else {
                val.append(ch);
                if (ch != ' ') {
                    isUnary = false;
                }
            }
        }
        res.add(val.toString());

        return res.stream().map(String::trim).collect(Collectors.toList());
    }

    private static String simpleExpression(String left, String right, String operator) {
        switch (operator) {
            case "*":
                return (convert(left) * convert(right)) + "";
            case "/":
                return (convert(left) / convert(right)) + "";
            case "+":
                return (convert(left) + convert(right)) + "";
            case "-":
                return (convert(left) - convert(right)) + "";
        }
        throw new IllegalArgumentException();
    }

    private static double convert(String val) {
        return Double.parseDouble(val.trim());
    }

}
