package amiya;

import java.io.*;

import amiya.command.Command;
import amiya.storage.Storage;
import amiya.task.*;
import amiya.storage.Storage;
import amiya.ui.Ui;
import amiya.parser.Parser;
import amiya.exception.AmiyaException;

/**
 * The Amiya class serves as the main entry point for the task management
 * application.
 * It handles user input, processes commands, and manages task storage and
 * retrieval.
 */
public class Amiya {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Initializes the application by setting up the UI, storage, and task list.
     * Loads previously saved tasks from the specified file.
     *
     * @param filePath The file path for storing task data.
     */
    public Amiya(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public Amiya() {
        super();
    }

    public void run() {
        ui.giveGreetings("Amiya");

        if (!taskList.getTasks().isEmpty()) {
            System.out.println("Previously saved tasks have been loaded:");
            ui.showTasks(taskList.getTasks());
        } else {
            System.out.println("No saved tasks found.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                Command command = Parser.parseCommand(input);

                command.execute(taskList, ui, storage);

                if (command.isExit()) {
                    ui.exit();
                    break;
                }
            } catch (AmiyaException e) {
                ui.showInvalidCommand(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Amiya("data/Amiya.txt").run();
    }

    /**
     * Parses and adds a task based on user input.
     * Displays appropriate messages upon successful task addition or error.
     *
     * @param command The user command containing task details.
     */
    public static void handleTasks(String command) {
        try {
            Task task = Parser.parseTask(command);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.size());
        } catch (AmiyaException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Amiya heard: " + input;
    }
}
