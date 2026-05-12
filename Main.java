import java.util.Scanner;
import Infrastructure.Console;
import Infrastructure.MemoryNoticia;
import Repositories.NoticiaRepository;
import Services.NoticiaService;
import UI.NoticiaUI;
import Interfaces.IODevice;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            IODevice ioDevice = new Console(scanner);
            NoticiaRepository noticiaRepository = new MemoryNoticia();
            NoticiaService service = new NoticiaService(noticiaRepository);
            NoticiaUI ui = new NoticiaUI(service, ioDevice);
            
            ui.iniciar();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }
}
