package Services;

import java.util.ArrayList;
import Models.Noticia;
import Repositories.NoticiaRepository;

/**
 * Serviço responsável por orquestrar as operações sobre notícias.
 */
public class NoticiaService {

    NoticiaRepository noticiaRepository;

    /**
     * Cria o serviço com o repositório informado.
     *
     * @param noticiaRepository repositório utilizado para persistência
     */
    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    /**
     * Retorna todas as notícias persistidas.
     *
     * @return lista de notícias
     */
    public ArrayList<Noticia> listarNoticias() {
        return noticiaRepository.listarNoticias();
    }

    /**
     * Adiciona uma notícia com classificação definida manualmente.
     *
     * @param texto conteúdo da notícia
     * @param classificacao classificação atribuída à notícia
     */
    public void adicionarNoticiaManual(String texto, String classificacao) {
        Noticia noticia = new Noticia(texto, classificacao);
        noticiaRepository.salvarNoticia(noticia);
    }

    /**
     * Adiciona uma notícia com classificação inferida automaticamente pelo score.
     *
     * @param texto conteúdo da notícia
     */
    public void adicionarNoticiaAutomaticamente(String texto) {
        Noticia noticia = new Noticia(texto);
        noticiaRepository.salvarNoticia(noticia);
    }
}
