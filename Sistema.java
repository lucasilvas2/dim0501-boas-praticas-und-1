import java.util.ArrayList;
import java.util.Scanner;

class Noticia {
    String texto;
    String classificacao;

    Noticia() {   
    }

    Noticia(String texto, String classificacao) {
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

enum Classificacao {
    CONFIAVEL("confiavel"), DUVIDOSA("duvidosa"), FALSA("falsa");

    private final String valor;

    Classificacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}

enum PalavrasChave{
    
    FONTE("FONTE"), URGENTE("URGENTE"), EXCLAMACAO("!!!");

    private final String valor;

    PalavrasChave(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}

enum Score {
    MINIMO(0),
    MEDIO(1),
    MAXIMO(2);

    private final int valor;

    Score(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

enum MenuOpcoes {
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

public class Sistema {

    public static final int QUANTIDADE_DE_CARACTERES_MINIMO = 10;
    static ArrayList<Noticia> listaDeNoticias = new ArrayList<>();

    // função que faz tudo
    public static void adicionaNoticia(String texto, String classificacao) {
        // adiciona coisa
        if (texto != null && !texto.equals("")) {
            Noticia novaNoticia = new Noticia();
            novaNoticia.texto = texto;

            if (classificacao == null || classificacao.equals("")) {
                novaNoticia.classificacao = Classificacao.DUVIDOSA.getValor();
            } else {
                novaNoticia.classificacao = classificacao;
            }

            listaDeNoticias.add(novaNoticia);
        } else {
            mostrarMensagemTerminal("Erro: texto invalido.", true);
        }
    }

    public static void listarNoticias() {
        // lista tudo
        for (int i = 0; i < listaDeNoticias.size(); i++) {
            mostrarMensagemTerminal("Texto: " + listaDeNoticias.get(i).texto, true);
            mostrarMensagemTerminal("Classificacao: " + listaDeNoticias.get(i).classificacao, true);
            mostrarMensagemTerminal("-------------------", true);
        }
    }

    public static String analisar(String txt) {
        int score = 0;

        if (!txt.contains(PalavrasChave.FONTE.getValor())) {
            score = score + 1;
        }
        if (txt.contains(PalavrasChave.EXCLAMACAO.getValor())) {
            score = score + 1;
        }
        if (txt.contains(PalavrasChave.URGENTE.getValor())) {
            score = score + 1;
        }
        if (txt.length() < QUANTIDADE_DE_CARACTERES_MINIMO) {
            score = score + 1;
        }

        if (score == 0) {
            return Classificacao.CONFIAVEL.getValor();
        } else if (score == 1) {
            return Classificacao.DUVIDOSA.getValor();
        } else {
            return Classificacao.FALSA.getValor();
        }
    }

    public static void addNoticiaECalssificacaoManual(Scanner sc) {
        String texto = lerString(sc, "Digite o texto: ");

        String classificacao = lerString(sc, "Digite classificacao: ");

        if (classificacao.equals("")) {
            adicionaNoticia(texto, null);
        } else {
            adicionaNoticia(texto, classificacao);
        }
    }

    public static void addNoticiaEClassificarAutomaticamente(Scanner sc) {
        String texto = lerString(sc, "Digite o texto: ");

        String classificacao = analisar(texto);
        adicionaNoticia(texto, classificacao);
    }

    public static void menu() {
        Scanner scannerComandosOperacoes = new Scanner(System.in);

        while (true) {
            MenuOpcoes[] opcoes = MenuOpcoes.values();
            mostrarMenu(opcoes);

            Integer operacao = lerInt(scannerComandosOperacoes, "Digite uma operacao: ");

            if (operacao == MenuOpcoes.ADICIONAR_MANUAL.getValor()) {
                addNoticiaECalssificacaoManual(scannerComandosOperacoes);
            } else if (operacao.equals(MenuOpcoes.ADICIONAR_AUTOMATICO.getValor())) {
                addNoticiaEClassificarAutomaticamente(scannerComandosOperacoes);
            } else if (operacao.equals(MenuOpcoes.LISTAR.getValor())) {
                listarNoticias();
            } else if (operacao.equals(MenuOpcoes.SAIR.getValor())) {
                scannerComandosOperacoes.close();
                break;
            } else {
                System.out.println("errado");
            }
        }

        scannerComandosOperacoes.close();
    }

    public static String lerString(Scanner sc, String mensagem){
        System.out.print(mensagem);
        return sc.nextLine();
    }

    public static int lerInt(Scanner sc, String mensagem){
        System.out.print(mensagem);
        return sc.nextInt();
    }

    public static void mostrarMensagemTerminal(String mensagem, Boolean pularLinha){
        if(pularLinha){
            System.out.println(mensagem);
        } else {
            System.out.print(mensagem);
        }
    }

    public static void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
        }
    }

    public static String formatarTexto(String texto){
        texto = texto.trim();
        if (texto == null || texto.equals("")) {
            return null;
        }
        return texto;
    }

    public static boolean validarTexto(String texto){
        if (texto == null || texto.trim().equals("")) {
            return false;
        }
        return true;
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}
