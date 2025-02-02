package amiya.ui;

import amiya.task.Task;

import java.util.List;

/**
 * The UI class handles user interaction by displaying messages and responding to commands.
 */
public class UI {

    /**
     * Displays a greeting message with the given name.
     *
     * @param name The name to be included in the greeting.
     */
    public void greeting(String name) {
        System.out.printf("Hello Dokutah! I'm %s.%n", name);
        System.out.println("What can I do for you?");
        System.out.println("_______________________");
    }

    /**
     * Displays an exit message when the user leaves.
     */
    public void exit() {
        System.out.println("さようなら! Hope to see you again soon.");
    }

    /**
     * Echoes the provided command, optionally translating it into Japanese.
     *
     * @param command The command to be echoed.
     */
    public void echo(String command) {
        if (!command.isEmpty()) {
            String translatedCommand = translateToJapanese(command);
            System.out.println(translatedCommand);
            System.out.println("_______________________");
        } else {
            System.out.println("Please provide some text to echo.");
            System.out.println("_______________________");
        }
    }

    /**
     * Translates common English greetings into Japanese.
     *
     * @param command The command to translate.
     * @return The translated string or the original command if no translation is found.
     */
    public String translateToJapanese(String command) {
        return switch (command.toLowerCase()) {
            case "hi" -> "こんにちは!";
            case "hello" -> "こんにちは!";
            case "thank you" -> "ありがとう!";
            default -> command;
        };
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    /**
     * Displays a given error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
        System.out.println("_______________________");
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I have added this task:");
        System.out.println("   " + task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", taskCount);
        System.out.println("_______________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showTaskListEmpty();
        } else {
            System.out.println("Dokutah. Here's a list of your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + tasks.get(i).toString());
            }
            System.out.println("_______________________");
        }
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskListSize The remaining number of tasks.
     */
    public void showTaskDeleted(Task task, int taskListSize) {
        System.out.println("Noted. I have removed this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskListSize);
        System.out.println("_______________________");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("OK, I have marked this task as done:");
        System.out.println("   " + task);
        System.out.println("_______________________");
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I have unmarked this task:");
        System.out.println("   " + task);
        System.out.println("_______________________");
    }

    /**
     * Displays a message when there are no tasks to mark.
     */
    public static void showNoTasks() {
        System.out.println("There are no tasks to mark. Please add some tasks first.");
        System.out.println("_______________________");
    }

    /**
     * Displays a message when the task list is empty.
     */
    public void showTaskListEmpty() {
        System.out.println("Currently there are no tasks. Have a rest!");
        System.out.println("_______________________");
    }

    /**
     * Displays a message for an invalid command.
     *
     * @param message The message to display.
     */
    public void showInvalidCommand(String message) {
        System.out.println(message);
        System.out.println("_______________________");
    }

    /**
     * Displays a message when all tasks are cleared.
     */
    public void showTasksCleared() {
        System.out.println("All tasks have been cleared!");
        System.out.println("_______________________");
    }
}
