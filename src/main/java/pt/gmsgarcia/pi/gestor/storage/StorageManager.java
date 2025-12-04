package pt.gmsgarcia.pi.gestor.storage;

import org.yaml.snakeyaml.Yaml;
import pt.gmsgarcia.pi.gestor.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StorageManager {
    public static HashMap<UUID, ArrayList<Task>> loadTasks()  {
        HashMap<UUID, ArrayList<Task>> all = new HashMap<>();

        File dir = new File("tasks");
        if (!dir.exists()) return all;

        File[] files = dir.listFiles((d, name) -> name.endsWith(".yaml"));
        if (files == null) return all;

        for (File f : files) {
            String name = f.getName().replace(".yaml", "");
            UUID userId = UUID.fromString(name);

            try {
                ArrayList<Task> tasks = loadUserTasks(userId);
                all.put(userId, tasks);
            } catch (Exception e) {
                System.out.println("Failed to load tasks for user with UUID " + userId);
            }
        }

        return all;
    }

    public static void saveTasks(HashMap<UUID, ArrayList<Task>> tasks) {
        for (Map.Entry<UUID, ArrayList<Task>> entry : tasks.entrySet()) {
            try {
                saveUserTasks(entry.getKey(), entry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Task> loadUserTasks(UUID userId) throws IOException {
        File file = new File("tasks", userId.toString() + ".yaml");
        if (!file.exists()) return new ArrayList<>();

        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;

        try (FileInputStream fis = new FileInputStream(file)) {
            yamlMap = yaml.load(fis);
        }

        ArrayList<Task> tasks = new ArrayList<>();

        List<Map<String, Object>> rawTasks = (List<Map<String, Object>>) yamlMap.get("tasks");
        if (rawTasks != null) {
            for (Map<String, Object> taskMap : rawTasks) {
                tasks.add(Task.fromMap(taskMap));
            }
        }

        return tasks;
    }

    public static void saveUserTasks(UUID userId, ArrayList<Task> tasks) throws IOException {
        Map<String, Object> yamlMap = new LinkedHashMap<>();

        List<Map<String, Object>> converted = new ArrayList<>();
        for (Task t : tasks) {
            converted.add(t.toMap());
        }

        // put tasks list directly — no extra map needed
        yamlMap.put("tasks", converted);

        // Create folder if missing
        File dir = new File("tasks");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // File per user
        File file = new File(dir, userId.toString() + ".yaml");

        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(yamlMap, writer);
        }
    }
}
