import java.util.ArrayList;
import java.util.Scanner;

class Noticia {
    String texto;
    Classificacao classificacao;
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
    static ArrayList<Noticia> data = new ArrayList<>();

    // função que faz tudo
    public static void f(String a, String b) {
        // adiciona coisa
        if (a != null && !a.equals("")) {
            Noticia d = new Noticia();
            d.texto = a;

            if (b == null || b.equals("")) {
                d.classificacao = Classificacao.DUVIDOSA;
            } else {
                d.classificacao = Classificacao.valueOf(b.toUpperCase());
            }

            data.add(d);
        } else {
            System.out.println("erro");
        }
    }

    public static void func2() {
        // lista tudo
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Texto: " + data.get(i).texto);
            System.out.println("Classificacao: " + data.get(i).classificacao.getValor());
            System.out.println("-------------------");
        }
    }

    public static String analisar(String txt) {
        int score = 0;

        if (!txt.contains("FONTE")) {
            score = score + 1;
        }
        if (txt.contains("!!!")) {
            score = score + 1;
        }
        if (txt.contains("URGENTE")) {
            score = score + 1;
        }
        if (txt.length() < 10) {
            score = score + 1;
        }

        if (score == 0) {
            return "confiavel";
        } else if (score == 1) {
            return "duvidosa";
        } else {
            return "falsa";
        }
    }

    public static void addManual(Scanner sc) {
        System.out.print("Digite o texto: ");
        String t = sc.nextLine();

        System.out.print("Digite classificacao: ");
        String c = sc.nextLine();

        if (c.equals("")) {
            f(t, null);
        } else {
            f(t, c);
        }
    }

    public static void addAuto(Scanner sc) {
        System.out.print("Digite o texto: ");
        String t = sc.nextLine();

        String c = analisar(t);
        f(t, c);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

            String op = sc.nextLine();

            if (op.equals("1")) {
                addManual(sc);
            } else if (op.equals("2")) {
                addAuto(sc);
            } else if (op.equals("3")) {
                func2();
            } else if (op.equals("4")) {
                break;
            } else {
                System.out.println("errado");
            }
        }

        sc.close();
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}
