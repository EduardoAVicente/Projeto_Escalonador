public class Fila {

    private Node inicio;
    private Node fim;
    private int tamanho;

    public Fila() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void adicionar(Processo dado) {
        Node novoNode = new Node(dado);

        if (inicio == null) {
            inicio = novoNode;
            fim = novoNode;
        } else {
            fim.proximo = novoNode;
            fim = novoNode;
        }
        tamanho++;
    }

    public Processo remover() {
        if (inicio == null) {
            throw new IllegalStateException("A fila está vazia");
        }

        Processo dadoRemovido = inicio.dado;
        inicio = inicio.proximo;

        if (inicio == null) {
            fim = null;
        }
        tamanho--;
        return dadoRemovido;
    }

    public int size() {
        return tamanho;
    }

    public Processo get(int index) {
        if (index < 0 || index >= tamanho) {
            throw new IndexOutOfBoundsException("Index inválido: " + index);
        }

        Node current = inicio;
        for (int i = 0; i < index; i++) {
            current = current.proximo;
        }
        return current.dado;
    }

    private class Node {
        Processo dado;
        Node proximo;

        Node(Processo dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }
}

