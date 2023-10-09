package Escalonador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoundRobin {
    private Optional<Float> Quantum;
    private float tempo = 0;
    private List<Processo> processos;

    public RoundRobin(Optional<Float> Quantum) {
        this.Quantum = Quantum;
        this.processos = new ArrayList<>();
    }

    public void add(Processo processo) {
        processos.add(processo);
    }

    public void inicar(){
        
    }
}
