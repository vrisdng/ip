package amiya.task;

import amiya.exception.AmiyaException;
import amiya.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline.
     * @param dueDate The due date of the deadline in string format.
     * @throws AmiyaException If there is an error parsing the date and time.
     */
    public Deadline(String description, String dueDate) throws AmiyaException {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert dueDate != null && !dueDate.trim().isEmpty() : "Due date string should not be null or empty";
        this.dueDate = DateTimeParser.parseDateTime(dueDate);
        assert this.dueDate != null : "Parsed due date should not be null";
    }

    /**
     * Formats the given LocalDateTime object using the specified formatter.
     *
     * @param formatter The DateTimeFormatter to use for formatting.
     * @return The formatted due date as a string.
     */
    public String getFormattedDueDate(DateTimeFormatter formatter) {
        return dueDate.format(formatter);
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, description, and due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description,
                getFormattedDueDate(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return TaskType.DEADLINE.name();
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return The due date of the deadline.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Returns a string representation of the deadline task in a format suitable for file storage.
     *
     * @return A string representation of the deadline task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | "
                + getFormattedDueDate(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }
}
