package pt.gmsgarcia.procimg.gestor.task;

import java.util.UUID;

public class TaskItem {
    private final UUID uuid;
    private String title;
    private String description;
    private TaskStatus status;

    public TaskItem(UUID uuid, String title, String description, TaskStatus status) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.status = status;
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

    public TaskStatus getStatus() {
        return this.status;
    }
}
