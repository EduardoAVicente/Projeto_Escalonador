import java.util.ArrayList;
import java.util.List;

public class MainFIFO {
    public static void main(String[] args) {

        List<Processo> processos = new ArrayList();
        Leitor leitor = new Leitor();
        FIFO FIFO = new FIFO(leitor.Processos("arquivoProcessos.txt"), 0, "./",null);
        FIFO.execute();

    }
}