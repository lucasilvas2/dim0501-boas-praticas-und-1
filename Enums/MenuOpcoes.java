package Enums;

public enum MenuOpcoes {
    ADICIONAR_MANUAL(1, "Adicionar noticia manualmente"),
    ADICIONAR_AUTOMATICO(2, "Adicionar noticia e classificala automaticamente"),
    LISTAR(3, "Listar todas as noticias"),
    SAIR(4, "Sair do sistema");

    private final int valor;
    private final String descricao;

    MenuOpcoes(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}