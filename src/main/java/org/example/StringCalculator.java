package org.example;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIMITER = ",";
    private static final Pattern DELIMITER_PATTERN = Pattern.compile("//(.*?)\n");

    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        var delimiter = getDelimiter(input);
        var values = input
                .replaceFirst(DELIMITER_PATTERN.pattern(), "")
                .split("[" + delimiter + "\\n]");

        return Arrays
                .stream(values)
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String getDelimiter(String input) {
        var matcher = DELIMITER_PATTERN.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return DEFAULT_DELIMITER;
    }
}
