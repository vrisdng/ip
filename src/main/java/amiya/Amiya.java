package amiya;

import java.io.*;
import java.util.Scanner;
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
        String command;
        while (true) {
            command = scanner.nextLine().trim();
            String[] parts = command.split("\\s+");
            String commandType = parts[0];
            try {
                if (command.equalsIgnoreCase("bye")) {
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    ui.showTasks(taskList.getTasks());
                } else if (commandType.equalsIgnoreCase("echo") && parts.length > 1) {
                    String textToEcho = command.substring(5).trim();
                    ui.echo(textToEcho);
                } else if (commandType.equalsIgnoreCase("find") && parts.length > 1) {
                    String keyword = command.substring(5).trim();
                    ui.showFoundTasks(taskList.findTasks(keyword));
                } else if (commandType.equals("mark")) {
                    taskList.markTask(Integer.parseInt(parts[1]));
                    ui.showTaskMarked(taskList.getTasks().get(Integer.parseInt(parts[1]) - 1));
                } else if (commandType.equals("unmark")) {
                    taskList.unmarkTask(Integer.parseInt(parts[1]));
                    ui.showTaskUnmarked(taskList.getTasks().get(Integer.parseInt(parts[1]) - 1));
                } else if (commandType.equals("remove")) {
                    Task task = taskList.removeTask(Integer.parseInt(parts[1]));
                    ui.showTaskDeleted(task, taskList.size());
                } else if (commandType.equals("clear")) {
                    taskList.clearTasks();
                    ui.showTasksCleared();
                } else if (commandType.equals("todo") || commandType.equals("deadline")
                        || commandType.equals("event")) {
                    handleTasks(command);
                } else {
                    throw new AmiyaException("this is an unknown command.");
                }
                storage.save(taskList.getTasks());
            } catch (AmiyaException | NumberFormatException e) {
                ui.showInvalidCommand(e.getMessage());
            }
        }
        ui.exit();
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
