package Infrastructure;

import Repositories.NoticiaRepository;
import java.util.ArrayList;
import Models.Noticia;

public class MemoryNoticia implements NoticiaRepository {
    private ArrayList<Noticia> listaDeNoticias = new ArrayList<>();

    public void salvarNoticia(Noticia noticia) {
        listaDeNoticias.add(noticia);
    }

    public ArrayList<Noticia> listarNoticias() {
        return listaDeNoticias;
    }
}
