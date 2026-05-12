package Services;
import java.util.ArrayList;

import Models.Noticia;
import Repositories.NoticiaRepository;

public class NoticiaService {

    NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public ArrayList<Noticia> listarNoticias() {
        return noticiaRepository.listarNoticias();
    }

    public void adicionarNoticiaManual(String texto, String classificacao) {
        Noticia noticia = new Noticia(texto, classificacao);
        noticiaRepository.salvarNoticia(noticia);
    }

    public void adicionarNoticiaAutomaticamente(String texto) {
        Noticia noticia = new Noticia(texto);
        noticiaRepository.salvarNoticia(noticia);
    }
}
