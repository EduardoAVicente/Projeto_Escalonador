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

    public void ordenarDuracao() {
        if (tamanho <= 1) {
            return; // Nada a fazer se a fila estiver vazia ou tiver apenas um elemento
        }

        boolean trocado;
        do {
            trocado = false;
            Node atual = inicio;
            Node anterior = null;

            while (atual.proximo != null) {
                Node proximo = atual.proximo;

                if (atual.dado.getDuracao() > proximo.dado.getDuracao()) {
                    // Trocar os dados dos nós
                    Processo temp = atual.dado;
                    atual.dado = proximo.dado;
                    proximo.dado = temp;
                    trocado = true;
                }

                // Avançar para o próximo nó
                anterior = atual;
                atual = proximo;
            }
        } while (trocado);
    }

    public boolean estaOrdenadoDuracao() {
        if (tamanho <= 1) {
            return true; // Fila vazia ou com um único elemento está sempre ordenada
        }

        Node atual = inicio;

        while (atual.proximo != null) {
            Node proximo = atual.proximo;

            if (atual.dado.getDuracao() > proximo.dado.getDuracao()) {
                return false; // Se encontrar um elemento fora de ordem, a fila não está ordenada
            }

            atual = proximo;
        }

        return true; // Todos os elementos foram verificados e a fila está ordenada
    }

    public void ordenarIO() {
        if (tamanho <= 1) {
            return; // Nada a fazer se a fila estiver vazia ou tiver apenas um elemento
        }

        boolean trocado;
        do {
            trocado = false;
            Node atual = inicio;
            Node anterior = null;

            while (atual.proximo != null) {
                Node proximo = atual.proximo;

                if (atual.dado.getQuantIO() > proximo.dado.getQuantIO()) {
                    // Trocar os dados dos nós
                    Processo temp = atual.dado;
                    atual.dado = proximo.dado;
                    proximo.dado = temp;
                    trocado = true;
                }

                // Avançar para o próximo nó
                anterior = atual;
                atual = proximo;
            }
        } while (trocado);
    }

    public boolean estaOrdenadaIO() {
        if (tamanho <= 1) {
            return true; // Fila vazia ou com um único elemento está sempre ordenada
        }

        Node atual = inicio;

        while (atual.proximo != null) {
            Node proximo = atual.proximo;

            if (atual.dado.getQuantIO() > proximo.dado.getQuantIO()) {
                return false; // Se encontrar um elemento fora de ordem, a fila não está ordenada
            }

            atual = proximo;
        }

        return true; // Todos os elementos foram verificados e a fila está ordenada
    }

}
