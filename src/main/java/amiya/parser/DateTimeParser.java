package amiya.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import amiya.exception.AmiyaException;

/**
 * A utility class for parsing date-time strings into LocalDateTime objects.
 */
public class DateTimeParser {

    // Regular expression pattern to match a valid date-time format (e.g., "2-12-2019 18:00")
    private static final String DATE_TIME_PATTERN = "\\d{1,2}-\\d{1,2}-\\d{4} \\d{2}:\\d{2}";

    /**
     * Parses a given date-time string into a LocalDateTime object.
     *
     * @param dateTime The date-time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws IllegalArgumentException If the provided date-time format is unsupported.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws AmiyaException {
        dateTime = dateTime.trim();
        if (dateTime.matches(DATE_TIME_PATTERN)) {
            return parseDateTimeWithFormat(dateTime);
        } else {
            throw new AmiyaException("this is an unsupported date-time format. Correct format: dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Parses a date-time string using a specific format pattern.
     *
     * @param dateStr The date-time string in the expected format "d-M-yyyy HH:mm".
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private static LocalDateTime parseDateTimeWithFormat(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }
}
