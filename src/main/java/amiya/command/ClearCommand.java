package amiya.command;

import amiya.exception.AmiyaException;
import amiya.storage.Storage;
import amiya.task.TaskList;
import amiya.ui.Ui;

/**
 * The ClearCommand class represents a command to clear all tasks from the task list.
 */
public class ClearCommand extends Command {

    /**
     * Executes the command to clear all tasks from the list.
     * It updates the UI, clears the storage, and resets the task list.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage system for saving changes.
     * @return
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        taskList.clearTasks();
        storage.save(taskList.getTasks());  // Persist the empty list
        return ui.showTasksCleared();
    }

    /**
     * Indicates that this command does not terminate the program.
     *
     * @return false since clearing tasks does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
