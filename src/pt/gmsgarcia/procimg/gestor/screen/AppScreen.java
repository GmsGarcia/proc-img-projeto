package pt.gmsgarcia.procimg.gestor.screen;

import pt.gmsgarcia.procimg.gestor.screen.panels.AgentPanel;
import pt.gmsgarcia.procimg.gestor.screen.panels.MainMenuPanel;
import pt.gmsgarcia.procimg.gestor.screen.panels.TaskListPanel;
import pt.gmsgarcia.procimg.gestor.screen.panels.TaskInfoPanel;
import pt.gmsgarcia.procimg.gestor.task.Task;

import javax.swing.*;
import java.awt.*;

public class AppScreen {
    static String title;
    static JFrame frame;
    static JPanel content;

    public AppScreen(String title) {
        AppScreen.title = title;
        init();
    }

    private static void init() {
        frame = new JFrame(title);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        frame.setLocationRelativeTo(null);

        content = new JPanel(new CardLayout());

        // main menu
        MainMenuPanel main = new MainMenuPanel();
        TaskListPanel tasks = new TaskListPanel();
        AgentPanel agent = new AgentPanel();

        content.add(main, "MainMenu");
        content.add(tasks, "Tasks");
        content.add(agent, "Agent");

        frame.add(content);

        showPanel("MainMenu");

        frame.setVisible(true);
    }

    public static void showPanel(String name) {
        CardLayout cl = (CardLayout) content.getLayout();
        cl.show(content, name);
    }

    public static void showTaskPanel(Task task) {
        for (Component comp : content.getComponents()) {
            if (comp instanceof TaskInfoPanel) {
                content.remove(comp);
            }
        }

        TaskInfoPanel detailPanel = new TaskInfoPanel(task);
        content.add(detailPanel, "TaskDetail");

        showPanel("TaskDetail");
    }
}