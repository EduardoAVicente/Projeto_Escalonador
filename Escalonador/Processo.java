import java.util.List;

public class Processo {
    private String PID;
    private int duracao;
    private int chegada;
    private int espera;
    private List<Integer> IO;
    private int tempoTotal;
    private String totalIO;

    public Processo(String PID, int duracao, int chegada, List<Integer> IO) {
        this.PID = PID;
        this.duracao = duracao;
        this.chegada = chegada;
        this.IO = IO;
        this.espera = 0;
        this.tempoTotal = this.duracao;
        StringBuilder sb = new StringBuilder();
        String separator = "";
        if (IO == null || IO.isEmpty()) {
            totalIO = "";
        } else {
            for (Integer integer : IO) {
                sb.append(separator).append(String.valueOf(integer));
                separator = ", ";
            }
            totalIO = sb.toString();
        }
    }

    public String getPID() {
        return PID;
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

    public int getQuantIO() {
        if (IO != null && !IO.isEmpty()) {
            return IO.size();
        }else{
            return 0;
        }

    }

    public String imprimeIO() {
        return totalIO;

    }

    public int getChegada() {
        return chegada;
    }

    public void setChegada(int chegada) {
        this.chegada = chegada;
    }

    public int getTempoTotal() {
        return this.tempoTotal;
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

    public int getEspera() {
        return this.espera;
    }

    public void atualizaEspera() {
        espera = espera + 1;
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