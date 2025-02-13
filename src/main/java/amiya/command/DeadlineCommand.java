package amiya.command;

import amiya.task.TaskList;
import amiya.task.Deadline;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        if (description.isBlank() || by.isBlank()) {
            throw new AmiyaException("deadline description and due date cannot be empty.");
        }

        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        storage.save(taskList.getTasks());
        return ui.showTaskAdded(deadline, taskList.size());
    }
}
