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
        scrollPane.setBorder(new EmptyBorder(0, 0, 50, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton create = new JButton("Add new task");

        bottom.add(create);
        this.add(bottom, BorderLayout.SOUTH);
        this.setContent();
    }

    public void setContent() {
        ArrayList<Task> tasks = TaskManager.getTasks(Main.sampleUUID);

        tasksList.removeAll();

        if (tasks.isEmpty()) {
            JLabel empty = new JLabel("No tasks found.");
            empty.setAlignmentX(CENTER_ALIGNMENT);
            empty.setBorder(new EmptyBorder(20, 15, 20, 15));

            tasksList.add(empty);
        } else {
            for (Task task : tasks) {
                tasksList.add(new TaskListItem(task));
            }
        }

        tasksList.revalidate();
        tasksList.repaint();
    }
}
