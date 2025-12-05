package pt.gmsgarcia.pi.gestor;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMTGitHubDarkIJTheme;
import pt.gmsgarcia.pi.gestor.screen.AppScreen;
import pt.gmsgarcia.pi.gestor.task.Task;
import pt.gmsgarcia.pi.gestor.task.TaskItem;
import pt.gmsgarcia.pi.gestor.task.TaskManager;
import pt.gmsgarcia.pi.gestor.task.TaskStatus;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static UUID sampleUUID = UUID.fromString("aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa");

    public static void main(String[] args) {
        TaskManager.init();
        Scanner sc = new Scanner(System.in);

        try {
            UIManager.setLookAndFeel(new FlatMTGitHubDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }

        try {
            InputStream is = Main.class.getResourceAsStream("/fonts/Satoshi-Variable.ttf");
            Font satoshi = Font.createFont(Font.TRUETYPE_FONT, is);

            satoshi = satoshi.deriveFont(Font.PLAIN, 16f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(satoshi);

            UIManager.put("Label.font", satoshi.deriveFont(16f));
            UIManager.put("Button.font", satoshi.deriveFont(14f));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AppScreen("todo app");
    }

    public static void displayTaskList(Scanner sc) {
        while (true) {
            ArrayList<Task> tasks = TaskManager.getTasks(sampleUUID);
            if (tasks == null) {
                System.out.println("Não existem tarefas.");
                return;
            }

            System.out.println("\nNr : Titulo : Descriçao : Deadline : Prioridade");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println(i+1 + ". " + t.getTitle() + " : " + t.getDescription() + " : " + t.getDeadline() + " : " + t.getPriority() + t.getStatus());
            }


            System.out.println("\nSelecione uma tarefa (0 para voltar ao menu inicial):");
            int opt = sc.nextInt();
            if (opt == 0) {
                break;
            }

            if (opt > tasks.size() || opt < 0) {
                System.out.println("Opção invalida!");
            } else {
                displayTaskDetails(tasks.get(opt-1), sc);
            }
        }
    }

    public static void displayTaskDetails(Task task, Scanner sc) {
        boolean displaying = true;
        while (displaying) {
            clearTerminal();

            System.out.println(task.getUUID() + "- " + task.getTitle());
            System.out.println("Deadline: " + task.getDeadline() + " | Status: " + task.getStatus() + " | Prioridade: " + task.getPriority());
            System.out.println("Descriçao: " + task.getDescription());

            System.out.println("\nSelecione uma operaçao (0 para voltar ao menu inicial):");
            System.out.println("1. Concluir tarefa");
            System.out.println("2. Remover tarefa");
            System.out.println("3. Concluir subtarefa");
            System.out.println("4. Remover subtarefa");

            int opt = sc.nextInt();
            if (opt == 0) {
                break;
            }

            switch (opt) {
                case 1:
                    task.setStatus(TaskStatus.FINISHED);
                    break;
                case 2:
                    displaying = false;
                    TaskManager.removeTask(sampleUUID, task);
                    break;
                case 3:
                    System.out.println("TODO");
                    break;
                case 4:
                    System.out.println("TODO");
                    break;
                default:
                    System.out.println("Opçao invalida!");
                    break;
            }
        }
    }

    public static void createNewTask() {
        Scanner sc = new Scanner(System.in);

        String title;
        String description;
        int priority;
        Instant deadline;

        clearTerminal();
        System.out.println("Nova Tarefa");

        System.out.println("\nInsira o titulo da tarefa:");
        title = sc.nextLine();

        System.out.println("\nInsira a descriçao da tarefa:");
        description = sc.nextLine();

        try  {
            System.out.println("\nInsira a prioridade da tarefa? (numero inteiro)");
            priority = Integer.parseInt(sc.nextLine());
        } catch (Exception ignored) {
            System.out.println("Prioridade inválida. Prioridade vai ser definida para 1.");
            priority = 1;
        }

        deadline = Instant.now();

        // subtarefas
        System.out.println("\n\nSubtarefas");
        System.out.println("É necessário criar pelo menos 1 subtarefa!");

        ArrayList<TaskItem> taskItems = new ArrayList<>();
        boolean done = false;

        while (!done) {
            TaskItem item = new TaskItem();

            System.out.println("\nInsira o titulo da subtarefa:");
            item.setTitle(sc.nextLine());

            System.out.println("\nInsira a descrição da subtarefa:");
            item.setDescription(sc.nextLine());

            taskItems.add(item);

            while (true) {
                System.out.println("\nAdicionar mais subtarefas? (y/n)");
                String opt = sc.nextLine();
                if (opt.equals("y") || opt.equals("n")) {
                    done = opt.equals("n");
                    break;
                }
                System.out.println("Opção inválida!");
            }
        }

        TaskManager.createTask(sampleUUID, title, description, priority, deadline, taskItems);
        TaskManager.saveTasks();
    }

    public static void clearTerminal() {
        // ANSI codes won't do it cuz IntelliJ doesn't support 'em :/
        //System.out.print("\033[H\033[2J");
        //System.out.flush();

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}