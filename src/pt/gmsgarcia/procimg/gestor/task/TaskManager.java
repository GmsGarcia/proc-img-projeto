package pt.gmsgarcia.procimg.gestor.task;

import pt.gmsgarcia.procimg.gestor.storage.StorageManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TaskManager {
    private static HashMap<UUID, ArrayList<Task>> tasks = new HashMap<>();

    public static void init() {
        tasks = StorageManager.loadTasks();
    }

    public static ArrayList<Task> getTasks(UUID user) {
        return tasks.get(user);
    }

    public static Task createTask(UUID user, String title, String description, int priority, Instant deadline, ArrayList<TaskItem> items) {
        Task task = new Task(UUID.randomUUID(), title, description, priority, deadline, TaskStatus.UNFINISHED, items);

        if (!tasks.containsKey(user)) {
            tasks.put(user, new ArrayList<>());
        }

        tasks.get(user).add(task);

        return task;
    }

    public static void removeTask(UUID user, Task task) {
        tasks.get(user).remove(task);
    }

    public static void saveTasks() {
        StorageManager.saveTasks(tasks);
    }
}