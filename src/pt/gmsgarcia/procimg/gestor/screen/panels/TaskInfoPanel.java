package pt.gmsgarcia.procimg.gestor.screen.panels;

import pt.gmsgarcia.procimg.gestor.screen.components.Header;
import pt.gmsgarcia.procimg.gestor.task.Task;

import javax.swing.*;
import java.awt.*;

public class TaskInfoPanel extends JPanel {

    public TaskInfoPanel(Task task) {
        this.setLayout(new BorderLayout());
        this.add(new Header(task.getTitle(), "Tasks"), BorderLayout.NORTH);

        JPanel content = new JPanel(); // can set layout dynamically
        content.setLayout(new BorderLayout()); // example layout
        this.add(content, BorderLayout.CENTER);

        // Example content
        content.add(new JLabel(task.getUUID().toString()));
        content.add(new JLabel(task.getDeadlineString()));
        content.add(new JLabel(String.valueOf(task.getPriority())));
    }
}
