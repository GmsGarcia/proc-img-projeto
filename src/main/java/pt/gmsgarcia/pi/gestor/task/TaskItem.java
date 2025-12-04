package pt.gmsgarcia.pi.gestor.task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class TaskItem {
    private final UUID uuid;
    private String title;
    private String description;
    private TaskStatus status;

    public TaskItem() {
        this.uuid = UUID.randomUUID();
        this.status = TaskStatus.UNFINISHED;
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("uuid", uuid.toString());
        m.put("title", title);
        m.put("description", description);
        m.put("status", status.toString());
        return m;
    }

    public static TaskItem fromMap(Map<String, Object> m) {
        UUID uuid = UUID.fromString((String) m.get("uuid"));
        String title = (String) m.get("title");
        String description = (String) m.get("description");
        TaskStatus status = TaskStatus.valueOf((String) m.get("status"));

        return new TaskItem(uuid, title, description, status);
    }
}