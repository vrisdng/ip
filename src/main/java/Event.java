import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String start, String end) {
        super(description);
        this.startTime = DateTimeParser.parseDateTime(start);
        this.endTime = DateTimeParser.parseDateTime(end);
    }

    public String getFormattedDueDate(LocalDateTime time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusIcon(),
                this.description,
                getFormattedDueDate(startTime, formatter),
                getFormattedDueDate(endTime, formatter));
    }

    @Override
    public String getType() {
        return TaskType.EVENT.name();
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        return "EVENT | " + (isDone ? "1" : "0") + " | " + description +
                " | " +getFormattedDueDate(startTime, formatter) + " | " + getFormattedDueDate(endTime, formatter);
    }
}
