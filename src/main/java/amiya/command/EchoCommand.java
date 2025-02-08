package amiya.command;

import amiya.task.TaskList;
import amiya.storage.Storage;
import amiya.ui.Ui;

public class EchoCommand extends Command {
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.echo(message);
    }
}
