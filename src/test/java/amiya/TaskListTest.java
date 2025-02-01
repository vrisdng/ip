package amiya;

import amiya.exception.AmiyaException;
import amiya.task.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read a book");

        taskList.addTask(task);
        List<Task> tasks = taskList.getTasks();

        assertEquals(1, tasks.size(), "Task list should have one task after adding.");
        assertEquals(task, tasks.get(0), "Added task should be the same as retrieved task.");
    }

    @Test
    public void testRemoveTask() throws AmiyaException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Read a book");
        Task task2 = new Todo("Write a report");

        taskList.addTask(task1);
        taskList.addTask(task2);

        Task removedTask = taskList.removeTask(2);

        assertEquals(task2, removedTask, "Removed task should match the expected task.");
        assertEquals(1, taskList.getTasks().size(), "Task list should contain only one task after removal.");

        assertThrows(AmiyaException.class, () -> taskList.removeTask(5), "Should throw exception for invalid index.");
    }
}
