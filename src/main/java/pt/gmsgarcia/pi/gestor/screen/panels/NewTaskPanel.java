package pt.gmsgarcia.pi.gestor.screen.panels;

import pt.gmsgarcia.pi.gestor.screen.components.Header;
import raven.datetime.DatePicker;
import raven.datetime.TimePicker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewTaskPanel extends JPanel {
    public NewTaskPanel() {
        this.setLayout(new BorderLayout());
        this.add(new Header("New Task", "Tasks"), BorderLayout.NORTH);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton create = new JButton("Create Task");

        bottom.add(create);
        this.add(bottom, BorderLayout.SOUTH);
        this.setContent();
    }

    public void setContent() {
        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.Y_AXIS));

        // title
        JPanel title = new JPanel(new BorderLayout());
        title.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel tLabel = new JLabel("Title:");
        JTextField tInput = new JTextField();
        tInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        title.add(tLabel, BorderLayout.PAGE_START);
        title.add(tInput, BorderLayout.CENTER);

        // priority
        JPanel priority = new JPanel(new BorderLayout());
        priority.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel pLabel = new JLabel("Priority:");
        JTextField pInput = new JTextField();
        pInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        priority.add(pLabel, BorderLayout.PAGE_START);
        priority.add(pInput, BorderLayout.CENTER);

        JPanel deadline = new JPanel(new BorderLayout());
        deadline.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel fLabel = new JLabel("Deadline:");

        deadline.add(fLabel, BorderLayout.PAGE_START);
        deadline.add(setupDeadlinePanel(), BorderLayout.CENTER);

        // description
        JPanel description = new JPanel(new BorderLayout());
        description.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel dLabel = new JLabel("Description:");
        JTextField dInput = new JTextField();

        description.add(dLabel, BorderLayout.PAGE_START);
        description.add(dInput, BorderLayout.CENTER);

        input.add(title);
        input.add(deadline);
        input.add(priority);
        input.add(description);

        this.add(input, BorderLayout.CENTER);
    }

    private JPanel setupDeadlinePanel() {
        JPanel deadline = new JPanel();

        JFormattedTextField date = new JFormattedTextField();
        JFormattedTextField time = new JFormattedTextField();

        DatePicker dp = new DatePicker();
        dp.setEditor(date);

        TimePicker tp = new TimePicker();
        tp.setEditor(time);

        deadline.add(date);
        deadline.add(time);

        return deadline;
    }
}
