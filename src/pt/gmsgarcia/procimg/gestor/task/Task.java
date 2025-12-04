package pt.gmsgarcia.procimg.gestor.task;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public String getDeadlineString() {
        ZonedDateTime dateTime = this.deadline.atZone(ZoneId.systemDefault());
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("uuid", uuid.toString());
        m.put("title", title);
        m.put("description", description);
        m.put("priority", priority);
        m.put("deadline", deadline != null ? deadline.toString() : null);
        m.put("status", status.toString());

        List<Map<String, Object>> items = new ArrayList<>();
        for (TaskItem ti : taskItems) {
            items.add(ti.toMap());
        }
        m.put("taskItems", items);

        return m;
    }

    public static Task fromMap(Map<String, Object> m) {
        UUID uuid = UUID.fromString((String) m.get("uuid"));
        String title = (String) m.get("title");
        String description = (String) m.get("description");
        int priority = (int) m.get("priority");

        Instant deadline = null;
        if (m.get("deadline") != null) {
            deadline = Instant.parse((String) m.get("deadline"));
        }

        TaskStatus status = TaskStatus.valueOf((String) m.get("status"));

        ArrayList<TaskItem> items = new ArrayList<>();
        List<Map<String, Object>> rawItems = (List<Map<String, Object>>) m.get("taskItems");
        if (rawItems != null) {
            for (Map<String, Object> itemMap : rawItems) {
                items.add(TaskItem.fromMap(itemMap));
            }
        }

        return new Task(uuid, title, description, priority, deadline, status, items);
    }
}