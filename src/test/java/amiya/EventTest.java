package amiya;

import amiya.task.*;
import amiya.exception.AmiyaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testEventCreation_validInput_createsEvent() throws AmiyaException {
        Event event = new Event("Conference", "15-03-2025 09:00", "15-03-2025 17:00");

        assertNotNull(event);
        assertEquals("Conference", event.getDescription());
        assertEquals(LocalDateTime.of(2025, 3, 15, 9, 0), event.getStartTime());
        assertEquals(LocalDateTime.of(2025, 3, 15, 17, 0), event.getEndTime());
    }

    @Test
    void testEventToString_validInput_returnsCorrectString() throws AmiyaException {
        Event event = new Event("Conference", "15-03-2025 09:00", "15-03-2025 17:00");

        String expected = "[E][ ] Conference (from: Mar 15 2025 09:00 to: Mar 15 2025 17:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    void testEventToFileFormat_validInput_returnsCorrectFileFormat() throws AmiyaException {
        Event event = new Event("Conference", "15-03-2025 09:00", "15-03-2025 17:00");

        String expected = "EVENT | 0 | Conference | 15-3-2025 09:00 | 15-3-2025 17:00";
        assertEquals(expected, event.toFileFormat());
    }
}

