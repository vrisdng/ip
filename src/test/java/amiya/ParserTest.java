package amiya;

import amiya.command.*;
import amiya.exception.AmiyaException;
import amiya.parser.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseCommand_validEchoCommand_returnsEchoCommand() throws AmiyaException {
        Command command = Parser.parseCommand("echo Hello World!");
        assertTrue(command instanceof EchoCommand);
    }

    @Test
    void parseCommand_validTodoCommand_returnsAddTodoCommand() throws AmiyaException {
        Command command = Parser.parseCommand("todo Finish homework");
        assertTrue(command instanceof TodoCommand);
    }

    @Test
    void parseCommand_validDeadlineCommand_returnsAddDeadlineCommand() throws AmiyaException {
        Command command = Parser.parseCommand("deadline Project /by 10-02-2025 13:00");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    void parseCommand_validEventCommand_returnsAddEventCommand() throws AmiyaException {
        Command command = Parser.parseCommand("event Conference /from 15-05-2025 14:00 /to 15-05-2025 15:00");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    void parseCommand_invalidCommand_throwsAmiyaException() {
        assertThrows(AmiyaException.class, () -> Parser.parseCommand("invalid command"));
    }

    @Test
    void parseCommand_exitCommand_returnsExitCommand() throws AmiyaException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }
}
