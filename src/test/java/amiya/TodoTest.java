package amiya;

import amiya.task.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("[T][ ] Buy groceries", todo.toString(), "ToString should return correct format before marking done.");
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Finish homework");
        assertFalse(todo.getStatus(), "Task should not be marked done initially.");

        todo.mark();
        assertTrue(todo.getStatus(), "Task should be marked as done after calling mark.");
    }
}
