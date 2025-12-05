package pt.gmsgarcia.pi.gestor.screen.components;

import pt.gmsgarcia.pi.gestor.screen.AppScreen;
import pt.gmsgarcia.pi.gestor.task.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskListItem extends JPanel {
    public TaskListItem(Task task) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 85));
        this.setMinimumSize(new Dimension(0, 85));
        this.setPreferredSize(new Dimension(0, 85));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setBorder(new EmptyBorder(0, 15, 0, 15));

        JPanel taskInfo = new JPanel();
        taskInfo.setLayout(new BoxLayout(taskInfo, BoxLayout.Y_AXIS));
        taskInfo.setOpaque(false);

        JLabel title = new JLabel(task.getTitle());
        title.setFont(new Font("Satoshi Variable Bold", Font.BOLD, 18));
        taskInfo.add(title);

        JLabel deadline = new JLabel("<html><b>Deadline:</b> " + task.getDeadlineString() + "</html>");
        deadline.setFont(new Font("Satoshi Variable Bold", Font.PLAIN, 14));
        taskInfo.add(deadline);

        JLabel priority = new JLabel("Prioridade: " + task.getPriority());
        priority.setFont(new Font("Satoshi Variable Bold", Font.PLAIN, 14));
        taskInfo.add(priority);


        this.add(taskInfo);
        this.add(Box.createHorizontalGlue());

        JButton details = new JButton("+");
        details.setFont(new Font("Aria", Font.BOLD, 18));
        details.addActionListener(e -> AppScreen.showTaskPanel(task));
        this.add(details);
    }
}
