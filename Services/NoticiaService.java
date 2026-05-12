package Services;
import java.util.ArrayList;

import Models.Noticia;
import Interfaces.IODevice;
import Repositories.NoticiaRepository;

public class NoticiaService {

    NoticiaRepository noticiaRepository;
    IODevice ioDevice;

    public NoticiaService(IODevice ioDevice, NoticiaRepository noticiaRepository) {
        this.ioDevice = ioDevice;
        this.noticiaRepository = noticiaRepository;
    }

    public void listarNoticias() {
        for (int i = 0; i < noticiaRepository.listarNoticias().size(); i++) {
            ioDevice.mostrarMensagemTerminal("Texto: " + noticiaRepository.listarNoticias().get(i).getTexto(), true);
            ioDevice.mostrarMensagemTerminal("Classificacao: " + noticiaRepository.listarNoticias().get(i).getClassificacao(), true);
            ioDevice.mostrarMensagemTerminal("-------------------", true);
        }
    }

    public void adicionarNoticiaManual() {
        String texto = ioDevice.lerString("Digite o texto: ");

        String classificacao = ioDevice.lerString("Digite classificacao: ");
        Noticia noticia = new Noticia(texto, classificacao);
        noticiaRepository.salvarNoticia(noticia);
    }

    public void adicionarNoticiaAutomaticamente() {
        String texto = ioDevice.lerString("Digite o texto: ");

        Noticia noticia = new Noticia(texto);
        noticiaRepository.salvarNoticia(noticia);
    }
}
