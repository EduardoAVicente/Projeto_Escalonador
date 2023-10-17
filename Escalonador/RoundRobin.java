package Escalonador;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private int Quantum;
    private int tempo = 0;
    private List<Processo> processos;
    JobUtil jobutil;
    Fila fila;
    private Processo cpu = null;

    public RoundRobin(List<Processo> processos, int Quantum) {
        this.Quantum = Quantum;
        this.processos = processos;
    }

    public void inicar() {
        // Inicializando objetos e variaveis
        jobutil = new JobUtil();
        fila = new Fila();
        cpu = processos.get(0);
        processos.remove(0);

        // logs iniciais
        System.out.println("***********************************");
        System.out.println("***** ESCALONADOR ROUND ROBIN *****");
        System.out.println("-----------------------------------");
        System.out.println("------- INICIANDO SIMULACAO -------");
        System.out.println("-----------------------------------");

        // loop de processos
        while (cpu != null) {
            // adiciona a espera
            jobutil.espera();
            System.out.println("********** TEMPO " + jobutil.getTime() + " **************");

            // procediemtnos de I/O
            if (cpu.getIO() == cpu.getTempo()) {
                System.out.println("#[evento] OPERACAO I/O <" + cpu.getPID() + ">");
                fila.adicionar(cpu);
                cpu = fila.remover();
            }

            // procedimento de chegada de processo
            if (processos.size() > 0) {
                if (processos.get(0).getChegada() == jobutil.getTime()) {
                    System.out.println("#[evento] CHEGADA <" + processos.get(0).getPID() + ">");
                    fila.adicionar(processos.get(0));
                    processos.remove(0);
                }
            }

            // verifica se o processo da cpu terminou
            if (cpu.getTempo() == 0) {
                System.out.println("#[evento] ENCERRANDO <" + cpu.getPID() + ">");

                // verifica se ainda a processos
                if (fila.size() > 0) {
                    cpu = fila.remover();
                } else {
                    cpu = null;
                    System.out.println("ACABARAM OS PROCESSOS!!!");
                    System.out.println("-----------------------------------");
                    System.out.println("------- Encerrando simulacao ------");
                    System.out.println("-----------------------------------");
                    break;
                }

            }

            // print de estado da fila e cpu
            if (fila.size() == 0) {
                System.out.println("FILA: Nao ha processos na fila");
            } else {
                System.out.print("FILA: ");
                for (int i = 0; i < fila.size(); i++) {
                    System.out.print(fila.get(i).getPID() + "(" + fila.get(i).getTempo() + ")" + " ");
                }
                System.out.println();
            }

            System.out.println("CPU: " + cpu.getPID() + "(" + cpu.getTempo() + ")");

            // computado o processo da cpu
            cpu.tempo();

        }

    }

}
