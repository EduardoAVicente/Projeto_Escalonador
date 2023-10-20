import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //ROUND ROBIN
        List<Processo> processos = new ArrayList();
        Leitor leitor = new Leitor();
        RoundRobin roundrobin = new RoundRobin(leitor.Processos(), 4, 0);
        processos = roundrobin.iniciar();

        System.out.println(processos.get(0).getPID());

        // FIFO
        // List<Processo> processos = new ArrayList();
        // Leitor leitor = new Leitor();
        // FIFO FIFO = new FIFO(leitor.Processos(), 0);
        // processos = FIFO.iniciar();

        // System.out.println(processos.get(0).getPID());
    }
}