package com.example.mycalculator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public final class MathEval {
    public MathEval() {
    }

    public static BigDecimal eval(String text) throws Exception {
        Character op = '+';
        text = text + "+";
        System.out.println("text " + text);
        StringBuilder n = new StringBuilder();
        BigDecimal result = BigDecimal.ZERO;
        Stack<BigDecimal> numbers = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            Character item = text.charAt(i);
            System.out.println("char at " + i + ": " + item);
            if (Character.isDigit(item) || item == '.') {
                n = n.append(item);
                continue;
            }

            // To account for negative operations (e.g "5*-2", 5/-2)
            if (i < text.length() - 1 && (!Character.isDigit(text.charAt(i - 1)) || i == 0)) {
                if (item == '-') {
                    n = n.append(item);
                    continue;
                }
            }

            if (!Character.isDigit(item)) {
                BigDecimal num = BigDecimal.ZERO;
                if (n.length() > 0) {
                    num = new BigDecimal(n.toString());
                    System.out.println("num " + num);
                    n.setLength(0);
                }

                switch (op) {
                    case '+':
                        numbers.push(num);
                        break;
                    case '-':
                        numbers.push(num.negate());
                        break;
                    case '*':
                        numbers.push(num.multiply((BigDecimal) numbers.pop()));
                        break;
                    case '/':
                        if (num == BigDecimal.ZERO) {
                            throw new ArithmeticException("Division by zero");
                        }
                        BigDecimal first = (BigDecimal) numbers.pop();
                        numbers.push(first.divide(num, 10, RoundingMode.HALF_UP));
                }
                op = item;
            }
        }
        for (BigDecimal no : numbers) {
            result = result.add(no);
        }
        return result;
    }
}
