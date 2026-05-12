package Infrastructure;

import java.util.Scanner;
import Interfaces.IODevice;

public class Console implements IODevice {
    private Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }
    
    @Override
    public String lerString(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    @Override
    public int lerInt(String mensagem){
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor inválido para o classe de serviço tratar
        }
    }

    @Override
    public void mostrarMensagemTerminal(String mensagem, Boolean pularLinha){
        if(pularLinha){
            System.out.println(mensagem);
        } else {
            System.out.print(mensagem);
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
