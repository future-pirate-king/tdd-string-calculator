package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    @Test
    void testAddMethodForEmptyString() {
        var calculator = new StringCalculator();
        Assertions.assertEquals(0, calculator.add(""));
    }
}
