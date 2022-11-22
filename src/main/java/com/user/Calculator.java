package com.user;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        int result = 0;

        if (a < 0 && b < 0) {
            a = Math.abs(a);
            b = Math.abs(b);
            for (int i = 0; i < b; i++) {
                result += a;
            }
            return result;
        } else if (a < 0) {
            a = Math.abs(a);
            for (int i = 0; i < b; i++) {
                result += a;
            }
            return -result;
        } else if (b < 0) {
            b = Math.abs(b);
            for (int i = 0; i < b; i++) {
                result += a;
            }
            return -result;
        }
        for (int i = 0; i < b; i++) {
            result += a;
        }
        return result;
//        return (float) a / ((float)b / (float)b / (float)b);
    }

    public int divide(int a, int b) {
        return a / b;
    }
}
