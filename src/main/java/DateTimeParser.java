import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final String DATE_TIME_PATTERN = "\\d{1,2}-\\d{1,2}-\\d{4} \\d{2}:\\d{2}"; // e.g., "2-12-2019 18:00"

    public static LocalDateTime parseDateTime(String dateTime) {
        dateTime = dateTime.trim();
        if (dateTime.matches(DATE_TIME_PATTERN)) {
            return parseDateTimeWithFormat(dateTime);
        } else {
            throw new IllegalArgumentException("Unsupported date-time format");
        }
    }

    private static LocalDateTime parseDateTimeWithFormat(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }
}
