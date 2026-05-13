package Interfaces;

/**
 * Contrato para dispositivos de entrada e saída da aplicação.
 */
public interface IODevice {

    /**
     * Exibe uma mensagem e lê uma string fornecida pelo usuário.
     *
     * @param mensagem texto exibido antes da leitura
     * @return string fornecida pelo usuário
     */
    String lerString(String mensagem);

    /**
     * Exibe uma mensagem e lê um número inteiro fornecido pelo usuário.
     *
     * @param mensagem texto exibido antes da leitura
     * @return inteiro fornecido pelo usuário
     */
    int lerInt(String mensagem);

    /**
     * Exibe uma mensagem no dispositivo de saída.
     *
     * @param mensagem texto a ser exibido
     * @param pularLinha se {@code true}, adiciona quebra de linha ao final
     */
    void mostrarMensagemTerminal(String mensagem, boolean pularLinha);

    /**
     * Libera os recursos do dispositivo.
     */
    void close();
}
