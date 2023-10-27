import java.util.ArrayList;
import java.util.List;

public class MainRoundRobin {
    public static void main(String[] args) {

        List<Processo> processos = new ArrayList();
        Leitor leitor = new Leitor();
        RoundRobin roundrobin = new RoundRobin(leitor.Processos(), 4, 0);
        processos = roundrobin.iniciar();
    }
}