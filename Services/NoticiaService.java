package Services;
import java.util.ArrayList;

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
}
