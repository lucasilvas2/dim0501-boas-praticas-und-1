package Enums;

public enum Classificacao {
    CONFIAVEL("confiavel"), DUVIDOSA("duvidosa"), FALSA("falsa");

    private final String valor;

    Classificacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}