package UI;

import Models.Noticia;
import Interfaces.IODevice;
import Services.NoticiaService;
import Enums.MenuOpcoes;
import java.util.ArrayList;

/**
 * Camada de apresentação responsável pela interação com o usuário via terminal.
 */
public class NoticiaUI {

    private NoticiaService service;
    private IODevice ioDevice;

    /**
     * @param service   serviço de negócio para operações sobre notícias
     * @param ioDevice  dispositivo de entrada e saída
     */
    public NoticiaUI(NoticiaService service, IODevice ioDevice) {
        this.service = service;
        this.ioDevice = ioDevice;
    }

    /**
     * Inicia o loop principal da aplicação, exibindo o menu e processando
     * as operações do usuário até que o encerramento seja solicitado.
     * Erros de entrada inválida nas operações são capturados e exibidos sem encerrar a aplicação.
     *
     * @throws IllegalArgumentException se a entrada do menu não for um número válido
     */
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
                        ioDevice.close();
                        System.exit(0);
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

    /**
     * Exibe as opções do menu numeradas.
     *
     * @param opcoes array com as opções disponíveis
     */
    public void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            ioDevice.mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
        }
    }

    /**
     * Exibe cada notícia da lista no dispositivo de saída.
     *
     * @param noticias lista de notícias a exibir
     */
    private void listarNoticias(ArrayList<Noticia> noticias) {
        for (Noticia noticia : noticias) {
            ioDevice.mostrarMensagemTerminal(noticia.toString(), true);
        }
    }
}
