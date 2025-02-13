package amiya.command;

import amiya.task.TaskList;
import amiya.task.Todo;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        if (description.isBlank()) {
            throw new AmiyaException("ToDo description cannot be empty.");
        }

        Todo todo = new Todo(description);
        taskList.addTask(todo);
        storage.save(taskList.getTasks());
        return ui.showTaskAdded(todo, taskList.size());
    }
}
