package amiya.command;

import amiya.task.TaskList;
import amiya.task.Event;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.exception.AmiyaException;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new AmiyaException("event description, start, and end times cannot be empty.");
        }

        Event event = new Event(description, from, to);
        taskList.addTask(event);
        storage.save(taskList.getTasks());
        return ui.showTaskAdded(event, taskList.size());
    }
}