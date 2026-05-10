import java.util.ArrayList;
import java.util.Scanner;

import Enums.Classificacao;
import Enums.PalavrasChave;
import Enums.Score;
import Enums.MenuOpcoes;
import Models.Noticia;

public class Sistema {

    public static final int QUANTIDADE_DE_CARACTERES_MINIMO = 10;
    static ArrayList<Noticia> listaDeNoticias = new ArrayList<>();

    public static void adicionaNoticia(String texto, String classificacao) {
        if (textoValido(texto)) {
            Noticia novaNoticia = new Noticia();
            novaNoticia.setTexto(texto);

            if (!textoValido(classificacao)) {
                novaNoticia.setClassificacao(Classificacao.DUVIDOSA.getValor());
            } else {
                novaNoticia.setClassificacao(classificacao);
            }

            listaDeNoticias.add(novaNoticia);
        } else {
            mostrarMensagemTerminal("Erro: texto invalido.", true);
        }
    }

    public static void listarNoticias() {
        // lista tudo
        for (int i = 0; i < listaDeNoticias.size(); i++) {
            mostrarMensagemTerminal("Texto: " + listaDeNoticias.get(i).getTexto(), true);
            mostrarMensagemTerminal("Classificacao: " + listaDeNoticias.get(i).getClassificacao(), true);
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

        if (score == Score.MINIMO.getValor()) {
            return Classificacao.CONFIAVEL.getValor();
        } else if (score == Score.MEDIO.getValor()) {
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
                System.out.println("Erro: Operacao invalida");
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
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna um valor inválido para o menu tratar
        }
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

    public static boolean textoValido(String texto){
        return texto != null && !texto.trim().equals("");
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}
