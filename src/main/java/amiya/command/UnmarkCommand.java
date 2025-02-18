package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        taskList.unmarkTask(index);
        storage.save(taskList.getTasks());
        return ui.showTaskUnmarked(taskList.getTasks().get(index - 1));
    }
}