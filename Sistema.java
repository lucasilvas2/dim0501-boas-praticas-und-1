import java.util.ArrayList;
import java.util.Scanner;

class Noticia {
    String texto;
    Classificacao classificacao;

    Noticia() {   
    }

    Noticia(String texto, Classificacao classificacao) {
        this.setTexto(texto);
        this.setClassificacao(classificacao);
    }

    public String getTexto() {
        return texto;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
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
    public static void f(String a, String b) {
        // adiciona coisa
        if (a != null && !a.equals("")) {
            Noticia novaNoticia = new Noticia();
            novaNoticia.texto = a;

            if (b == null || b.equals("")) {
                novaNoticia.classificacao = Classificacao.DUVIDOSA;
            } else {
                novaNoticia.classificacao = Classificacao.valueOf(b.toUpperCase());
            }

            listaDeNoticias.add(novaNoticia);
        } else {
            System.out.println("erro");
        }
    }

    public static void listarNoticias() {
        // lista tudo
        for (int i = 0; i < listaDeNoticias.size(); i++) {
            System.out.println("Texto: " + listaDeNoticias.get(i).texto);
            System.out.println("Classificacao: " + listaDeNoticias.get(i).classificacao.getValor());
            System.out.println("-------------------");
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
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        System.out.print("Digite classificacao: ");
        String classificacao = sc.nextLine();

        if (classificacao.equals("")) {
            f(texto, null);
        } else {
            f(texto, classificacao);
        }
    }

    public static void addNoticiaEClassificarAutomaticamente(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        String classificacao = analisar(texto);
        f(texto, classificacao);
    }

    public static void menu() {
        Scanner scannerComandosOperacoes = new Scanner(System.in);

        while (true) {
            System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

            String operacao = scannerComandosOperacoes.nextLine();

            if (operacao.equals("1")) {
                addNoticiaECalssificacaoManual(scannerComandosOperacoes);
            } else if (operacao.equals("2")) {
                addNoticiaEClassificarAutomaticamente(scannerComandosOperacoes);
            } else if (operacao.equals("3")) {
                listarNoticias();
            } else if (operacao.equals("4")) {
                scannerComandosOperacoes.close();
                break;
            } else {
                System.out.println("errado");
            }
        }

        scannerComandosOperacoes.close();
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}
