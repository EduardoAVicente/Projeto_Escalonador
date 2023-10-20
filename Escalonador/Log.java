import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Log {

    private List<String> strings;

    public Log() {
        strings = new ArrayList<>();
    }

    public void write(String str) {
        System.out.println(str);
        strings.add(str);
    }

    public void close(String filename) {
        String originalFileName = filename;
        String fileExtension = ".txt";
        int count = 0;
        String filePath = "output/" + filename;

        // Criando a pasta de saída se não existir
        try {
            Path path = Path.of("output");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verificando se o arquivo já existe e alterando o nome se necessário
        Path file = Path.of(filePath + fileExtension);
        while (Files.exists(file)) {
            count++;
            filename = originalFileName + "(" + count + ")";
            filePath = "output/" + filename;
            file = Path.of(filePath + fileExtension);
        }

        // Escrevendo as strings no arquivo
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath + fileExtension))) {
            for (String str : strings) {
                writer.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
