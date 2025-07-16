package rosa.ribeiro.jonas;

import rosa.ribeiro.jonas.config.ConfigLoader;
import rosa.ribeiro.jonas.dto.QuoteDto;
import rosa.ribeiro.jonas.persistence.QuoteRepository;
import rosa.ribeiro.jonas.servico.QuoteServico;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = ConfigLoader.getApiKey();
        QuoteServico quoteServico = new QuoteServico(apiKey);
        List<QuoteDto> quotes = quoteServico.fetchRandomQuotes();
        QuoteRepository quoteRepository = new QuoteRepository();
        quoteRepository.saveQuotes(quotes);
        System.out.println(quotes);
    }
}