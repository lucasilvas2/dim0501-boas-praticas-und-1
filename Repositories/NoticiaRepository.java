package Repositories; 

import Models.Noticia;
import java.util.ArrayList;

/**
 * Contrato para persistência de notícias.
 */
public interface NoticiaRepository {

    /**
     * Persiste uma notícia.
     *
     * @param noticia notícia a ser salva
     */
    void salvarNoticia(Noticia noticia);

    /**
     * Retorna todas as notícias persistidas.
     *
     * @return lista de notícias; vazia se nenhuma foi salva
     */
    ArrayList<Noticia> listarNoticias();
}
