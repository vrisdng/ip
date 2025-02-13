package amiya.command;

import amiya.exception.AmiyaException;
import amiya.storage.Storage;
import amiya.task.Task;
import amiya.task.Deadline;
import amiya.task.Event;
import amiya.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleCommand {

    private Storage storage;

    public ScheduleCommand(Storage storage) {
        this.storage = storage;
    }

    /**
     * Executes the schedule command and displays tasks on the given date.
     * @param dateInput The date provided by the user in dd/MM/yyyy format.
     */
    public void execute(String dateInput) throws AmiyaException {
        try {
            // Parse the date input to a LocalDate object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime date = LocalDateTime.parse(dateInput, formatter);

            // Load the tasks from the storage
            List<Task> tasks = storage.load();

            // Filter tasks that are on the specified date
            List<Task> tasksForDate = tasks.stream()
                    .filter(task -> isTaskOnDate(task, date))
                    .collect(Collectors.toList());

            // Display the tasks if any are found
            if (!tasksForDate.isEmpty()) {
                Ui.showTasksForDate(date, tasksForDate);
            } else {
                Ui.showNoTasksForDate(date);
            }

        } catch (Exception e) {
            throw new AmiyaException("wrong date format");
        }
    }

    /**
     * Checks if a task is due on a given date.
     * @param task The task to check.
     * @param date The date to compare.
     * @return true if the task is on the given date, false otherwise.
     */
    private boolean isTaskOnDate(Task task, LocalDateTime date) {
        if (task instanceof Deadline) {
            // Check if the deadline task is on the given date
            LocalDate taskDate = ((Deadline) task).getDueDate().toLocalDate();
            return taskDate.equals(date);
        } else if (task instanceof Event) {
            // Check if the event falls on the given date
            LocalDate taskStartDate = ((Event) task).getStartTime().toLocalDate();
            LocalDate taskEndDate = ((Event) task).getEndTime().toLocalDate();
            return !taskStartDate.isAfter(ChronoLocalDate.from(date)) && !taskEndDate.isBefore(ChronoLocalDate.from(date));
        }
        return false;
    }
}
