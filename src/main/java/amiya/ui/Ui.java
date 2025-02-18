package amiya.ui;

import amiya.task.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * The UI class handles user interaction by returning messages instead of printing directly.
 */
public class Ui {

    public String giveGreetings(String name) {
        return "Hello Dokutah! I'm " + name + ".\nWhat can I do for you?";
    }

    public String exit() {
        return "さようなら! Hope to see you again soon.";
    }

    public String echo(String command) {
        if (!command.isEmpty()) {
            return translateToJapanese(command);
        } else {
            return "Please provide some text to echo.";
        }
    }

    public String translateToJapanese(String command) {
        return switch (command.toLowerCase()) {
            case "hi" -> "こんにちは!";
            case "hello" -> "こんにちは!";
            case "thank you" -> "ありがとう!";
            default -> command;
        };
    }

    public String showLoadingError() {
        return "Error loading tasks from file.";
    }

    public String showError(String message) {
        if (message != null) {
            return message;
        } else {
            return "There is a problem loading your tasks.";
        }
    }

    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I have added this task:\n   " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    public String showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return showTaskListEmpty();
        } else {
            StringBuilder sb = new StringBuilder("Dokutah. Here's a list of your tasks:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("   ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    public String showFoundTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task task : tasks) {
                sb.append(count).append(". ").append(task).append("\n");
                count++;
            }
            return sb.toString().trim();
        }
    }

    public String showTaskDeleted(Task task, int taskListSize) {
        return "Noted. I have removed this task:\n   " + task + "\nNow you have " + taskListSize + " tasks in the list.";
    }

    public String showTaskMarked(Task task) {
        return "OK, I have marked this task as done:\n   " + task;
    }

    public String showTaskUnmarked(Task task) {
        return "OK, I have unmarked this task:\n   " + task;
    }

    public String showNoTasks() {
        return "There are no tasks to mark. Please add some tasks first.";
    }

    public String showTaskListEmpty() {
        return "Currently there are no tasks. Have a rest!";
    }

    public String showInvalidCommand(String message) {
        return message;
    }

    public String showTasksCleared() {
        return "All tasks have been cleared!";
    }

    public String showTasksForDate(LocalDate date, List<Task> tasks) {
        if (tasks.isEmpty()) {
            return showNoTasksForDate(date);
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks on " + date + ":\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("   ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    public String showNoTasksForDate(LocalDate date) {
        return "No tasks found for " + date.toString();
    }
}