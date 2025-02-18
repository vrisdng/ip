package amiya.task;

import amiya.exception.AmiyaException;
import amiya.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, String dueDate) throws AmiyaException {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert dueDate != null && !dueDate.trim().isEmpty() : "Due date string should not be null or empty";
        this.dueDate = DateTimeParser.parseDateTime(dueDate);
        assert this.dueDate != null : "Parsed due date should not be null";
    }

    public String getFormattedDueDate(DateTimeFormatter formatter) {
        return dueDate.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description,
                getFormattedDueDate(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String getType() {
        return TaskType.DEADLINE.name();
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toFileFormat() {
        return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | "
                + getFormattedDueDate(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }
}
