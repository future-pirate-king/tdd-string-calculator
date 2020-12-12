package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    @Test
    void testAddMethodForEmptyString() {
        var calculator = new StringCalculator();
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    void testAddMethodForOneInput() {
        var calculator = new StringCalculator();
        Assertions.assertEquals(1, calculator.add("1"));
    }

    @Test
    void testAddMethodForTwoInput() {
        var calculator = new StringCalculator();
        Assertions.assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void testAddMethodForUnknownNoOfInput() {
        var calculator = new StringCalculator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(17, calculator.add("1,2,6,8")),
                () -> Assertions.assertEquals(45, calculator.add("1,2,3,4,5,6,7,8,9"))
        );
    }

    @Test
    void testAddMethodNewLineInInput() {
        var calculator = new StringCalculator();
        Assertions.assertEquals(8, calculator.add("1\n2,5"));
    }

    @Test
    void testAddMethodDifferentDelimiter() {
        var calculator = new StringCalculator();

        Assertions.assertAll(
                () -> Assertions.assertEquals(17, calculator.add("//;\n1;2;6;8")),
                () -> Assertions.assertEquals(45, calculator.add("//*\n1*2*3*4*5*6*7*8*9"))
        );
    }

    @Test
    void testAddMethodWithNegativeNumbers() {
        var calculator = new StringCalculator();

        Assertions.assertAll(
                () -> {
                    var exception = Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> calculator.add("-1,2,3")
                    );

                    Assertions.assertEquals("negatives not allowed", exception.getMessage());
                },
                () -> {
                    var exception = Assertions.assertThrows(
                            IllegalArgumentException.class,
                            () -> calculator.add("-1,-2,3")
                    );

                    Assertions.assertEquals("negatives not allowed: [-1, -2]", exception.getMessage());
                }
        );
    }
}
