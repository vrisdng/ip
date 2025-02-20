package amiya.parser;

import amiya.command.*;
import amiya.exception.AmiyaException;

/**
 * The Parser class handles user input commands and converts them into Task objects.
 * It identifies the type of task (TODO, DEADLINE, EVENT) and extracts relevant details.
 */
public class Parser {

/**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param command The user input command as a string.
     * @return The corresponding Command object.
     * @throws AmiyaException If the command is invalid or unknown.
     */
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
        case "clear": return parseClear(parts);
        case "todo": return parseTodo(parts);
        case "deadline": return parseDeadline(parts);
        case "event": return parseEvent(parts);
        default: throw new AmiyaException("I don't know this command.");
        }
    }

    private static Command parseEcho(String[] parts) throws AmiyaException {
        if (parts.length < 2) {
            throw new AmiyaException("echo command requires a text.");
        }
        return new EchoCommand(parts[1].trim());
    }

    private static Command parseFind(String[] parts) throws AmiyaException {
        if (parts.length < 2) {
            throw new AmiyaException("find command requires a keyword.");
        }
        return new FindCommand(parts[1].trim());
    }

    private static Command parseView(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AmiyaException("view command requires a date (in dd/mm/yyyy format).");
        }
        return new ScheduleCommand(parts[1].trim());
    }

    private static Command parseMark(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new AmiyaException("please give the index of the task you want to mark.");
        }
        return new MarkCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseUnmark(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new AmiyaException("please give the index of the task you want to unmark.");
        }
        return new UnmarkCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseRemove(String[] parts) throws AmiyaException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new AmiyaException("please give the index of the task you want to remove.");
        }
        return new RemoveCommand(Integer.parseInt(parts[1]));
    }

    private static Command parseClear(String[] parts) {
        return new ClearCommand();
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
}
