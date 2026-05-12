package Interfaces;

public interface IODevice {
    String lerString(String mensagem);
    int lerInt(String mensagem);
    void mostrarMensagemTerminal(String mensagem, boolean pularLinha);
    void close();
}
