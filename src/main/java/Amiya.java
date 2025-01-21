import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Amiya {
    static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greeting("Amiya");
        System.out.println("_______________________");
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine().trim();
            String[] parts = command.split("\\s+");
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                list();
            } else if (parts[0].equalsIgnoreCase("mark")) {
                handleMarking(parts, true);
            } else if (parts[0].equalsIgnoreCase("unmark")) {
                handleMarking(parts, false);
            } else {
                addTask(new Task(command));
            }
        }
        exit();
        scanner.close();
    }

    public static void greeting(String name) {
        System.out.printf("Hello Dokutah! I'm %s.%n", name);
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("さようなら! Hope to see you again soon.");
    }

    public static void echo(String command) {
        if (command.equals("bye")) {
            exit();
            return;
        }

        String translatedCommand = translateToJapanese(command);

        System.out.println("_______________________");
        System.out.println(translatedCommand);
        System.out.println("_______________________");
    }

    public static String translateToJapanese(String command) {
        return switch (command.toLowerCase()) {
            case "hello" -> "こんにちは! ";
            case "thank you" -> "ありがとう! ";
            default -> command;
        };
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("added: " + task.getDescription());
        System.out.println("_______________________");
    }

    public static void list() {
        System.out.println("Here's a list of your tasks: ");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("_______________________");
    }

    public static void handleMarking(String[] parts, boolean isMarking) {
        if (parts.length > 1) {
            try {
                int taskId = Integer.parseInt(parts[1]);
                if (taskId - 1 >= 0 && taskId - 1 < taskList.size()) {
                    Task task = taskList.get(taskId);
                    if (isMarking) {
                        task.mark();
                    } else {
                        task.unmark();
                    }
                    System.out.println("_______________________");
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            catch (NumberFormatException e){
                System.out.println("Invalid task number.");
            }
        }
    }

}
