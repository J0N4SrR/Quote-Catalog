package rosa.ribeiro.jonas.persistence;

import rosa.ribeiro.jonas.dto.QuoteDto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class QuoteRepository {
    String REPO_URL = "src/repository/";

    public QuoteRepository() {
        try{
            Files.createDirectories(Path.of(REPO_URL));
        }catch (IOException e){
            System.err.println("Falha ao criar o diretório base para as citações: " + REPO_URL + " - " + e.getMessage());
            throw new RuntimeException("Não foi possível preparar o diretório de armazenamento de citações.", e);
        }
    }

    public String createString(List<QuoteDto> quoteDto){
        return "Quote: " +
                quoteDto.get(0).getQuote() +
                ", " +
                "Author: " +
                quoteDto.get(0).getAuthor() +", " +
                "Category: " +
                quoteDto.get(0).getCategory()+"\n";
    }

    public boolean fileExists(Path path){
        return Files.exists(path);
    }

    private Path createPath(List<QuoteDto> quoteDto){
        return Path.of(REPO_URL + quoteDto.get(0).getCategory() + ".txt");
    }

    private Path createPath(String category){
        return Path.of(REPO_URL + category + ".txt");
    }

    public void createFile(Path path, String txt) throws IOException {
        Files.writeString(path, txt, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void saveQuoteTxt(List<QuoteDto> quoteDto) throws IOException {
        createFile(createPath(quoteDto),createString(quoteDto));
    }

    public List<String> readFileByCategory(String c) throws IOException {
        String category = c.toLowerCase();
        if(fileExists(createPath(category))){
           return Files.readAllLines(createPath(category));
        }
        System.out.println("Categoria: " + category + "' não encontrada.");
        return List.of();
    }





}
