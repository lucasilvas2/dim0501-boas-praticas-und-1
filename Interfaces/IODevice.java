package Interfaces;

public interface IODevice {
    String lerString(String mensagem);
    int lerInt(String mensagem);
    void mostrarMensagemTerminal(String mensagem, Boolean pularLinha);
    void close();
}
