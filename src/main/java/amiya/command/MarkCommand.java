package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        taskList.markTask(index);
        storage.save(taskList.getTasks());
        return ui.showTaskMarked(taskList.getTasks().get(index - 1));
    }
}