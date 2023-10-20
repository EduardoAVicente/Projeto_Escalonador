import java.util.ArrayList;
import java.util.List;

public class MainSJF {
    public static void main(String[] args) {

        List<Processo> processos = new ArrayList();
        Leitor leitor = new Leitor();
        SJF sjf = new SJF(leitor.Processos(), 0);
        processos = sjf.iniciar();

    }
}