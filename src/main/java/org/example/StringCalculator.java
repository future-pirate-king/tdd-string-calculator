package org.example;

import java.util.Arrays;

public class StringCalculator {

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        var values = input.split("[,\\n]");
        return Arrays
                .stream(values)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
