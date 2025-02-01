import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<>();
        }

        List<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task = null;

                switch (parts[0]) {
                    case "TODO":
                        task = new Todo(parts[2]);
                        break;
                    case "DEADLINE":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "EVENT":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (parts[1].equals("1")) {
                        task.mark(); // Set task as done
                    }
                    taskList.add(task);
                }
            }
        }
        return taskList;
    }

    public void save(List<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
