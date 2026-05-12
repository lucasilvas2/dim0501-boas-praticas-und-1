package Models;

import Enums.Classificacao;
import Enums.PalavrasChave;
import Enums.Score;

public class Noticia {
    private String texto;
    private String classificacao;
    private int score;
    private static final int QUANTIDADE_DE_CARACTERES_MINIMO = 10;

    public Noticia(String texto) {
        this.setTexto(texto);
        this.classificarAutomaticamente();
    }

    public Noticia(String texto, String classificacao) {
        this.setTexto(texto);

        if (classificacao == null) {
            this.setClassificacao(Classificacao.DUVIDOSA.getValor());
        } else {
            this.setClassificacao(classificacao);
        }

        this.calcularScore();
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
        if (texto != null && !texto.trim().equals("")) {
            this.texto = texto;
        }else{
            throw new IllegalArgumentException("Texto invalido");
        }
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        if (score < 0) throw new IllegalArgumentException("Score inválido");
        this.score = score;
    }

    private int calcularScore(){
        int score = 0;

        if (!this.getTexto().contains(PalavrasChave.FONTE.getValor())) {
            score++;
        }
        if (this.getTexto().contains(PalavrasChave.EXCLAMACAO.getValor())) {
            score++;
        }
        if (this.getTexto().contains(PalavrasChave.URGENTE.getValor())) {
            score++;
        }
        if (this.getTexto().length() < QUANTIDADE_DE_CARACTERES_MINIMO) {
            score++;
        }

        return score;
    }

    private String classificarPorScore(){
        if (this.score <= Score.MINIMO.getValor()) return Classificacao.CONFIAVEL.getValor();
        if (this.score <= Score.MEDIO.getValor()) return Classificacao.DUVIDOSA.getValor();
        return Classificacao.FALSA.getValor();
    }

    private void classificarAutomaticamente(){
        this.setScore(calcularScore());
        this.setClassificacao(classificarPorScore());
    }

    @Override
    public String toString() {
        String nl = System.lineSeparator();
        return "Texto: " + texto + nl +
               "Classificação: " + classificacao + nl +
               "-------------------";
    }
}
