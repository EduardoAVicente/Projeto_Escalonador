
import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private int Quantum;
    private int tempo = 0;
    private List<Processo> processos;
    JobUtil jobutil;
    Fila fila;
    Log log;
    private Processo cpu = null;

    public RoundRobin(List<Processo> processos, int Quantum) {
        this.Quantum = Quantum;
        this.processos = processos;
    }

    public void inicar() {
        // Inicializando objetos e variaveis
        jobutil = new JobUtil();
        fila = new Fila();
        log = new Log();
        cpu = processos.get(0);
        processos.remove(0);

        // logs iniciais
        log.write("***********************************");
        log.write("***** ESCALONADOR ROUND ROBIN *****");
        log.write("-----------------------------------");
        log.write("------- INICIANDO SIMULACAO -------");
        log.write("-----------------------------------");

        // loop de processos
        while (cpu != null) {
            // adiciona a espera
            jobutil.espera();
            log.write("********** TEMPO " + jobutil.getTime() + " **************");

            // procediemtnos de I/O
            if (cpu.getIO() == cpu.getTempo()) {
                log.write("#[evento] OPERACAO I/O <" + cpu.getPID() + ">");
                fila.adicionar(cpu);
                cpu = fila.remover();
            }

            // procedimento de chegada de processo
            if (processos.size() > 0) {
                if (processos.get(0).getChegada() == jobutil.getTime()) {
                    log.write("#[evento] CHEGADA <" + processos.get(0).getPID() + ">");
                    fila.adicionar(processos.get(0));
                    processos.remove(0);
                }
            }

            // verifica se o processo da cpu terminou
            if (cpu.getTempo() == 0) {
                log.write("#[evento] ENCERRANDO <" + cpu.getPID() + ">");

                // verifica se ainda a processos
                if (fila.size() > 0) {
                    cpu = fila.remover();
                } else {
                    cpu = null;
                    log.write("ACABARAM OS PROCESSOS!!!");
                    log.write("-----------------------------------");
                    log.write("------- Encerrando simulacao ------");
                    log.write("-----------------------------------");
                    break;
                }

            }

            // print de estado da fila e cpu
            if (fila.size() == 0) {
                log.write("FILA: Nao ha processos na fila");
            } else {
                StringBuilder filaString = new StringBuilder();
                filaString.append("FILA: ");
                for (int i = 0; i < fila.size(); i++) {
                    filaString.append(fila.get(i).getPID()).append("(").append(fila.get(i).getTempo()).append(")")
                            .append(" ");
                }
                log.write(filaString.toString());
            }

            log.write("CPU: " + cpu.getPID() + "(" + cpu.getTempo() + ")");
            // computado o processo da cpu
            cpu.tempo();

        }
        log.close("RoundRobin");
    }

}
