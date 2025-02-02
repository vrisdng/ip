package amiya.task;

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
        return amiya.task.TaskType.TODO.name();
    }

    @Override
    public String toFileFormat() {
        return "TODO | " + (isDone ? "1" : "0") + " | " + description;
    }
}
