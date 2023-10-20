
public class Main {
    public static void main(String[] args) {
        Leitor leitor = new Leitor();
        // for (Processo processo : leitor.Processos()) {
        //     processo.imprime();
        // }
        RoundRobin roundrobin = new RoundRobin(leitor.Processos(),4);
        roundrobin.inicar();
    }
}