import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leitor {
    private static List<Processo> processos = new ArrayList<>();
    private String nomeArquivo;

    public static List<Processo> Processos(String nomeArquivo) {

        try {
            FileReader arquivoReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(arquivoReader);

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] entrada = linha.split(" ");

                if (entrada.length == 3) {// Processos sem I/O
                    Processo processo = new Processo(entrada[0], Integer.parseInt(entrada[1]),
                            Integer.parseInt(entrada[2]), null);
                    processos.add(processo);
                } else if (entrada.length == 4) {// Processos com I/O
                    List<Integer> IO = new ArrayList<>();
                    for (String valor : entrada[3].split(",")) {
                        IO.add(Integer.parseInt(valor));
                    }
                    Processo processo = new Processo(entrada[0], Integer.parseInt(entrada[1]),
                            Integer.parseInt(entrada[2]), IO);
                    processos.add(processo);
                } else {
                    return null;
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }

        // Ordenar a lista de processos por tempo de chegada
        ordenarProcessosPorTempoDeChegada(processos);
        
        return processos;
    }

    // Método para ordenar os processos por tempo de chegada
    private static void ordenarProcessosPorTempoDeChegada(List<Processo> processos) {
        Collections.sort(processos, new Comparator<Processo>() {
            @Override
            public int compare(Processo p1, Processo p2) {
                // Compare os tempos de chegada dos processos
                return Integer.compare(p1.getChegada(), p2.getChegada());
            }
        });
    }
}