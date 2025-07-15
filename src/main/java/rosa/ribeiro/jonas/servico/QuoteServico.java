package rosa.ribeiro.jonas.servico;

import com.fasterxml.jackson.databind.ObjectMapper;
import rosa.ribeiro.jonas.dto.QuoteDto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class QuoteServico {
    private static final String API_URL = "https://api.api-ninjas.com/v1/quotes";
    private final HttpClient client;
    private final String apiKey;
    private final ObjectMapper objectMapper;


    public QuoteServico(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<QuoteDto> fetchRandomQuotes() throws Exception {
        HttpResponse<String> httpResponse;
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL))
                    .GET()
                    .header("X-Api-Key", this.apiKey)
                    .build();
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() == 200) {
                return Arrays.asList(objectMapper.readValue(httpResponse.body(), QuoteDto[].class));
            } else {
                String errorMessage = "Falha ao buscar citações da API. Código HTTP: " + httpResponse.statusCode() + ", Resposta: " + httpResponse.body();
                System.err.println(errorMessage);
                throw new RuntimeException(errorMessage);
            }

        } catch (RuntimeException | InterruptedException | IOException | URISyntaxException e) {
            throw new Exception(e);
        }


    }
}
