package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        ui.showFoundTasks(taskList.findTasks(keyword));
    }
}