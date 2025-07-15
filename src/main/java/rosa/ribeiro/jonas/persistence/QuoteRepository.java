package rosa.ribeiro.jonas.persistence;

import rosa.ribeiro.jonas.dto.QuoteDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class QuoteRepository {
    private final List<QuoteDto> quoteDto;

    public QuoteRepository(List<QuoteDto> quoteDto) {
        this.quoteDto = quoteDto;
    }

    public String CreateString(List<QuoteDto> quoteDto){
        return "Quote: " +
                quoteDto.get(0).getQuote() +
                ", " +
                "Author: " +
                quoteDto.get(0).getAuthor() +", " +
                "Category: " +
                quoteDto.get(0).getCategory()+"\n";

    }
    public void CreateFileTxt() throws IOException {
        String category = quoteDto.get(0).getCategory();
        String uri = "src/repository/"+category+".txt";
        Path path = Path.of(uri);

        if(Files.notExists(path)){
            Files.createFile(path);
        }
        Files.writeString(path,CreateString(quoteDto), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }

}
