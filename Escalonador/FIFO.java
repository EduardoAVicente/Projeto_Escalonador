import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class FIFO extends SwingWorker<List<Processo>, String> {
    private List<Processo> processos;
    private List<Processo> saida;
    JobUtil jobutil;
    Fila fila;
    Log log;
    private Processo cpu = null;
    GanttChart chart;
    GUI gui;

    public FIFO(List<Processo> processos, int tempo, String outputDirectory, GUI gui) {
        this.processos = processos;
        this.saida = new ArrayList<>();
        this.jobutil = new JobUtil(tempo);
        this.fila = new Fila();
        this.log = new Log(outputDirectory);
        this.chart = new GanttChart("FIFO");
        this.gui = gui;
        log.setGUI(gui);
    }

    @Override
    protected List<Processo> doInBackground() throws Exception {
        // Inicializando objetos e variaveis
        chart.setSize(800, 600);
        cpu = processos.get(0);
        processos.remove(0);

        // logs iniciais
        log.write("***********************************");
        log.write("******** ESCALONADOR FIFO *********");
        log.write("-----------------------------------");
        log.write("------- INICIANDO SIMULACAO -------");
        log.write("-----------------------------------");
        // loop de processos
        while (cpu != null  && !isCancelled()) {
            // adiciona a espera
            jobutil.espera();
            if (jobutil.getCiclo() < 10) {
                log.write("********** TEMPO " + jobutil.getCiclo() + " **************");
            } else {
                log.write("********** TEMPO " + jobutil.getCiclo() + " *************");
            }

            // calcula tempo de espera
            for (int i = 0; i < fila.size(); i++) {
                fila.get(i).atualizaEspera();
            }

            // procedimento de I/O
            if (cpu.getIO() == true) {
                log.write("#[evento] OPERACAO I/O <" + cpu.getPID() + ">");
                fila.adicionar(cpu);
                chart.add(cpu.getPID(), jobutil.getCiclo());
                cpu = fila.remover();
                if (fila.size() > 0) {// verifica se o processo mudou para validação da espera
                    if (fila.get(fila.size() - 1) != cpu) {
                    }
                }
            }

            // procedimento de chegada de processo
            if (processos.size() > 0) {
                if (processos.get(0).getChegada() == jobutil.getCiclo()) {
                    log.write("#[evento] CHEGADA <" + processos.get(0).getPID() + ">");
                    fila.adicionar(processos.get(0));
                    processos.remove(0);
                }
            }

            // verifica se o processo da cpu terminou
            if (cpu.getDuracao() <= 0) {
                log.write("#[evento] ENCERRANDO <" + cpu.getPID() + ">");
                saida.add(cpu);
                // verifica se ainda a processos
                if (fila.size() > 0) {
                    chart.add(cpu.getPID(), jobutil.getCiclo());
                    cpu = fila.remover();
                } else {
                    cpu = null;
                    if (fila.size() == 0) {
                        log.write("FILA: Nao ha processos na fila");
                    } else {
                        StringBuilder filaString = new StringBuilder();
                        filaString.append("FILA: ");
                        for (int i = 0; i < fila.size(); i++) {
                            filaString.append(fila.get(i).getPID()).append("(").append(fila.get(i).getDuracao())
                                    .append(")")
                                    .append(" ");
                        }
                        log.write(filaString.toString());
                    }
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
                    filaString.append(fila.get(i).getPID()).append("(").append(fila.get(i).getDuracao()).append(")")
                            .append(" ");
                }
                log.write(filaString.toString());
            }

            log.write("CPU: " + cpu.getPID() + "(" + cpu.getDuracao() + ")");
            // computado o processo da cpu
            cpu.atualizaDuracao();

        }
        log.close("FIFO");
        if (!isCancelled()) {
            chart.setVisible(true);
            chart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        return this.saida;
    }

    @Override
    protected void done() {
        try {
            saida = get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (gui != null) {
            gui.setTabela(saida);
        }
    }
}
