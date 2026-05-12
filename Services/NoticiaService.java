package Services;
import java.util.ArrayList;

import Enums.MenuOpcoes;
import Models.Noticia;
import Interfaces.IODevice;

public class NoticiaService {

    static ArrayList<Noticia> listaDeNoticias = new ArrayList<>();
    IODevice ioDevice;

    public NoticiaService(IODevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public void listarNoticias() {
        for (int i = 0; i < listaDeNoticias.size(); i++) {
            ioDevice.mostrarMensagemTerminal("Texto: " + listaDeNoticias.get(i).getTexto(), true);
            ioDevice.mostrarMensagemTerminal("Classificacao: " + listaDeNoticias.get(i).getClassificacao(), true);
            ioDevice.mostrarMensagemTerminal("-------------------", true);
        }
    }

    public void adicionarNoticiaManual() {
        String texto = ioDevice.lerString("Digite o texto: ");

        String classificacao = ioDevice.lerString("Digite classificacao: ");
        Noticia noticia = new Noticia(texto, classificacao);
        listaDeNoticias.add(noticia);
    }

    public void adicionarNoticiaAutomaticamente() {
        String texto = ioDevice.lerString("Digite o texto: ");

        Noticia noticia = new Noticia(texto);
        listaDeNoticias.add(noticia);
    }

    public void iniciar() {

        while (true) {
            MenuOpcoes[] opcoes = MenuOpcoes.values();
            mostrarMenu(opcoes);

            Integer operacao = ioDevice.lerInt("Digite uma operacao: ");

            try{
                if (operacao == MenuOpcoes.ADICIONAR_MANUAL.getValor()) {
                    this.adicionarNoticiaManual();
                } else if (operacao.equals(MenuOpcoes.ADICIONAR_AUTOMATICO.getValor())) {
                    this.adicionarNoticiaAutomaticamente();
                } else if (operacao.equals(MenuOpcoes.LISTAR.getValor())) {
                    this.listarNoticias();
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

        ioDevice.close();
    }

    public void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            ioDevice.mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
        }
    }
}
