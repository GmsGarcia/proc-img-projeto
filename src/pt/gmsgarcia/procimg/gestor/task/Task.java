package pt.gmsgarcia.procimg.gestor.task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

public class Task {
    private final UUID uuid;
    private String title;
    private String description;
    private int priority;
    private Instant deadline;
    private TaskStatus status;
    private ArrayList<TaskItem> taskItems;

    public Task(UUID uuid, String title, String description, int priority, Instant deadline, TaskStatus status, ArrayList<TaskItem> taskItems) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.taskItems = taskItems;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPriority() {
        return this.priority;
    }

    public Instant getDeadline() {
        return this.deadline;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public ArrayList<TaskItem> getTaskItems() {
        return this.taskItems;
    }

    public void addTaskItem(TaskItem item) {
        this.taskItems.add(item);
    }

    public void removeTaskItem(TaskItem item) {
        this.taskItems.remove(item);
    }
}
