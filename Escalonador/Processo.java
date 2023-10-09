package Escalonador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Processo {
    private String PID;
    private float duracao;
    private float chegada;
    private List<Float> IO;

    public Processo(String PID, float duracao, float chegada, List<Float> IO) {
        this.PID = PID;
        this.duracao = duracao;
        this.chegada = chegada;
        this.IO = IO;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public float getDuracao() {
        return duracao;
    }

    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public float getChegada() {
        return chegada;
    }

    public void setChegada(float chegada) {
        this.chegada = chegada;
    }

    public float getIO() {
        if (IO != null && !IO.isEmpty()) {
            return IO.get(0);
        } else {
            return 0.0f;
        }
    }

    public void removePrimeiroIO() {
        if (IO != null && !IO.isEmpty()) {
            IO.remove(0);
        }
    }

    public void imprime() {
        System.out.println("Processo: " + PID);
        System.out.println("Duração: " + duracao);
        System.out.println("Chegada: " + chegada);
        System.out.print("IO: ");
        if (IO != null) {
            for (float io : IO) {
                System.out.print(io + ", ");
            }
        }else{
            System.out.println("null");
        }
        System.out.println("");
            System.out.println("");
    }

}
