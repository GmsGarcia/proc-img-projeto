package pt.gmsgarcia.procimg.gestor.llm;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Engine responsável por interagir com um modelo LLM através de pedidos HTTP.
 * Permite enviar prompts e obter respostas, com opção de contornar problemas de certificados SSL.
 *
 * TODO - documentar melhor responsabilidades gerais.
 */
public class LLMInteractionEngine {

    String url;    // TODO - documentar
    String apiKey; // TODO - documentar
    String model;  // TODO - documentar
    boolean useHack; // TODO - documentar

    /**
     * Construtor principal da engine.
     *
     * @param url URL do endpoint da API LLM. TODO - documentar
     * @param apiKey Chave de autenticação utilizada nos pedidos. TODO - documentar
     * @param model Modelo a ser utilizado nas chamadas. TODO - documentar
     * @param useHack Indica se deve ser usado o hack para ignorar certificados SSL. TODO - documentar
     */
    public LLMInteractionEngine(String url, String apiKey, String model, boolean useHack) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
        this.useHack = useHack;
    }

    /**
     * Construtor alternativo que não utiliza o hack SSL.
     *
     * @param url URL do endpoint da API. TODO - documentar
     * @param apiKey Chave de autenticação. TODO - documentar
     * @param model Nome do modelo a utilizar. TODO - documentar
     */
    LLMInteractionEngine(String url, String apiKey, String model) {
        this.url = url;
        this.apiKey = apiKey;
        this.model = model;
        this.useHack = false;
    }

    /**
     * Constrói manualmente o JSON enviado no corpo do pedido.
     *
     * @param model Nome do modelo a utilizar. TODO - documentar
     * @param prompt Conteúdo textual enviado ao LLM. TODO - documentar
     * @return String JSON formatada contendo modelo e prompt.
     */
    String buildJSON(String model, String prompt) {
        String json = "";
        json += "{";
        json += "\"" + "model" + "\": " + "\"" + model + "\",";
        json += "\"" + "prompt" + "\" :" + "\"" + prompt + "\"";
        json += "}";
        return json;
    }

    /**
     * Envia um prompt ao LLM.
     * Caso useHack seja true, utiliza o método alternativo que ignora certificados SSL.
     *
     * @param prompt Texto enviado ao modelo.
     * @return Resposta devolvida pelo servidor.
     * @throws IOException Em caso de erro de I/O.
     * @throws InterruptedException Se o envio do pedido for interrompido.
     * @throws NoSuchAlgorithmException Caso o algoritmo SSL não exista. TODO - documentar
     * @throws KeyManagementException Caso ocorra erro na gestão de chaves SSL. TODO - documentar
     */
    String sendPrompt(String prompt) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {
        if (useHack) {
            return sendPrompt_Hack(prompt);
        }
        HttpClient client = HttpClient.newHttpClient();
        String json = buildJSON(model, prompt);
        return sendRequestToClientAndGetReply(client, url, apiKey, json);
    }

    /**
     * Método alternativo para enviar um prompt ignorando verificações de certificados SSL.
     *
     * @param prompt Prompt a enviar ao LLM.
     * @return Resposta devolvida pelo servidor.
     * @throws IOException Em caso de erro de comunicação.
     * @throws InterruptedException Se o envio for interrompido.
     * @throws NoSuchAlgorithmException Se o algoritmo TLS não estiver disponível.
     * @throws KeyManagementException Em caso de erro ao inicializar o contexto SSL. TODO - documentar
     */
    String sendPrompt_Hack(String prompt) throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

        // *************
        // hack por causa dos certificados
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, new TrustManager[]{ new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] c, String a) {}
            public void checkServerTrusted(X509Certificate[] c, String a) {}
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
        }}, new SecureRandom());

        HttpClient insecureClient = HttpClient.newBuilder().sslContext(sc).build();
        // fim do hack
        // *************

        String json = buildJSON(model, prompt);

        return sendRequestToClientAndGetReply(insecureClient, url, apiKey, json);
    }

    /**
     * Envia um pedido HTTP ao servidor e devolve a resposta.
     *
     * @param client Cliente HTTP configurado. TODO - documentar
     * @param url URL do endpoint da API. TODO - documentar
     * @param apiKey Chave de autenticação para o cabeçalho Authorization.
     * @param json Corpo do pedido em formato JSON.
     * @return Corpo da resposta devolvida pela API.
     * @throws IOException Em caso de falha de I/O.
     * @throws InterruptedException Caso o pedido seja interrompido.
     */
    String sendRequestToClientAndGetReply(HttpClient client, String url, String apiKey, String json) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        return resp.body();
    }
}