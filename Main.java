import java.util.Scanner;
import Infrastructure.Console;
import Services.NoticiaService;
import UI.NoticiaUI;
import Interfaces.IODevice;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            IODevice ioDevice = new Console(scanner);
            NoticiaService service = new NoticiaService(ioDevice);
            NoticiaUI ui = new NoticiaUI(service, ioDevice);
            
            ui.iniciar();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}
