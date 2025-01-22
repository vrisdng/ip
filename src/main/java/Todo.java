public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }

    @Override
    public String getType() {
        return TaskType.TODO.name();
    }
}
