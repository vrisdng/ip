package amiya.parser;

import amiya.exception.AmiyaException;
import amiya.task.Deadline;
import amiya.task.Event;
import amiya.task.*;

public class Parser {

    public static Task parseTask(String command) throws AmiyaException {
        String[] parts = command.split(" /");
        String description = parts[0].substring(parts[0].indexOf(" ") + 1); // Extract task description
        Task task = null;

        if (command.startsWith("todo")) {
            task = new Todo(description);
        } else if (command.startsWith("deadline")) {
            if (parts.length < 2) throw new AmiyaException("Missing /by date-time for deadline task.");
            String by = parts[1].replaceFirst("^by\\s*", "");
            task = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (parts.length < 3) throw new AmiyaException("Missing /from and /to date-time for event task.");
            String from = parts[1].replaceFirst("^from\\s*", "");
            String to = parts[2].replaceFirst("^to\\s*", "");
            task = new Event(description, from, to);
        } else {
            throw new AmiyaException("Unknown task type. Please use todo, deadline, or event.");
        }

        return task;
    }
}
