package Enums;

public enum PalavrasChave {
    FONTE("FONTE"), URGENTE("URGENTE"), EXCLAMACAO("!!!");

    private final String valor;

    PalavrasChave(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
