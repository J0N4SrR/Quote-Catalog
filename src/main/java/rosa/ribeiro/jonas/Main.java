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
        QuoteRepository quoteRepository = new QuoteRepository();
//        for(int i = 0; i < 10; i++){
//            List<QuoteDto> quotes = quoteServico.fetchRandomQuotes();
//            quoteRepository.saveQuotes(quotes);
//            System.out.println(quotes);
//        }
        System.out.println(quoteRepository.readFileByCategory("family"));
    }
}