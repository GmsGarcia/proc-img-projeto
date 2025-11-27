package pt.gmsgarcia.procimg.gestor.task;

import pt.gmsgarcia.procimg.gestor.storage.StorageManager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TaskManager {
    private final HashMap<UUID, ArrayList<Task>> tasks;

    public TaskManager() {
        this.tasks = StorageManager.loadTasks();
    }

    public Task createTask(UUID user, String title, String description, int priority, Instant deadline) {
        Task task = new Task(UUID.randomUUID(), title, description, priority, deadline, TaskStatus.CREATED, new ArrayList<>());

        if (!this.tasks.containsKey(user)) {
            this.tasks.put(user, new ArrayList<>());
        }

        this.tasks.get(user).add(task);

        return task;
    }

    public void saveTasks() {
        StorageManager.saveTasks(tasks);
    }
}
