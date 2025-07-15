package rosa.ribeiro.jonas.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigLoader {
    private static final String CONFIG_FILE = ".env";
    private static final Properties properties = new Properties();

    static{
        try (FileInputStream input = new FileInputStream("src/main/" + CONFIG_FILE)){
            properties.load(input);
        } catch (IOException e){
            throw new RuntimeException("Falha ao inicializar a configuração da aplicação. Verifique o arquivo " + CONFIG_FILE, e);
        }
    }

    public static String getApiKey() {
        String apiKey = properties.getProperty("API_NINJAS_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("A chave 'API_NINJAS_KEY' não foi encontrada ou está vazia no arquivo " + CONFIG_FILE);
        }
        return apiKey;
    }

}
