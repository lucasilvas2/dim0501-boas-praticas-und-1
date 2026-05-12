package UI;

import Models.Noticia;
import Interfaces.IODevice;
import Services.NoticiaService;
import Enums.MenuOpcoes;
import java.util.ArrayList;

public class NoticiaUI {

    private NoticiaService service;
    private IODevice ioDevice;

    public NoticiaUI(NoticiaService service, IODevice ioDevice) {
        this.service = service;
        this.ioDevice = ioDevice;
    }

    public void iniciar() {
        while (true) {
            MenuOpcoes[] opcoes = MenuOpcoes.values();
            mostrarMenu(opcoes);

            int operacao = ioDevice.lerInt("Digite uma operacao: ");

            try{
                switch(operacao){
                    case 1: 
                        String texto1 = ioDevice.lerString("Digite o texto: ");
                        String classificacao1 = ioDevice.lerString("Digite classificacao: ");
                        this.service.adicionarNoticiaManual(texto1, classificacao1);
                        break;  
                    case 2: 
                        String texto2 = ioDevice.lerString("Digite o texto: ");
                        this.service.adicionarNoticiaAutomaticamente(texto2);
                        break;
                    case 3: 
                        ArrayList<Noticia> noticias = this.service.listarNoticias();
                        listarNoticias(noticias);
                        break;
                    case 4: 
                        break;
                    default: 
                        ioDevice.mostrarMensagemTerminal("Erro: Operacao invalida", true);
                        break;
                }
            }catch(IllegalArgumentException e){
                ioDevice.mostrarMensagemTerminal("Erro: " + e.getMessage(), true);
            }
        }
    }

    private void listarNoticias(ArrayList<Noticia> noticias) {
        for (Noticia noticia : noticias) {
            ioDevice.mostrarMensagemTerminal(noticia.toString(), true);
        }
    }

    public void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            ioDevice.mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
        }
    }
}
