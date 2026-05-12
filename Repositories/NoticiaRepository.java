package Repositories; 

import Models.Noticia;
import java.util.ArrayList;

public interface NoticiaRepository {
    public void salvarNoticia(Noticia noticia);
    public ArrayList<Noticia> listarNoticias();
}
