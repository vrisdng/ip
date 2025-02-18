package amiya;

import amiya.command.*;
import amiya.exception.AmiyaException;
import amiya.storage.Storage;
import amiya.task.Deadline;
import amiya.task.Task;
import amiya.task.TaskList;
import amiya.task.Todo;
import amiya.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
        ui = new Ui();
        storage = new Storage("testData.txt");  // Dummy file path
    }

    @Test
    void executeEchoCommand_validInput_printsMessage() {
        EchoCommand command = new EchoCommand("Hello Test");
        command.execute(taskList, ui, storage);
        // Manual console check required
    }

    @Test
    void executeAddTodoCommand_validInput_addsTask() throws AmiyaException {
        TodoCommand command = new TodoCommand("Finish homework");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("Finish homework", taskList.getTasks().get(0).getDescription());
    }

    @Test
    void executeAddDeadlineCommand_validInput_addsTask() throws AmiyaException {
        DeadlineCommand command = new DeadlineCommand("Project", "10-02-2024 13:00");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("Project", taskList.getTasks().get(0).getDescription());
    }

    @Test
    void executeAddEventCommand_validInput_addsTask() throws AmiyaException {
        EventCommand command = new EventCommand("Conference", "11-02-2025 14:00", "11-02-2025 15:00");
        command.execute(taskList, ui, storage);
        assertEquals(1, taskList.size());
        assertEquals("Conference", taskList.getTasks().get(0).getDescription());
    }

    @Test
    void executeMarkCommand_validIndex_marksTask() throws AmiyaException {
        taskList.addTask(new Todo("Sample Task"));
        MarkCommand command = new MarkCommand(1);
        command.execute(taskList, ui, storage);
        assertTrue(taskList.getTasks().get(0).getStatus());
    }

    @Test
    void executeUnmarkCommand_validIndex_unmarksTask() throws AmiyaException {
        Task task = new Todo("Sample Task");
        task.mark();
        taskList.addTask(task);
        UnmarkCommand command = new UnmarkCommand(1);
        command.execute(taskList, ui, storage);
        assertFalse(taskList.getTasks().get(0).getStatus());
    }

    @Test
    void executeRemoveCommand_validIndex_removesTask() throws AmiyaException {
        taskList.addTask(new Todo("Task to remove"));
        RemoveCommand command = new RemoveCommand(1);
        command.execute(taskList, ui, storage);
        assertEquals(0, taskList.size());
    }

    @Test
    void executeClearCommand_clearsTaskList() throws AmiyaException {
        taskList.addTask(new Todo("Task 1"));
        taskList.addTask(new Todo("Task 2"));
        ClearCommand command = new ClearCommand();
        command.execute(taskList, ui, storage);
        assertEquals(0, taskList.size());
    }

    @Test
    void executeListCommand_withTasks_printsTasks() throws AmiyaException {
        taskList.addTask(new Todo("Finish homework"));
        taskList.addTask(new Todo("Attend meeting"));

        ListCommand command = new ListCommand();
        command.execute(taskList, ui, storage);
        assertEquals(2, taskList.size());
    }

    @Test
    void executeFindCommand_validKeyword_findsTasks() throws AmiyaException {
        taskList.addTask(new Todo("Meeting with team"));
        taskList.addTask(new Todo("Finish assignment"));
        FindCommand command = new FindCommand("Meeting");
        command.execute(taskList, ui, storage);
        // Manual console check required
    }

    @Test
    void executeScheduleCommand_setsScheduleForTask() throws AmiyaException {
        DeadlineCommand deadline = new DeadlineCommand("Finish homework", "01-03-2024 10:00");
        deadline.execute(taskList, ui, storage);

        ScheduleCommand scheduleCommand = new ScheduleCommand("01-03-2024");
        scheduleCommand.execute(taskList, ui, storage);

        Task task = taskList.getTasks().get(0);

        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;  // Casting to Deadline
            assertNotNull(deadlineTask.getDueDate());  // Check if due date is not null
        } else {
            fail("Task is not an instance of Deadline");
        }
    }

    @Test
    void executeExitCommand_triggersExit_returnsTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}

