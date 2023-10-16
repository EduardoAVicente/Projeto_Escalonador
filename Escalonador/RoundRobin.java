package Escalonador;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private int Quantum;
    private int tempo = 0;
    private List<Processo> processos;
    // private Fila fila;
    private Processo cpu = null;

    public RoundRobin(List<Processo> processos, int Quantum) {
        this.Quantum = Quantum;
        this.processos = processos;
    }

    public void inicar() {
        JobUtil jobutil = new JobUtil();

        Fila fila = new Fila();
        System.out.println("***********************************");
        System.out.println("***** ESCALONADOR ROUND ROBIN *****");
        System.out.println("-----------------------------------");
        System.out.println("------- INICIANDO SIMULACAO -------");
        System.out.println("-----------------------------------");
        cpu = processos.get(0);
        processos.remove(0);

        while (processos.size() != 0) {
            // roda o tempo
            jobutil.espera();

            // Sistema de logs
            System.out.println("********** TEMPO " + jobutil.getTime() + " **************");
            //LOGS DE EVETOS
            if (processos.get(0).getChegada() == jobutil.getTime()) {// adiciona novos pocessos
                System.out.println("#[evento] CHEGADA <" + processos.get(0).getPID() + ">");
            }

            //LOGS DE MONITORIAÇÃO
            if (fila.size() == 0) {
                System.out.println("FILA: Nao ha processos na fila");
            } else {
                System.out.print("FILA: ");
                for (int i = 0; i < fila.size(); i++) {
                    System.out.print(fila.get(i).getPID() + " ");
                }
                System.out.println();
            }

            System.out.println("CPU: " + cpu.getPID() + "(" + cpu.getTempo() + ")");

            cpu.tempo();

            if (processos.get(0).getChegada() == jobutil.getTime()) {// adiciona novos pocessos
                fila.adicionar(cpu);
                cpu = processos.get(0);
                processos.remove(0);

            }
        }

    }

}
