package amiya.command;

import amiya.exception.AmiyaException;
import amiya.storage.Storage;
import amiya.task.Task;
import amiya.task.Deadline;
import amiya.task.Event;
import amiya.task.TaskList;
import amiya.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleCommand extends Command {

    private String dateInput;

    public ScheduleCommand(String date) {
        this.dateInput = date;
    }


    public String execute(TaskList taskList, Ui ui, Storage storage) throws AmiyaException {
        try {
            // Parse the date input to a LocalDate object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateInput, formatter);

            // Load the tasks from the storage
            List<Task> tasks = storage.load();

            // Filter tasks that are on the specified date
            List<Task> tasksForDate = tasks.stream()
                    .filter(task -> isTaskOnDate(task, date))
                    .collect(Collectors.toList());

            // Display the tasks if any are found
            if (!tasksForDate.isEmpty()) {
                return ui.showTasksForDate(date, tasksForDate);
            } else {
                return ui.showNoTasksForDate(date);
            }

        } catch (Exception e) {
            throw new AmiyaException("wrong date format (should be dd-mm-yyyy)");
        }
    }

    /**
     * Checks if a task is due on a given date.
     * @param task The task to check.
     * @param date The date to compare.
     * @return true if the task is on the given date, false otherwise.
     */
    private boolean isTaskOnDate(Task task, LocalDate date) {
        if (task instanceof Deadline) {
            // Check if the deadline task is on the given date
            LocalDate taskDate = ((Deadline) task).getDueDate().toLocalDate();
            return taskDate.equals(date);
        } else if (task instanceof Event) {
            // Check if the event falls on the given date
            LocalDate startDate = ((Event) task).getStartTime().toLocalDate();
            LocalDate endDate = ((Event) task).getEndTime().toLocalDate();
            return (date.isEqual(startDate) || date.isEqual(endDate)) || 
                   (date.isAfter(startDate) && date.isBefore(endDate));
        }
        return false;
    }
}
