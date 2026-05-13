package Models;

import Enums.Classificacao;
import Enums.PalavrasChave;
import Enums.Score;

/**
 * Representa uma notícia com texto, classificação de credibilidade e score.
 * A classificação pode ser definida manualmente ou inferida automaticamente
 * com base em critérios de penalidade aplicados ao texto.
 */
public class Noticia {
    private String texto;
    private String classificacao;
    private int score;
    private static final int QUANTIDADE_DE_CARACTERES_MINIMO = 10;

    /**
     * Cria uma notícia e infere a classificação automaticamente pelo score.
     *
     * @param texto conteúdo da notícia; não pode ser nulo ou vazio
     * @throws IllegalArgumentException se o texto for nulo ou vazio
     */
    public Noticia(String texto) {
        this.setTexto(texto);
        this.classificarAutomaticamente();
    }

    /**
     * Cria uma notícia com classificação definida manualmente.
     * Se a classificação for nula ou vazia, utiliza {@code Classificacao.DUVIDOSA}.
     *
     * @param texto conteúdo da notícia; não pode ser nulo ou vazio
     * @param classificacao classificação atribuída à notícia
     * @throws IllegalArgumentException se o texto for nulo ou vazio
     */
    public Noticia(String texto, String classificacao) {
        this.setTexto(texto);

        // foi mantida a regra original, que define como duvidosa caso não seja fornecida uma classificação
        if (classificacao == null || classificacao.trim().isEmpty()) {
            this.setClassificacao(Classificacao.DUVIDOSA.getValor());
        } else {
            this.setClassificacao(classificacao.trim());
        }

        int score = this.calcularScore();
        this.setScore(score);
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

    /**
     * Define o texto da notícia.
     *
     * @param texto conteúdo da notícia
     * @throws IllegalArgumentException se o texto for nulo ou vazio
     */
    public void setTexto(String texto) {
        if (texto != null && !texto.trim().equals("")) {
            this.texto = texto;
        }else{
            throw new IllegalArgumentException("Texto inválido");
        }
    }

    public int getScore() {
        return score;
    }

    /**
     * Define o score da notícia.
     *
     * @param score valor calculado; deve ser maior ou igual a zero
     * @throws IllegalArgumentException se o score for negativo
     */
    private void setScore(int score) {
        if (score < 0) throw new IllegalArgumentException("Score inválido");
        this.score = score;
    }

    /**
     * Calcula o score de credibilidade com base em penalidades aplicadas ao texto.
     *
     * <p>Critérios de penalidade (cada um soma 1 ao score):
     * <ul>
     *   <li>Ausência da palavra-chave {@code FONTE}</li>
     *   <li>Presença de ponto de exclamação ({@code !})</li>
     *   <li>Presença da palavra {@code URGENTE}</li>
     *   <li>Texto com menos de {@value #QUANTIDADE_DE_CARACTERES_MINIMO} caracteres</li>
     * </ul>
     *
     * @return score calculado; quanto maior, menos confiável a notícia
     */
    private int calcularScore(){
        int score = 0;

        // penaliza a ausência da palavra-chave FONTE
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

    /**
     * Determina a classificação com base no score atual.
     * score &lt;= MINIMO → CONFIAVEL; MINIMO &lt; score &lt;= MEDIO → DUVIDOSA; acima de MEDIO → FALSA.
     *
     * @return string de classificação conforme {@link Enums.Classificacao}
     */
    private String classificarPorScore(){
        if (this.score <= Score.MINIMO.getValor()) return Classificacao.CONFIAVEL.getValor();
        if (this.score <= Score.MEDIO.getValor()) return Classificacao.DUVIDOSA.getValor();
        return Classificacao.FALSA.getValor();
    }

    /**
     * Calcula o score e define a classificação automaticamente.
     */
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
