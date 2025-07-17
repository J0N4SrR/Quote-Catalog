package rosa.ribeiro.jonas.persistence;

import rosa.ribeiro.jonas.dto.QuoteDto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class QuoteRepository {
    private static final String REPO_URL = "src/repository/";

    public QuoteRepository() {
        try{
            Files.createDirectories(Path.of(REPO_URL));
        }catch (IOException e){
            System.err.println("Falha ao criar o diretório base para as citações: " + REPO_URL + " - " + e.getMessage());
            throw new RuntimeException("Não foi possível preparar o diretório de armazenamento de citações.", e);
        }
    }

    private String formatQuote(QuoteDto quoteDto){
        return "Quote: " +
                quoteDto.getQuote() +
                ", " +
                "Author: " +
                quoteDto.getAuthor() +", " +
                "Category: " +
                quoteDto.getCategory()+"\n";
    }

    public boolean fileExists(Path path){
        return Files.exists(path);
    }

    private Path createPath(String c){
        String category = c.toLowerCase();
        return Path.of(REPO_URL + category + ".txt");
    }

    public void createFile(Path path, String txt) throws IOException {
        Files.writeString(path, txt, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void saveQuotes(List<QuoteDto> quoteDto) throws IOException {
        if(quoteDto == null || quoteDto.isEmpty()){
            System.out.println("Não há citações para salvar!");
            return;
        }
        int cont = 0;
        File dir = new File(REPO_URL);
        File[] files = dir.listFiles();
        if(files != null){
            for(File d : files){
                try(FileReader fr = new FileReader(d);
                    BufferedReader br = new BufferedReader(fr)){
                    String linha;
                    while((linha = br.readLine()) != null){
                        for (QuoteDto quote : quoteDto){
                            if(linha.equalsIgnoreCase(formatQuote(quote))){
                            cont++;
                            }
                        }

                    }
                    if(cont == 0){
                        for (QuoteDto quote : quoteDto){
                            createFile(createPath(quote.getCategory()), formatQuote(quote));
                        }
                    }
                }
            }
        }




    }

    public List<String> readFileByCategory(String c) throws IOException {
        String category = c.toLowerCase();
        if(fileExists(createPath(category))){
           return Files.readAllLines(createPath(category));
        }
        System.out.println("Categoria: " + category + " não encontrada.");
        return List.of();
    }

    public List<String> findQuotesByAuthor(String a) throws IOException {
        String author = a.toLowerCase();
        List<String> authorList = new java.util.ArrayList<>();
        File dir = new File(REPO_URL);
        File[] files = dir.listFiles();
        if(files != null){
            for(File d : files){
                 try(FileReader fr = new FileReader(d);
                     BufferedReader br = new BufferedReader(fr)){
                         String linha;
                         while((linha = br.readLine()) != null){
                             String linhaLower = linha.toLowerCase();
                             String[] linhaSplit = linhaLower.split(", ");
                             if(linhaSplit.length > 1){
                                 if(linhaSplit[1].contains(author)){
                                  authorList.add(linha);
                                 }
                             }
                         }
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }
            }
        }
        return authorList;
    }
}
