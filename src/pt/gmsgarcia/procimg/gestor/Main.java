package pt.gmsgarcia.procimg.gestor;

import pt.gmsgarcia.procimg.gestor.llm.LLMInteractionEngine;
import pt.gmsgarcia.procimg.gestor.llm.LLMRockPaperScsissors;
import pt.gmsgarcia.procimg.gestor.task.Task;
import pt.gmsgarcia.procimg.gestor.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static String apiKey = "sk-L88zR6OTZ1_5PN_cdJY3ag";
    static String url = "https://modelos.ai.ulusofona.pt/v1/completions";
    static String model = "gpt-4-turbo";
    static boolean useHack = false;

    static TaskManager taskManager;

    /*public static void main(String[] args) throws Exception {
        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);
        LLMRockPaperScsissors game = new LLMRockPaperScsissors(engine);
        game.execute();
    }*/

    public static void main(String[] args) {
        taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        int opt = -1;

        while (opt == -1) {
            System.out.println("Bem vindo!\n O que pretende fazer?\n1. Consultar tarefas\n2. Criar uma nova tarefa\n\n0. Sair");
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    displayTasks();
                case 2:
                    createNewTask();
                case 0:
                    break;
                default:
                    opt = -1;
            }
        }
    }

    public static void displayTasks() {
    }

    public static void createNewTask() {

    }
}