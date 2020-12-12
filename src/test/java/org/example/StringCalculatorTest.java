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
}
