package amiya.parser;

import amiya.command.*;
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
    public static Command parseCommand(String command) throws AmiyaException {
        String[] parts = command.split("\\s+", 2);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "echo":
            if (parts.length < 2) throw new AmiyaException("echo command requires a text.");
            return new EchoCommand(parts[1].trim());
        case "find":
            if (parts.length < 2) throw new AmiyaException("find command requires a keyword.");
            return new FindCommand(parts[1].trim());
        case "mark":
            return new MarkCommand(Integer.parseInt(parts[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        case "remove":
            return new RemoveCommand(Integer.parseInt(parts[1]));

        case "todo":
            if (parts.length < 2) throw new AmiyaException("ToDo description cannot be empty.");
            return new TodoCommand(parts[1].trim());
        case "deadline":
            String[] deadlineParts = parts[1].split(" /by ", 2);
            if (deadlineParts.length < 2)
                throw new AmiyaException("please follow deadline format: deadline <description> /by <dd-MM-yyyy HH:mm>");
            return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
        case "event":
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            if (eventParts.length < 3)
                throw new AmiyaException("please follow event format: event <description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
            return new EventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());

        default:
            throw new AmiyaException("i don't know this command.");
        }
    }

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
                throw new AmiyaException("we are missing /by date-time for deadline task.");
            String by = parts[1].replaceFirst("^by\\s*", "");
            task = new Deadline(description, by);
        } else if (command.startsWith("event")) {
            if (parts.length < 3) {
                throw new AmiyaException("we are missing /from and /to date-time for event task.");
            }
            String from = parts[1].replaceFirst("^from\\s*", "");
            String to = parts[2].replaceFirst("^to\\s*", "");
            task = new Event(description, from, to);
        } else {
            throw new AmiyaException("i don't known this task type. Available: todo, deadline, or event.");
        }

        return task;
    }
}
