package amiya;

import java.io.*;

import amiya.command.Command;
import amiya.storage.Storage;
import amiya.task.*;
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
            System.out.println(ui.showLoadingError());
            taskList = new TaskList();
        }
    }

    public Amiya() {
        super();
        ui = new Ui();
        storage = new Storage("data/Amiya.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(ui.showLoadingError());
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (ui == null) {
            ui = new Ui(); // Ensure ui is initialized
        }

        try {
            Command command = Parser.parseCommand(input);
            String response = command.execute(taskList, ui, storage);
            if (command.isExit()) {
               response = ui.exit();
               new Thread(() -> {
                   try {
                       Thread.sleep(500);  // Wait for 0.5 seconds before exiting
                       System.exit(0);  // Exit the system after the delay
                   } catch (InterruptedException e) {
                       System.out.println(e.getMessage());
                       e.printStackTrace();
                   }
               }).start();
            }
            return response;
        } catch (AmiyaException e) {
            return ui.showError(e.getMessage());
        }
    }
}
