import java.util.List;

public class Processo {
    private String PID;
    private int duracao;
    private int chegada;
    private List<Integer> IO;

    public Processo(String PID, int duracao, int chegada, List<Integer> IO) {
        this.PID = PID;
        this.duracao = duracao;
        this.chegada = chegada;
        this.IO = IO;
        this.duracao = this.duracao;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void atualizaDuracao() {
        this.duracao = duracao - 1;
        if (IO != null && !IO.isEmpty()) {
            for (int i = 0; i < IO.size(); i++) {
                IO.set(i, IO.get(i) - 1);
            }
        }
    }

    public int getChegada() {
        return chegada;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public boolean getIO() {
        if (IO != null && !IO.isEmpty()) {
            if (IO.get(0) == 0) {
                if (IO.size() > 1) {
                    IO.set(1, IO.get(1) - IO.get(0));
                }
                IO.remove(0);
                return true;
            }
        }
        return false;
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
