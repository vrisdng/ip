public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.dueDate);
    }

    @Override
    public String getType() {
        return TaskType.DEADLINE.name();
    }

    @Override
    public String toFileFormat() {
        return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
