import java.util.ArrayList;
import java.util.List;

public class SJF {
    private List<Processo> processos;
    private List<Processo> saida;
    JobUtil jobutil;
    Fila fila;
    Log log;
    private Processo cpu = null;

    public SJF(List<Processo> processos, int tempo) {
        this.processos = processos;
        this.saida = new ArrayList<>();
        jobutil = new JobUtil(tempo);
        fila = new Fila();
        log = new Log();
    }

    public List<Processo> iniciar() {
        return this.saida;
    }

}