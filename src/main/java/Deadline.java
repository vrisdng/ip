import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = DateTimeParser.parseDateTime(dueDate);
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

    @Override
    public String toFileFormat() {
        return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | " +
                getFormattedDueDate(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
    }
}
