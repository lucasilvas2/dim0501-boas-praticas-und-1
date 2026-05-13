package Infrastructure;

import Repositories.NoticiaRepository;
import java.util.ArrayList;
import Models.Noticia;

/**
 * Implementação em memória de {@link NoticiaRepository}.
 * Os dados são perdidos ao encerrar a aplicação.
 */
public class MemoryNoticia implements NoticiaRepository {
    private ArrayList<Noticia> listaDeNoticias = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void salvarNoticia(Noticia noticia) {
        listaDeNoticias.add(noticia);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Noticia> listarNoticias() {
        return listaDeNoticias;
    }
}
