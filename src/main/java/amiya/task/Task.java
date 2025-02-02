package amiya.task;

/**
 * Abstract class representing a task with a description and completion status.
 * Subclasses must implement methods to specify task type and file format.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     * "X" indicates completion, while an empty space indicates pending status.
     *
     * @return Status icon as a string.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param status True if the task is completed, false otherwise.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Gets the current completion status of the task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string with status icon and description.
     */
    @Override
    public String toString() {
        return String.format("[ %s ] %s", getStatusIcon(), description);
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.setStatus(true);
    }

    /**
     * Unmarks the task, setting it as incomplete.
     */
    public void unmark() {
        this.setStatus(false);
    }

    /**
     * Returns the specific type of the task.
     *
     * @return The task type as a string.
     */
    public abstract String getType();

    /**
     * Converts the task into a format suitable for file storage.
     *
     * @return A formatted string representing the task for storage.
     */
    public abstract String toFileFormat();

    /**
     * Creates a Task object from a file-formatted string.
     *
     * @param line A string representing a stored task.
     * @return The corresponding Task object, or null if the format is invalid.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 3) return null;

        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        return switch (parts[0]) {
            case "T" -> new Todo(description);
            case "D" -> new Deadline(description, parts[3]);
            case "E" -> new Event(description, parts[3], parts[4]);
            default -> null;
        };
    }
}
