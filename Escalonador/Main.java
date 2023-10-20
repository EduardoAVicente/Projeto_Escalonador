
public class Main {
    public static void main(String[] args) {
        Leitor leitor = new Leitor();
        RoundRobin roundrobin = new RoundRobin(leitor.Processos(),4);
        roundrobin.inicar();
    }
}