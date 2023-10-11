package Escalonador;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin {
    private int Quantum;
    private int tempo = 0;
    private List<Processo> processos;
    private List<Processo> fila;
    private Processo cpu = null;

    public RoundRobin(List<Processo> processos, int Quantum) {
        this.Quantum = Quantum;
        this.processos = processos;
    }



    public void inicar() {
        JobUtil jobutil = new JobUtil();

        System.out.println("***********************************");
        System.out.println("***** ESCALONADOR ROUND ROBIN *****");
        System.out.println("-----------------------------------");
        System.out.println("------- INICIANDO SIMULACAO -------");
        System.out.println("-----------------------------------");

        while(processos.size() != 0){
             jobutil.espera();

    






            System.out.println("********** TEMPO "+jobutil.getTime()+" **************");










            
            if(cpu != null){
                System.out.println("CPU");
            }
            
        }
            
    }

}
