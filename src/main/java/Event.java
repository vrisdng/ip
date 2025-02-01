public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                getStatusIcon(),
                this.description,
                this.startTime,
                this.endTime);
    }

    @Override
    public String getType() {
        return TaskType.EVENT.name();
    }

    @Override
    public String toFileFormat() {
        return "EVENT | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }
}
