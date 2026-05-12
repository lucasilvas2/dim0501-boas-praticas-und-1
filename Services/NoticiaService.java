package Services;
import java.util.ArrayList;

import Enums.Classificacao;
import Enums.PalavrasChave;
import Enums.Score;
import Enums.MenuOpcoes;
import Models.Noticia;
import Interfaces.IODevice;

public class NoticiaService {

    public static final int QUANTIDADE_DE_CARACTERES_MINIMO = 10;
    static ArrayList<Noticia> listaDeNoticias = new ArrayList<>();
    IODevice ioDevice;

    public NoticiaService(IODevice ioDevice) {
        this.ioDevice = ioDevice;
    }

    public void adicionaNoticia(String texto, String classificacao) {
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
            ioDevice.mostrarMensagemTerminal("Erro: texto invalido.", true);
        }
    }

    public void listarNoticias() {
        for (int i = 0; i < listaDeNoticias.size(); i++) {
            ioDevice.mostrarMensagemTerminal("Texto: " + listaDeNoticias.get(i).getTexto(), true);
            ioDevice.mostrarMensagemTerminal("Classificacao: " + listaDeNoticias.get(i).getClassificacao(), true);
            ioDevice.mostrarMensagemTerminal("-------------------", true);
        }
    }

    public static String analisarNoticia(String texto) {
        int score = calcularScore(texto);

        if (score <= Score.MINIMO.getValor()) return Classificacao.CONFIAVEL.getValor();
        if (score <= Score.MEDIO.getValor()) return Classificacao.DUVIDOSA.getValor();
        return Classificacao.FALSA.getValor();
    }

    public static int calcularScore(String texto) {
        int score = 0;

        if (!texto.contains(PalavrasChave.FONTE.getValor())) {
            score = score + 1;
        }
        if (texto.contains(PalavrasChave.EXCLAMACAO.getValor())) {
            score = score + 1;
        }
        if (texto.contains(PalavrasChave.URGENTE.getValor())) {
            score = score + 1;
        }
        if (texto.length() < QUANTIDADE_DE_CARACTERES_MINIMO) {
            score = score + 1;
        }

        return score;
    }

    public void addNoticiaECalssificacaoManual() {
        String texto = ioDevice.lerString("Digite o texto: ");

        String classificacao = ioDevice.lerString("Digite classificacao: ");

        if (classificacao.equals("")) {
            adicionaNoticia(texto, null);
        } else {
            adicionaNoticia(texto, classificacao);
        }
    }

    public void addNoticiaEClassificarAutomaticamente() {
        String texto = ioDevice.lerString("Digite o texto: ");

        String classificacao = analisarNoticia(texto);
        adicionaNoticia(texto, classificacao);
    }

    public void iniciar() {

        while (true) {
            MenuOpcoes[] opcoes = MenuOpcoes.values();
            mostrarMenu(opcoes);

            Integer operacao = ioDevice.lerInt("Digite uma operacao: ");

            if (operacao == MenuOpcoes.ADICIONAR_MANUAL.getValor()) {
                addNoticiaECalssificacaoManual();
            } else if (operacao.equals(MenuOpcoes.ADICIONAR_AUTOMATICO.getValor())) {
                addNoticiaEClassificarAutomaticamente();
            } else if (operacao.equals(MenuOpcoes.LISTAR.getValor())) {
                listarNoticias();
            } else if (operacao.equals(MenuOpcoes.SAIR.getValor())) {
                ioDevice.close();
                break;
            } else {
                ioDevice.mostrarMensagemTerminal("Erro: Operacao invalida", true);
            }
        }

        ioDevice.close();
    }

    public void mostrarMenu(MenuOpcoes[] opcoes){
        for (int i = 0; i < opcoes.length; i++) {
            ioDevice.mostrarMensagemTerminal(opcoes[i].getValor() + " - " + opcoes[i].getDescricao(), true);
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
}
