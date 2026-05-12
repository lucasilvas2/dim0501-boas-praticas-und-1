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

            Integer operacao = ioDevice.lerInt("Digite uma operacao: ");

            try{
                if (operacao == MenuOpcoes.ADICIONAR_MANUAL.getValor()) {
                    String texto = ioDevice.lerString("Digite o texto: ");

                    String classificacao = ioDevice.lerString("Digite classificacao: ");
                    this.service.adicionarNoticiaManual(texto, classificacao);
                } else if (operacao.equals(MenuOpcoes.ADICIONAR_AUTOMATICO.getValor())) {
                    String texto = ioDevice.lerString("Digite o texto: ");
                    this.service.adicionarNoticiaAutomaticamente(texto);
                } else if (operacao.equals(MenuOpcoes.LISTAR.getValor())) {
                    ArrayList<Noticia> noticias = this.service.listarNoticias();
                    listarNoticias(noticias);
                } else if (operacao.equals(MenuOpcoes.SAIR.getValor())) {
                    ioDevice.close();
                    break;
                } else {
                    ioDevice.mostrarMensagemTerminal("Erro: Operacao invalida", true);
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
