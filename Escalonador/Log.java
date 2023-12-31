import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Log {

    private List<String> strings;
    private String outputDirectory; // adicionando o diretório de saída
    private GUI gui = null;

    public Log(String outputDirectory) {
        strings = new ArrayList<>();
        this.outputDirectory = outputDirectory;
    }

    public void write(String str) {
       // System.out.println(str);
        strings.add(str);
        if (gui != null) {
            gui.atualizaConsole(str);
        }
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void close(String filename) {
        String originalFileName = filename;
        String fileExtension = ".txt";
        int count = 0;
        String filePath = outputDirectory + "/" + filename;

        // Criando a pasta de saída se não existir
        try {
            Path path = Path.of(outputDirectory);
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
            filePath = outputDirectory + "/" + filename;
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