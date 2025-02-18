package amiya.storage;

import amiya.exception.AmiyaException;
import amiya.task.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class is responsible for loading and saving tasks from/to a file.
 * It provides methods to read tasks from a specified file path and store them in
 * a list of Task objects. Additionally, it allows saving a list of tasks back to the file.
 */
public class Storage {

    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the filePath.
     * If the file does not exist, a new file is created and an empty task list is returned.
     * The method parses each line of the file and creates Task objects based on the content.
     *
     * @return A list of Task objects loaded from the file.
     * @throws IOException If an I/O error occurs during reading from the file.
     */
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
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (AmiyaException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    /**
     * Parses a line of text and creates a Task object.
     *
     * @param line The line of text to parse.
     * @return The created Task object or null if parsing fails.
     */
    private Task parseTask(String line) throws AmiyaException {
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
        default:
            throw new AmiyaException("invalid task");
        }

        if (task != null && parts[1].equals("1")) {
            task.mark(); // Mark task as done if applicable
        }
        return task;
    }

    /**
     * Saves the list of tasks to the file specified in the file path.
     * Each task is written in a format that can be read later.
     *
     * @param tasks The list of Task objects to save to the file.
     */
    public void save(List<Task> tasks) throws AmiyaException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new AmiyaException("unable to save to file.");
        }
    }
}
