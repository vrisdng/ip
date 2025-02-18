package amiya.ui;

import amiya.task.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * The UI class handles user interaction by returning messages instead of printing directly.
 */
public class Ui {

    public String giveGreetings(String name) {
        StringBuilder sb = new StringBuilder("Hello Dokutah! I'm " + name + ". What can I do for you? \n");
        sb.append("Here's a list of commands you can use: \n");
        sb.append(listCommand());
        return sb.toString();
    }

    public String listCommand() {
        StringBuilder sb = new StringBuilder();

        sb.append("1. **bye**\n");
        sb.append("   - Exits the application.\n");
        sb.append("   - Usage: `bye`\n\n");

        sb.append("2. **list**\n");
        sb.append("   - Lists all tasks.\n");
        sb.append("   - Usage: `list`\n\n");

        sb.append("3. **echo**\n");
        sb.append("   - Repeats the text you provide.\n");
        sb.append("   - Usage: `echo <text>`\n");
        sb.append("   - Example: `echo Hello World!`\n\n");

        sb.append("4. **find**\n");
        sb.append("   - Searches for tasks containing the given keyword.\n");
        sb.append("   - Usage: `find <keyword>`\n");
        sb.append("   - Example: `find meeting`\n\n");

        sb.append("5. **view**\n");
        sb.append("   - Views tasks scheduled for a specific date.\n");
        sb.append("   - Usage: `view <dd/mm/yyyy>`\n");
        sb.append("   - Example: `view 12/03/2025`\n\n");

        sb.append("6. **mark**\n");
        sb.append("   - Marks a task as completed by task number.\n");
        sb.append("   - Usage: `mark <task number>`\n");
        sb.append("   - Example: `mark 1`\n\n");

        sb.append("7. **unmark**\n");
        sb.append("   - Unmarks a completed task by task number.\n");
        sb.append("   - Usage: `unmark <task number>`\n");
        sb.append("   - Example: `unmark 1`\n\n");

        sb.append("8. **remove**\n");
        sb.append("   - Removes a task by task number.\n");
        sb.append("   - Usage: `remove <task number>`\n");
        sb.append("   - Example: `remove 1`\n\n");

        sb.append("9. **todo**\n");
        sb.append("   - Adds a new ToDo task.\n");
        sb.append("   - Usage: `todo <description>`\n");
        sb.append("   - Example: `todo Buy groceries`\n\n");

        sb.append("10. **deadline**\n");
        sb.append("    - Adds a new Deadline task with a due date.\n");
        sb.append("    - Usage: `deadline <description> /by <dd-MM-yyyy HH:mm>`\n");
        sb.append("    - Example: `deadline Submit report /by 15-03-2025 23:59`\n\n");

        sb.append("11. **event**\n");
        sb.append("    - Adds a new Event task with a start and end time.\n");
        sb.append("    - Usage: `event <description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>`\n");
        sb.append("    - Example: `event Team meeting /from 10-03-2025 09:00 /to 10-03-2025 11:00`\n\n");

        return sb.toString();
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
            case "hello" -> "您好!";
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

    public String showTaskListEmpty() {
        return "Currently there are no tasks. Have a rest!";
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