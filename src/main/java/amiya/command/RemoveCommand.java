package amiya.command;

import amiya.task.Task;
import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class RemoveCommand extends Command {
    private final int index;

    public RemoveCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        Task removedTask = taskList.removeTask(index);
        storage.save(taskList.getTasks());
        return ui.showTaskDeleted(removedTask, taskList.size());
    }
}