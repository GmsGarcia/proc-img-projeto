package pt.gmsgarcia.procimg.gestor;

import pt.gmsgarcia.procimg.gestor.llm.LLMInteractionEngine;
import pt.gmsgarcia.procimg.gestor.llm.LLMRockPaperScsissors;

public class Main {
    static String apiKey = "sk-L88zR6OTZ1_5PN_cdJY3ag";
    static String url = "https://modelos.ai.ulusofona.pt/v1/completions";
    static String model = "gpt-4-turbo";
    static boolean useHack = false;

    public static void main(String[] args) throws Exception {
        LLMInteractionEngine engine = new LLMInteractionEngine(url, apiKey, model, useHack);
        LLMRockPaperScsissors game = new LLMRockPaperScsissors(engine);
        game.execute();
    }
}