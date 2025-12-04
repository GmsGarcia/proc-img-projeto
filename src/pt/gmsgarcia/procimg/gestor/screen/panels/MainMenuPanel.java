package pt.gmsgarcia.procimg.gestor.screen.panels;

import pt.gmsgarcia.procimg.gestor.screen.AppScreen;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    public MainMenuPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Aria",Font.BOLD, 24));
        this.add(titleLabel, gbc);

        JLabel welcomeLabel = new JLabel("Bem vindo!");
        this.add(welcomeLabel, gbc);

        JButton tasksButton = new JButton("Consultar tarefas");
        tasksButton.addActionListener(e -> AppScreen.showPanel("Tasks"));
        this.add(tasksButton, gbc);
        // opens TasksPanel

        JButton aiButton = new JButton("Consultar agente");
        aiButton.addActionListener(e -> AppScreen.showPanel("Agent"));
        this.add(aiButton, gbc);
        // opens AgentPanel
    }
}
