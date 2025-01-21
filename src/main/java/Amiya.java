import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Amiya {
    static List<String> storage = new ArrayList<>();
    public static void main(String[] args) {
        greeting("Amiya");
        System.out.println("_______________________");
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                list();
            } else {
                store(command);
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

    public static void store(String command) {
        storage.add(command);
        System.out.println("added: " + command);
        System.out.println("_______________________");
    }

    public static void list() {
        for(int i = 0; i < storage.size(); i++) {
            System.out.println(i+1 + ". " + storage.get(i));
        }
        System.out.println("_______________________");
    }

}
