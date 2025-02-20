package amiya.task;

public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its type, status, and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return amiya.task.TaskType.TODO.name();
    }

    /**
     * Returns a string representation of the todo task in a format suitable for file storage.
     *
     * @return A string representation of the todo task for file storage.
     */
    @Override
    public String toFileFormat() {
        return "TODO | " + (isDone ? "1" : "0") + " | " + description;
    }
}
