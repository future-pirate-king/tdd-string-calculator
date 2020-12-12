package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",";
    private static final Pattern N_DELIMITER_PATTERN = Pattern.compile("//\\[(.*?)\\]\n");
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("//(.*?)\n");
    private static final List<String> META_CHARS = Arrays.asList("*", "+", "$", "^");

    private int addCallCount = 0;

    public int add(String input) {
        addCallCount++;

        if (input.isEmpty()) {
            return 0;
        }

        var delimiter = getDelimiter(input);
        var values = input
                .replaceFirst(DELIMITER_PATTERN.pattern(), "")
                .split("(" + delimiter + ")|\\n");


        return checkNegatives(
                () -> Arrays
                        .stream(values)
                        .mapToInt(Integer::parseInt)
                )
                .filter(f -> f <= 1000)
                .sum();
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
        var nMatcher = N_DELIMITER_PATTERN.matcher(input);
        var matcher = DELIMITER_PATTERN.matcher(input);

        if (nMatcher.find()) {
            return getDelimiterForMatcher(nMatcher);
        } else if (matcher.find()) {
            return getDelimiterForMatcher(matcher);
        }

        return DEFAULT_DELIMITER;
    }

    private String getDelimiterForMatcher(Matcher matcher) {
        return matcher.group(1)
                .codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(v -> META_CHARS.contains(v) ? "\\" + v : v)
                .collect(Collectors.joining(""));
    }

    public int getCalledCount() {
        return addCallCount;
    }
}
