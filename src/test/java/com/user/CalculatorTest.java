package com.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    @ParameterizedTest
    @CsvSource({"-1, 1, 0", "0, 1, 1", "5, 5, 10", "20, 10, 30", "-10, 10, 0", "15, 14, 29"})
    void add(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();
        int factResult = calculator.add(a, b);

        Assertions.assertEquals(expectedResult, factResult);
    }

    @ParameterizedTest
    @CsvSource({"5, 2, 3", "3, -10, 13", "0, -10, 10"})
    void subtraction(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();
        int factResult = calculator.subtraction(a, b);

        Assertions.assertEquals(expectedResult, factResult);
    }

    @ParameterizedTest
    @CsvSource({"2, 2, 4", "-10, 5, -50", "0, 5, 0"})
    void multiply(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();
        int factResult = calculator.multiply(a, b);

        Assertions.assertEquals(expectedResult, factResult);
    }

    @ParameterizedTest
    @CsvSource({"10, 2, 5", "-10, 2, -5", "3, 0, 0"})
    void divide(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();
        int factResult = calculator.divide(a, b);

        Assertions.assertEquals(expectedResult, factResult);
    }
}