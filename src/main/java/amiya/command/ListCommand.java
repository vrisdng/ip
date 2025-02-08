package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasks(taskList.getTasks());
    }
}