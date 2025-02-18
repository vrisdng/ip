package amiya.task;

import amiya.exception.AmiyaException;
import amiya.ui.Ui;

import java.util.List;
import java.util.ArrayList;

/**
 * Manages a list of tasks, providing functionality to add, remove, mark, and unmark tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Task list should not be null";
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add a null task";
        tasks.add(task);
        assert tasks.size() > 0 : "Task list should have at least one task now.";
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */

    public List<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword should not be null or empty";
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        assert matchingTasks != null : "Matching tasks list should not be null";
        return matchingTasks;
    }

    /**
     * Removes a task from the task list based on the given task ID.
     *
     * @param taskId The 1-based index of the task to remove.
     * @return The removed task.
     * @throws AmiyaException If the task list is empty or the task ID is invalid.
     */

    public Task removeTask(int taskId) throws AmiyaException {
        assert taskId > 0 : "Task ID must be greater than 0";
        if (tasks.isEmpty()) {
            throw new AmiyaException("there are no tasks to remove.");
        }
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new AmiyaException("please provide a valid task number.");
        }
        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        assert !tasks.contains(task) : "The task should be removed from the list";
        return task;
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearTasks() {
        tasks.clear();
        assert tasks.isEmpty() : "Task list should be empty after clear";
    }

    /**
     * Marks a task as completed.
     *
     * @param taskId The 1-based index of the task to mark.
     * @throws AmiyaException If the task list is empty or the task ID is invalid.
     */
    public void markTask(int taskId) throws AmiyaException {
        if (tasks.isEmpty()) {
            throw new AmiyaException("there are no tasks to remove.");
        }

        if (taskId - 1 < 0 || taskId - 1 >= tasks.size()) {
            throw new AmiyaException("this is an invalid task number.");
        }
        Task task = tasks.get(taskId - 1);
        if (task.getStatus()) {
            throw new AmiyaException("you have already marked this task.");
        }
        task.mark();
        assert task.getStatus() : "Task should be marked as done";
    }

    /**
     * Unmarks a task, setting it as incomplete.
     *
     * @param taskId The 1-based index of the task to unmark.
     * @throws AmiyaException If the task list is empty or the task ID is invalid.
     */
    public void unmarkTask(int taskId) throws AmiyaException {
        assert taskId > 0 : "Task ID must be greater than 0";
        if (tasks.isEmpty()) {
            throw new AmiyaException("there are no tasks to remove.");
        }

        if (taskId - 1 < 0 || taskId - 1 >= tasks.size()) {
            throw new AmiyaException("this is an invalid task number.");
        }
        Task task = tasks.get(taskId - 1);
        if (!task.getStatus()) {
            throw new AmiyaException("this task is already unmarked.");
        }
        task.unmark();
        assert !task.getStatus() : "Task should be unmarked after unmarking";
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The 0-based index of the task.
     * @return The corresponding task.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index must be valid";
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The total count of tasks.
     */
    public int size() {
        assert tasks != null : "Task list should not be null";
        return tasks.size();
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        assert tasks != null : "Task list should not be null";
        return tasks;
    }
}
