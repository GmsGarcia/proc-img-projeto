package pt.gmsgarcia.procimg.gestor.storage;

import pt.gmsgarcia.procimg.gestor.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageManager {
    public static HashMap<UUID, ArrayList<Task>> loadTasks() {
        return new HashMap<>();
    }

    public static void saveTasks(HashMap<UUID, ArrayList<Task>> tasks) {
        for (Map.Entry<UUID, ArrayList<Task>> set : tasks.entrySet()) {
            if (set.getValue() == null) continue;

            UUID user = set.getKey();
            for (Task t : set.getValue()) {
                saveTask(user, t);
            }
        }
    }

    public static void saveTask(UUID user, Task tasks) {
        // save task to storage...
    }
}
