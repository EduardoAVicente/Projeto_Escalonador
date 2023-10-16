package Escalonador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Processo {
    private String PID;
    private int duracao;
    private int chegada;
    private List<Integer> IO;
    private int tempo;

    public Processo(String PID, int duracao, int chegada, List<Integer> IO) {
        this.PID = PID;
        this.duracao = duracao;
        this.chegada = chegada;
        this.IO = IO;
        this.tempo = this.duracao;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getChegada() {
        return chegada;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public int getIO() {
        if (IO != null && !IO.isEmpty()) {
            return IO.get(0);
        } else {
            return 0;// rever
        }
    }

    public void removePrimeiroIO() {
        if (IO != null && !IO.isEmpty()) {
            IO.remove(0);
        }
    }

    public void tempo() {
        this.tempo = tempo - 1;
    }

    public int getTempo(){
        return this.tempo;
    }

    public void imprime() {
        System.out.println("Processo: " + PID);
        System.out.println("Duração: " + duracao);
        System.out.println("Chegada: " + chegada);
        System.out.print("IO: ");
        if (IO != null) {
            for (int i = 0; i < IO.size() - 1; i++) {
                System.out.print(IO.get(i) + ", ");
            }
            System.out.println(IO.get(IO.size() - 1));
        } else {
            System.out.println("null");
        }
        System.out.println("");
    }

}
