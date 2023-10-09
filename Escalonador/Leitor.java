package Escalonador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor {
    private static List<Processo> processos = new ArrayList<>();

    public static List<Processo> Processos() {
        String nomeArquivo = "./Escalonador/arquivo.txt";

        try {
            FileReader arquivoReader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(arquivoReader);

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                String[] entrada = linha.split(" ");

                if (entrada.length == 3) {// Processos sem I/O
                    Processo processo = new Processo(entrada[0], Float.parseFloat(entrada[1]),
                            Float.parseFloat(entrada[2]), null);
                    processos.add(processo);
                } else if (entrada.length == 4) {// Processos com I/O
                    List<Float> IO = new ArrayList<>();
                    for (String valor : entrada[3].split(",")) {
                        IO.add(Float.parseFloat(valor));
                    }
                    Processo processo = new Processo(entrada[0], Float.parseFloat(entrada[1]),
                            Float.parseFloat(entrada[2]), IO);
                    processos.add(processo);
                } else {
                    System.out.println("ERRO: Arquivo de entrada inválida");
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return processos;
    }
}
