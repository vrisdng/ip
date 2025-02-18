package amiya.parser;

import amiya.command.*;
import amiya.exception.AmiyaException;
import amiya.task.*;

/**
 * The Parser class handles user input commands and converts them into Task objects.
 * It identifies the type of task (TODO, DEADLINE, EVENT) and extracts relevant details.
 */
public class Parser {

    public static Command parseCommand(String command) throws AmiyaException {
        String[] parts = command.split("\\s+", 2);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "echo": return parseEcho(parts);
        case "find": return parseFind(parts);
        case "view": return parseView(parts);
        case "mark": return parseMark(parts);
        case "unmark": return parseUnmark(parts);
        case "remove": return parseRemove(parts);
        case "todo": return parseTodo(parts);
        case "deadline": return parseDeadline(parts);
        case "event": return parseEvent(parts);
        default: throw new AmiyaException("I don't know this command.");
        }
    }

    private static Command parseEcho(String[] parts) throws AmiyaException {
        if (parts.length < 2) throw new AmiyaException("Echo command requires a text.");
        return new EchoCommand(parts[1].trim());
    }

    private static Command parseFind(String[] parts) throws AmiyaException {
        if (parts.length < 2) throw new AmiyaException("Find command requires a keyword.");
        return new FindCommand(parts[1].trim());
    }

    private static Command parseView(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AmiyaException("View command requires a date (in dd/mm/yyyy format).");
        }
        return new ScheduleCommand(parts[1].trim());
    }

    private static Command parseMark(String[] parts) {
        return new MarkCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseUnmark(String[] parts) {
        return new UnmarkCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseRemove(String[] parts) {
        return new RemoveCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseTodo(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AmiyaException("ToDo description cannot be empty.");
        }
        return new TodoCommand(parts[1].trim());
    }

    private static Command parseDeadline(String[] parts) throws AmiyaException {
        if (parts.length < 2) throw new AmiyaException("Deadline description cannot be empty.");
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (deadlineParts.length < 2)
            throw new AmiyaException("Please follow deadline format: deadline <description> /by <dd-MM-yyyy HH:mm>");
        return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    private static Command parseEvent(String[] parts) throws AmiyaException {
        if (parts.length < 2) throw new AmiyaException("Event description cannot be empty.");
        String[] eventParts = parts[1].split(" /from | /to ", 3);
        if (eventParts.length < 3)
            throw new AmiyaException("Please follow event format: event <description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
        return new EventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }

    public static Task parseTask(String command) throws AmiyaException {
        String[] parts = command.split(" /");
        if (parts[0].trim().isEmpty() || !parts[0].contains(" ")) {
            throw new AmiyaException("Task description cannot be empty.");
        }
        String description = parts[0].substring(parts[0].indexOf(" ") + 1).trim();
        if (description.isEmpty()) throw new AmiyaException("Task description cannot be empty.");

        if (command.startsWith("todo")) {
            return new Todo(description);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineTask(description, parts);
        } else if (command.startsWith("event")) {
            return parseEventTask(description, parts);
        } else {
            throw new AmiyaException("I don't know this task type. Available: todo, deadline, or event.");
        }
    }

    private static Task parseDeadlineTask(String description, String[] parts) throws AmiyaException {
        if (parts.length < 2) throw new AmiyaException("Missing /by date-time for deadline task.");
        String by = parts[1].replaceFirst("^by\\s*", "").trim();
        if (by.isEmpty()) throw new AmiyaException("Deadline date/time cannot be empty.");
        return new Deadline(description, by);
    }

    private static Task parseEventTask(String description, String[] parts) throws AmiyaException {
        if (parts.length < 3) throw new AmiyaException("Missing /from and /to date-time for event task.");
        String from = parts[1].replaceFirst("^from\\s*", "").trim();
        String to = parts[2].replaceFirst("^to\\s*", "").trim();
        if (from.isEmpty() || to.isEmpty()) throw new AmiyaException("Event start and end time cannot be empty.");
        return new Event(description, from, to);
    }
}
