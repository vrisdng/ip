package amiya.parser;

import amiya.exception.AmiyaException;
import amiya.task.Deadline;
import amiya.task.Event;
import amiya.task.*;

/**
 * The Parser class is responsible for parsing user input commands and converting them into Task objects.
 * It identifies the type of task (TODO, DEADLINE, EVENT) based on the input and extracts relevant details.
 * If the input is invalid or incomplete, it throws an AmiyaException with an appropriate error message.
 */
public class Parser {

    /**
     * Parses a user command into a Task object based on the specified format.
     * The command can represent a TODO, DEADLINE, or EVENT task, with the appropriate details extracted.
     *
     * @param command The input command to parse, representing the task and its details.
     * @return A Task object corresponding to the input command.
     * @throws AmiyaException If the input command is in an invalid format or required details are missing.
     */
    public static Task parseTask(String command) throws AmiyaException {
        String[] parts = command.split(" /");
        String description = parts[0].substring(parts[0].indexOf(" ") + 1); // Extract task description
        Task task = null;

        if (command.startsWith("todo")) {
            task = new Todo(description);
        } else if (command.startsWith("deadline")) {
            if (parts.length < 2)
                throw new AmiyaException("Missing /by date-time for deadline task.");
            String by = parts[1].replaceFirst("^by\\s*", "");
            task = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (parts.length < 3) {
                throw new AmiyaException("Missing /from and /to date-time for event task.");
            }
            String from = parts[1].replaceFirst("^from\\s*", "");
            String to = parts[2].replaceFirst("^to\\s*", "");
            task = new Event(description, from, to);
        } else {
            throw new AmiyaException("Unknown task type. Please use todo, deadline, or event.");
        }

        return task;
    }
}
