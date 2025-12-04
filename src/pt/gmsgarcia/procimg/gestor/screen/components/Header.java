package pt.gmsgarcia.procimg.gestor.screen.components;

import pt.gmsgarcia.procimg.gestor.screen.AppScreen;

import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
    public Header(String panelName, String backPanel) {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // padding

        JButton back = new JButton("Back");
        back.addActionListener(e -> AppScreen.showPanel(backPanel));
        this.add(back, BorderLayout.WEST);

        JLabel title = new JLabel(panelName, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title);
    }
}
