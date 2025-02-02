package amiya.task;

import amiya.exception.AmiyaException;
import amiya.ui.UI;

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
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
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
        if (tasks.isEmpty()) {
            throw new AmiyaException("there are no tasks to remove.");
        }
        if (taskId <= 0 || taskId > tasks.size()) {
            throw new AmiyaException("please provide a valid task number.");
        }
        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        return task;
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Marks a task as completed.
     *
     * @param taskId The 1-based index of the task to mark.
     * @throws AmiyaException If the task list is empty or the task ID is invalid.
     */
    public void markTask(int taskId) throws AmiyaException {
        if (tasks.isEmpty()) {
            UI.showNoTasks();
        }

        if (taskId - 1 < 0 || taskId - 1 >= tasks.size()) {
            throw new AmiyaException("this is an invalid task number.");
        }
        Task task = tasks.get(taskId - 1);
        if (task.getStatus()) {
            throw new AmiyaException("you have already marked this task.");
        }
        task.mark();
    }

    /**
     * Unmarks a task, setting it as incomplete.
     *
     * @param taskId The 1-based index of the task to unmark.
     * @throws AmiyaException If the task list is empty or the task ID is invalid.
     */
    public void unmarkTask(int taskId) throws AmiyaException {
        if (tasks.isEmpty()) {
            UI.showNoTasks();
        }

        if (taskId - 1 < 0 || taskId - 1 >= tasks.size()) {
            throw new AmiyaException("this is an invalid task number.");
        }
        Task task = tasks.get(taskId - 1);
        if (!task.getStatus()) {
            throw new AmiyaException("this task is already unmarked.");
        }
        task.unmark();
    }

    /**
     * Retrieves a task from the list by its index.
     *
     * @param index The 0-based index of the task.
     * @return The corresponding task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The total count of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
}
