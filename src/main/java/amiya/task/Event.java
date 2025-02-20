package amiya.task;

import amiya.exception.AmiyaException;
import amiya.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event in string format.
     * @param end The end time of the event in string format.
     * @throws AmiyaException If there is an error parsing the date and time.
     */
    public Event(String description, String start, String end) throws AmiyaException {
        super(description);
        this.startTime = DateTimeParser.parseDateTime(start);
        this.endTime = DateTimeParser.parseDateTime(end);
    }

    /**
     * Formats the given LocalDateTime object using the specified formatter.
     *
     * @param time The LocalDateTime object to format.
     * @param formatter The DateTimeFormatter to use for formatting.
     * @return The formatted date and time as a string.
     */
    public String getFormattedDueDate(LocalDateTime time, DateTimeFormatter formatter) {
        return time.format(formatter);
    }

    /**
     * Returns a string representation of the event task, including its type, status, description, start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusIcon(),
                this.description,
                getFormattedDueDate(startTime, formatter),
                getFormattedDueDate(endTime, formatter));
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return TaskType.EVENT.name();
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the event task in a format suitable for file storage.
     *
     * @return A string representation of the event task for file storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");
        return "EVENT | " + (isDone ? "1" : "0") + " | " + description
                + " | " + getFormattedDueDate(startTime, formatter)
                + " | " + getFormattedDueDate(endTime, formatter);
    }
}
