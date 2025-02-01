abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[ %s ] %s", getStatusIcon(), description);
    }

    public void mark() {
        this.setStatus(true);
    }

    public void unmark() {
        this.setStatus(false);
    }
    public abstract String getType();

    public abstract String toFileFormat();

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