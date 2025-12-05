package pt.gmsgarcia.pi.gestor.screen.panels;

import pt.gmsgarcia.pi.gestor.Main;
import pt.gmsgarcia.pi.gestor.screen.AppScreen;
import pt.gmsgarcia.pi.gestor.screen.components.Header;
import pt.gmsgarcia.pi.gestor.task.Task;
import pt.gmsgarcia.pi.gestor.task.TaskManager;
import pt.gmsgarcia.pi.gestor.task.TaskStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskInfoPanel extends JPanel {

    public TaskInfoPanel(Task task) {
        this.setLayout(new BorderLayout());
        this.add(new Header(task.getTitle(), "Tasks"), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS)); // example layout
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.add(content, BorderLayout.CENTER);

        content.add(new JLabel("UUID: " + task.getUUID().toString()));
        content.add(new JLabel("Status: " + task.getStatus()));
        content.add(new JLabel("Deadline: " + task.getDeadlineString()));
        content.add(new JLabel("Priority: " + task.getPriority()));
        content.add(new JLabel("Description: " + task.getDescription()));

        JButton done = new JButton("Mark as Done");
        done.addActionListener(e -> {
            task.setStatus(TaskStatus.FINISHED);
            AppScreen.showTaskPanel(task);
        });
        content.add(done);

        JButton remove = new JButton("Remove task");
        remove.addActionListener(e -> {
            TaskManager.removeTask(Main.sampleUUID, task);
            AppScreen.showPanel("Tasks");
        });
        content.add(remove);
    }
}
