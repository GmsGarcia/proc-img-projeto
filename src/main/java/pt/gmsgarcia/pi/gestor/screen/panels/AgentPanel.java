package pt.gmsgarcia.pi.gestor.screen.panels;

import pt.gmsgarcia.pi.gestor.screen.components.Header;

import javax.swing.*;
import java.awt.*;

public class AgentPanel extends JPanel {
    private JPanel contentPanel;

    public AgentPanel() {
        this.setLayout(new BorderLayout());
        this.add(new Header("AI Agent", "MainMenu"), BorderLayout.NORTH);

        contentPanel = new JPanel(); // can set layout dynamically
        contentPanel.setLayout(new BorderLayout()); // example layout
        this.add(contentPanel, BorderLayout.CENTER);

        // Example content
        contentPanel.add(new JLabel("Dynamic content goes here", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
