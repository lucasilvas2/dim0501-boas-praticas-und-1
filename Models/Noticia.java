package Models;

public class Noticia {
    private String texto;
    private String classificacao;

    public Noticia() {   
    }

    public Noticia(String texto, String classificacao) {
        this.setTexto(texto);
        this.setClassificacao(classificacao);
    }

    public String getTexto() {
        return texto;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
