package pt.gmsgarcia.pi.gestor.screen.panels;

import pt.gmsgarcia.pi.gestor.screen.AppScreen;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Satoshi Variable Bold", Font.BOLD, 24));
        this.add(titleLabel, gbc);

        JLabel welcomeLabel = new JLabel("Welcome!");
        this.add(welcomeLabel, gbc);

        JButton tasksButton = new JButton("Task List");
        tasksButton.addActionListener(e -> AppScreen.showPanel("Tasks"));
        this.add(tasksButton, gbc);

        JButton aiButton = new JButton("AI Agent");
        aiButton.addActionListener(e -> AppScreen.showPanel("Agent"));
        this.add(aiButton, gbc);
    }
}
