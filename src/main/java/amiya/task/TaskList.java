package amiya.task;

import amiya.exception.AmiyaException;
import amiya.ui.UI;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

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

    public void clearTasks() { tasks.clear(); }

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

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
