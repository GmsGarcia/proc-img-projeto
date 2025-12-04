package pt.gmsgarcia.pi.gestor.llm;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class LLMRockPaperScsissors {
    private final String CHOICE_ROCK = "Pedra";
    private final String CHOICE_PAPER = "Papel";
    private final String CHOICE_SCISSORS = "Tesoura";

    private final int MIN_OPTION = 1;
    private final int MAX_OPTION = 3;
    private final int MAX_ATTEMPTS = 3;
    private final String PROMPT_CHOICE = "Escolhe um destes 3 números: 1, 2 ou 3. Devolve apenas o número, sem pontuação.";
    private final String PROMPT_WINNER = "Jogo de Pedra/Papel/Tesoura: o jogador 1 (Humano) escolheu %s enquanto que o jogador 2 (GPT-4-Turbo) escolheu %s. Quem ganhou o jogo? Responde apenas com o nome do jogador e sem pontuação.";

    LLMInteractionEngine engine;

    public LLMRockPaperScsissors(LLMInteractionEngine engine) {
        this.engine = engine;
    }

    int askUserForChoice() {

        int option;

        do {
            System.out.println("Jogador, escolha a sua opção:");
            System.out.println("1) Pedra");
            System.out.println("2) Papel");
            System.out.println("3) Tesoura");

            option = Utils.readCharFromKeyboard();
        }
        while (option < MIN_OPTION || option > MAX_OPTION);

        return MIN_OPTION;
    }

    int askLLMForChoice() throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            String jsonResponse = engine.sendPrompt(PROMPT_CHOICE);

            int option = Integer.parseInt(JSONUtils.getJsonString(jsonResponse, "text"));

            if(option >= MIN_OPTION && option <= MAX_OPTION) {
                return option;
            }
        }

        return MIN_OPTION;
    }

    String askLLMWhoWon(String userOption, String llmOption) throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {
        String prompt = String.format(PROMPT_WINNER, userOption, llmOption);
        String jsonResponse = engine.sendPrompt(prompt);
        return JSONUtils.getJsonString(jsonResponse, "text");
    }

    String translateOption(int option) {
        return switch (option) {
            case 1 -> CHOICE_ROCK;
            case 2 -> CHOICE_PAPER;
            default -> CHOICE_SCISSORS;
        };
    }

    public void execute() throws IOException, NoSuchAlgorithmException, InterruptedException, KeyManagementException {

        int userOption = askUserForChoice();

        System.out.println("<O computador/LLM está a pensar...>");
        int llmOption = askLLMForChoice();

        String translatedUserOption = translateOption(userOption);
        String translatedLLMOption = translateOption(llmOption);

        String result = askLLMWhoWon(translatedUserOption, translatedLLMOption);

        System.out.println("Jogador 1 (Humano) escolheu: " + translatedUserOption);
        System.out.println("Jogador 2 (Computador/LLM) escolheu: " + translatedLLMOption);
        System.out.println("Vencedor: " + result);
    }
}