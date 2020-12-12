package org.example;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",";
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("//(.*?)\n");

    private int addCallCount = 0;

    public int add(String input) {
        addCallCount++;

        if (input.isEmpty()) {
            return 0;
        }

        var delimiter = getDelimiter(input);
        var values = input
                .replaceFirst(DELIMITER_PATTERN.pattern(), "")
                .split("[" + delimiter + "\\n]");


        return checkNegatives(
                () -> Arrays
                        .stream(values)
                        .mapToInt(Integer::parseInt)
                ).sum();
    }

    private IntStream checkNegatives(Supplier<IntStream> supplier) {
        var negativeValues = supplier.get().filter(f -> f < 0).toArray();

        if (negativeValues.length > 0) {
            final String message = "negatives not allowed";
            throw new IllegalArgumentException(
                    negativeValues.length > 1 ? message + ": " + Arrays.toString(negativeValues) : message
            );
        }

        return supplier.get();
    }

    private String getDelimiter(String input) {
        var matcher = DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return DEFAULT_DELIMITER;
    }

    public int getCalledCount() {
        return addCallCount;
    }
}
