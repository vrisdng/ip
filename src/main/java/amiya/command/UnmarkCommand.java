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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        taskList.unmarkTask(index);
        ui.showTaskUnmarked(taskList.getTasks().get(index - 1));
        storage.save(taskList.getTasks());
    }
}