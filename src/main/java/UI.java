import java.util.List;

public class UI {

    public void greeting(String name) {
        System.out.printf("Hello Dokutah! I'm %s.%n", name);
        System.out.println("What can I do for you?");
        System.out.println("_______________________");
    }

    public void exit() {
        System.out.println("さようなら! Hope to see you again soon.");
    }

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

    public String translateToJapanese(String command) {
        return switch (command.toLowerCase()) {
            case "hi" -> "こんにちは!";
            case "hello" -> "こんにちは!";
            case "thank you" -> "ありがとう!";
            default -> command;
        };
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showError(String message) {
        System.out.println(message);
        System.out.println("_______________________");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I have added this task:");
        System.out.println("   " + task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", taskCount);
        System.out.println("_______________________");
    }

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

    public void showTaskDeleted(Task task, int taskListSize) {
        System.out.println("Noted. I have removed this task:");
        System.out.println("   " + task);
        System.out.printf("Now you have %d tasks in the list.%n", taskListSize);
        System.out.println("_______________________");
    }

    public void showTaskMarked(Task task) {
        System.out.println("OK, I have marked this task as done:");
        System.out.println("   " + task);
        System.out.println("_______________________");
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I have unmarked this task:");
        System.out.println("   " + task);
        System.out.println("_______________________");
    }

    public static void showNoTasks() {
        System.out.println("There are no tasks to mark. Please add some tasks first.");
        System.out.println("_______________________");
    }

    public void showTaskListEmpty() {
        System.out.println("Currently there are no tasks. Have a rest!");
        System.out.println("_______________________");
    }

    public void showInvalidCommand(String message) {
        System.out.println(message);
        System.out.println("_______________________");
    }

    public void showTasksCleared() {
        System.out.println("All tasks have been cleared!");
        System.out.println("_______________________");
    }
}
