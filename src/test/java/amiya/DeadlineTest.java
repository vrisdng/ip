package amiya;

import amiya.task.*;
import amiya.exception.AmiyaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testDeadlineCreation_validInput_createsDeadline() throws AmiyaException {
        Deadline deadline = new Deadline("Finish homework", "10-03-2025 12:00");

        assertNotNull(deadline);
        assertEquals("Finish homework", deadline.getDescription());
        assertEquals(LocalDateTime.of(2025, 3, 10, 12, 0), deadline.getDueDate());
    }

    @Test
    void testDeadlineToString_validInput_returnsCorrectString() throws AmiyaException {
        Deadline deadline = new Deadline("Finish homework", "10-03-2025 12:00");

        String expected = "[D][ ] Finish homework (by: Mar 10 2025 12:00)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    void testDeadlineToFileFormat_validInput_returnsCorrectFileFormat() throws AmiyaException {
        Deadline deadline = new Deadline("Finish homework", "10-03-2025 12:00");

        String expected = "DEADLINE | 0 | Finish homework | 10-3-2025 12:00";
        assertEquals(expected, deadline.toFileFormat());
    }
}
