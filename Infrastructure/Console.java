package Infrastructure;

import java.util.Scanner;
import Interfaces.IODevice;

/**
 * Implementação de {@link IODevice} para entrada e saída via terminal.
 */
public class Console implements IODevice {
    private Scanner scanner;

    /**
     * @param scanner scanner associado à entrada padrão
     */
    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String lerString(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException se a entrada não for um número válido
     */
    @Override
    public int lerInt(String mensagem){
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Entrada inválida. Por favor, insira um número.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mostrarMensagemTerminal(String mensagem, boolean pularLinha){
        if(pularLinha){
            System.out.println(mensagem);
        } else {
            System.out.print(mensagem);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        scanner.close();
    }
}
