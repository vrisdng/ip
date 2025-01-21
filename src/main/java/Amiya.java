import java.util.Scanner;

public class Amiya {
    public static void main(String[] args) {
        greeting("Amiya");
        System.out.println("_______________________");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            echo(command);
        } while (!command.equals("bye"));
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
        switch (command.toLowerCase()) {
            case "hello":
                return "こんにちは! ";
            case "thank you":
                return "ありがとう! ";
            default:
                return command;
        }
    }
}
