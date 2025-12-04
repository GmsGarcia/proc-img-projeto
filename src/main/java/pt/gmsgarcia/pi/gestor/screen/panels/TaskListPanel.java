package pt.gmsgarcia.pi.gestor.screen.panels;

import pt.gmsgarcia.pi.gestor.Main;
import pt.gmsgarcia.pi.gestor.screen.components.Header;
import pt.gmsgarcia.pi.gestor.screen.components.TaskListItem;
import pt.gmsgarcia.pi.gestor.task.Task;
import pt.gmsgarcia.pi.gestor.task.TaskManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TaskListPanel extends JPanel {
    private final JPanel tasksList;

    public TaskListPanel() {
        this.setLayout(new BorderLayout());
        this.add(new Header("Tasks List", "MainMenu"), BorderLayout.NORTH);

        tasksList = new JPanel();
        tasksList.setLayout(new BoxLayout(tasksList, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(tasksList);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setContent();
    }

    public void setContent() {
        ArrayList<Task> tasks = TaskManager.getTasks(Main.sampleUUID);

        tasksList.removeAll();

        if (tasks.isEmpty()) {
            tasksList.add(new JLabel("No tasks found.", SwingConstants.CENTER));
        } else {
            for (Task task : tasks) {
                tasksList.add(new TaskListItem(task));
            }
        }

        tasksList.revalidate();
        tasksList.repaint();
    }
}
