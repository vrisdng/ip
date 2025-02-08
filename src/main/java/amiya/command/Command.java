package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList The list of tasks.
     * @param ui       The UI for displaying messages.
     * @param storage  The storage to save changes.
     * @throws AmiyaException If an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException;

    /**
     * Determines if this command exits the application.
     *
     * @return true if the command exits the application, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
