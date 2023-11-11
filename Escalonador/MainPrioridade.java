import java.util.ArrayList;
import java.util.List;

public class MainPrioridade {
    public static void main(String[] args) {

        List<Processo> processos = new ArrayList();
        Leitor leitor = new Leitor();
        Prioridade prioridade = new Prioridade(leitor.Processos("arquivoProcessos.txt"), 0,"./",null);
        prioridade.execute();

    }
}