package Enums;

public enum Score {
    MINIMO(0),
    MEDIO(1),
    MAXIMO(2);

    private final int valor;

    Score(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
