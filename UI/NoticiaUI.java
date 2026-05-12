package UI;

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
                    this.service.adicionarNoticiaManual();
                } else if (operacao.equals(MenuOpcoes.ADICIONAR_AUTOMATICO.getValor())) {
                    this.service.adicionarNoticiaAutomaticamente();
                } else if (operacao.equals(MenuOpcoes.LISTAR.getValor())) {
                    this.service.listarNoticias();
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

    public void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            ioDevice.mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
        }
    }

   
}
