public class Amiya {
    public static void main(String[] args) {
        greeting("Amiya");
        System.out.println("_______________________");
        exit();
    }

    public static void greeting(String name) {
        System.out.printf("Hello Dokutah! I'm %s.%n", name);
        System.out.println("What can I do for you?");
    }

    public static void exit() {
        System.out.println("さようなら! Hope to see you again soon.");
    }
}
