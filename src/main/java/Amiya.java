import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Amiya {
    static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greeting("Amiya");
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
                    list();
                } else if (commandType.equalsIgnoreCase("echo")) {
                    echo(command);
                } else if (commandType.equals("mark") || commandType.equals("unmark")) {
                    handleMarking(parts, commandType.equals("mark"));
                } else if (commandType.equals("delete")) {
                    deleteTask(parts);
                } else if (commandType.equals("todo") ||
                           commandType.equals("deadline") ||
                           commandType.equals("event")) {
                    handleTasks(command);
                } else {
                    throw new AmiyaException("this command is unfamiliar to me.");
                }
            } catch (AmiyaException e) {
                System.out.println(e.getMessage());
            }
        }
        exit();
        scanner.close();
    }

    public static void greeting(String name) {
        System.out.printf("Hello Dokutah! I'm %s.%n", name);
        System.out.println("What can I do for you?");
        System.out.println("_______________________");
    }

    public static void exit() {
        System.out.println("さようなら! Hope to see you again soon.");
    }

    public static Task parseTask(String input) throws AmiyaException {
        String[] parts = input.split("\\s+", 2);
        String type = parts[0].toLowerCase();

        if (parts.length < 2) {
            throw new AmiyaException("it seems like the task is missing a description. Could you please provide more details?");
        }

        String details = parts[1];

        switch (type) {
            case "todo":
                return new Todo(details.trim());
            case "deadline": {
                // first index is the description, second index is the dueDate
                String[] deadlineTask = parts[1].split(" /by", 2);
                if (deadlineTask.length == 2) {
                    return new Deadline(deadlineTask[0], deadlineTask[1].trim());
                }
                break;
            }
            case "event": {
                // first index is the description, second/third index is the startTime/endTime
                String[] eventTask = parts[1].split(" /from | /to", 3);
                if (eventTask.length == 3) {
                    return new Event(eventTask[0], eventTask[1].trim(), eventTask[2].trim());
                }
                break;
            }

            default:
                break;
        }
        return null;
    }

    public static void handleTasks(String command) {
        try {
            Task task = parseTask(command);

            if (task != null) {
                addTask(task);
            }
        } catch (AmiyaException e) {
            System.out.println(e.getMessage());
            System.out.println("_______________________");
        }
    }

    public static void echo(String command) {
        if (command.length() > 5) {
            String text = command.substring(5).trim();
            String translatedCommand = translateToJapanese(text);
            System.out.println(translatedCommand);
            System.out.println("_______________________");
        } else {
            System.out.println("Please provide some text to echo.");
            System.out.println("_______________________");
        }
    }

    public static String translateToJapanese(String command) {
        return switch (command.toLowerCase()) {
            case "hello" -> "こんにちは!";
            case "thank you" -> "ありがとう!";
            default -> command;
        };
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I have added this task:");
        System.out.println("   " + task.toString());
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("_______________________");
    }

    public static void deleteTask(String[] parts) {
        int taskId = Integer.parseInt(parts[1]);
        try {
            if (taskList.isEmpty()) {
                throw new AmiyaException("there are no tasks to delete yet.");
            }
            if (taskId <= 0 || taskId > taskList.size()) {
                throw new AmiyaException("please provide a valid task number.");
            }
            System.out.println("Noted. I have removed this task:");
            System.out.println("   " + taskList.get(taskId - 1).toString());
            taskList.remove(taskId - 1);
            System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
            System.out.println("_______________________");
        } catch (AmiyaException e) {
            System.out.println(e.getMessage());
            System.out.println("_______________________");
        }
    }

    public static void list() {
        if (taskList.isEmpty()) {
            System.out.println("Currently there are no tasks. Have a rest!");
        } else {
            System.out.println("Dokutah. Here's a list of your tasks:");
            for(int i = 0; i < taskList.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + taskList.get(i).toString());
            }
            System.out.println("_______________________");
        }
    }

    public static void handleMarking(String[] parts, boolean isMarking) throws AmiyaException {
        if (taskList.isEmpty()) {
            throw new AmiyaException("there are no tasks to mark. Please add some tasks first.");
        }

        if (parts.length > 1) {
            try {
                int taskId = Integer.parseInt(parts[1]);
                if (taskId - 1 >= 0 && taskId - 1 < taskList.size()) {
                    Task task = taskList.get(taskId - 1);
                    if (isMarking) {
                        if (task.getStatus()) {
                            System.out.println("You have already marked this task.");
                            System.out.println("_______________________");
                            return;
                        }
                        task.mark();
                    } else {
                        if (!task.getStatus()) {
                            System.out.println("It was already unmarked.");
                            System.out.println("_______________________");
                            return;
                        }
                        task.unmark();
                    }
                    System.out.println("_______________________");
                } else {
                    throw new AmiyaException("it is an invalid task number.");
                }
            }
            catch (NumberFormatException e){
                throw new AmiyaException("it is an invalid task number.");
            }
        }
    }

}
