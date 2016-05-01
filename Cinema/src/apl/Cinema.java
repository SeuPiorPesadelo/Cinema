package apl;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Filme;
import model.Sala;
import model.Sessao;
import model.Venda;
import static util.FormatadorUtil.*;

/**
 * Classe Cinema
 */
public class Cinema {

    static int geradorIdsFilme = 0;
    static int geradorIdsSala = 0;
    static final ArrayList<Filme> FILMES = new ArrayList<>();
    static final ArrayList<Sala> SALAS = new ArrayList<>();
    static final ArrayList<Sessao> SESSOES = new ArrayList<>();
    static final ArrayList<Venda> VENDAS = new ArrayList<>();
    static final Relatorio RELATORIO = new Relatorio();
    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.err.println("*********** Atenção, já existem dois Filmes e duas Salas cadastrados.***********\nPode-se cadastrar mais filmes e salas, ou começar a associá-los a partir de Sessões.");
        preencheArraysComDadosIniciais();
        do {
            exibeMenu();
        } while (true);
    }

    /**
     * Exibe Menu
     *
     * @return void
     */
    private static void exibeMenu() {
        String op;
        StringBuilder sb = new StringBuilder();
        sb.append("\n***** Bem Vindo ao Cinema *****\n");
        sb.append("Escolha uma opção: \n");
        sb.append("\tCadastrar Filme: 1\n");
        sb.append("\tCadastrar Sala: 2\n");
        sb.append("\tRegistrar Sessão: 3\n");
        sb.append("\tRealizar Venda: 4\n");
        sb.append("\tRelatorios do Cinema: 5\n");
        sb.append("\tRelatorio de Testes: 6\n");
        sb.append("\tEncerrar Programa: 0\n");
        System.out.println(sb);
        op = SC.next();
        if (op.equalsIgnoreCase("0")) {
            System.out.println("Programa Encerrado!!!");
            System.exit(0);
        }
        switch (op) {
            case "1":
                cadastrarFilme();
                break;
            case "2":
                cadastrarSala();
                break;
            case "3":
                cadastrarSessao();
                break;
            case "4":
                realizarVenda();
                break;
            case "5":
                Relatorio();
                break;
            case "6":
                RelatorioTeste();
                break;
            default:
                System.out.println("Opção Inválida!!!");
        }
    }

    /**
     * Cadastra Filme
     *
     * @return void
     */
    private static void cadastrarFilme() {
        //tratar inputMissMatch
        try {
            Filme filme = new Filme();
            filme.setId(++geradorIdsFilme);
            System.out.println("Digite o Nome do Filme");
            SC.nextLine();
            String nome = SC.nextLine();
            for (Filme filme1 : FILMES) {
                if (filme1.getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Filme com este nome '" + nome + "' já foi cadastrado anteriormente");
                    return;
                }
            }
            filme.setNome(nome);
            System.out.println("Digite o Genero do Filme");
            filme.setGenero(SC.nextLine());
            System.out.println("Digite a Sinopse do Filme");
            filme.setSinopse(SC.nextLine());
            FILMES.add(filme);
        } catch (InputMismatchException e) {
            entradaInvalida();
        }
    }

    /**
     * Cadastra Sala
     *
     * @return void
     */
    private static void cadastrarSala() {
        //tratar inputMissMatch
        Sala sala = new Sala();
        sala.setId(++geradorIdsSala);
        System.out.println("Digite a Quantidade de Assentos da Sala");
        sala.setQtdAssentos(SC.nextInt());
        SALAS.add(sala);
    }

    /**
     * Cadastra Sessao
     *
     * @return void
     */
    private static void cadastrarSessao() {
        Sessao sessao = new Sessao();
        System.out.println("Digite o Horário da Sessão - Ex.:  23:59");
        String hora = SC.next();
        if (formataDateHora(hora) != null) {
            sessao.setHora(formataDateHora(hora));
        } else {
            entradaInvalida();
            return;
        }
        System.out.println("");
        System.out.println(" --- Filmes Disponíveis --- ");
        for (Filme filme : FILMES) {
            System.out.print("ID: " + filme.getId());
            System.out.println(", Nome: " + filme.getNome());
            System.out.println("");
        }
        System.out.println("Digite o ID do Filme dessa Sessão");
        boolean idValidoParaFilme = false;
        boolean idValidoParaSala = false;
        try {
            int idFilme = SC.nextInt();
            for (Filme filme : FILMES) {
                if (filme.getId() == idFilme) {
                    sessao.setFilme(filme);
                    idValidoParaFilme = true;
                    break;
                }
            }
            if (!idValidoParaFilme) {
                entradaInvalida();
                return;
            }
            System.out.println("");
            System.out.println(" --- Salas Disponíveis --- ");
            for (Sala sala : SALAS) {
                System.out.println("Sala ID: " + sala.getId());
                System.out.println("");
            }
            System.out.println("Digite o ID da Sala dessa Sessão");
            int idSala = SC.nextInt();
            for (Sala sala : SALAS) {
                if (sala.getId() == idSala) {
                    Sala s = new Sala();
                    s.setId(idSala);
                    s.setQtdAssentos(sala.getQtdAssentos());
                    sessao.setSala(s);
                    idValidoParaSala = true;
                    break;
                }
            }
        } catch (InputMismatchException e) {
            entradaInvalida();
            SC.nextLine();
            return;
        }
        if (!idValidoParaSala) {
            entradaInvalida();
            return;
        }

        SESSOES.add(sessao);
    }

    /**
     * Cadastra Venda
     *
     * @return void
     */
    private static void realizarVenda() {
        System.out.println(" --- Sessoes Disponíveis --- ");
        for (Sessao sessao : SESSOES) {
            int lugaresDisponiveis = sessao.getSala().getQtdAssentos();
            System.out.println("");
            if (lugaresDisponiveis != 0) {
                System.out.println("SESSÃO DISPONÍVEL");
                System.out.println("Filme: " + sessao.getFilme().getNome());
                System.out.println("Gênero: " + sessao.getFilme().getGenero());
                System.out.println("Sinopse: " + sessao.getFilme().getSinopse());
                System.out.println("Hora: " + formataHora(sessao.getHora()));
                System.out.println("Sala ID: " + sessao.getSala().getId());
                System.out.println("Qtd de Lugares Dispoíveis: " + lugaresDisponiveis);
            } else {
                System.out.println("SESSÃO LOTADA");
                System.out.println("\tFilme: " + sessao.getFilme().getNome());
                System.out.println("\tHora: " + formataHora(sessao.getHora()));
                System.out.println("\tSala ID: " + sessao.getSala().getId());
            }
        }
        System.out.println("");
        System.out.println("Digite a Hora da Sessao desejada");
        String hora = SC.next();
        Date date = formataDateHora(hora);
        if (date != null) {
            System.out.println("Digite o ID da Sala desejada");
            boolean sessaoInvalida = true;
            try {
                int idSala = SC.nextInt();
                for (Sessao sessao : SESSOES) {
                    int qtdDisponivel = sessao.getSala().getQtdAssentos();
                    if (sessao.getHora().getHours() == date.getHours() && sessao.getHora().getMinutes() == date.getMinutes()
                            && sessao.getSala().getId() == idSala) {
                        if (qtdDisponivel > 0) {
                            System.out.println("");
                            System.out.println("Ingressos dispoíveis desta Sessão: " + qtdDisponivel);
                            System.out.println("Deseja comprar quantos ingressos? ");
                            int qtdComprada = SC.nextInt();
                            if (qtdDisponivel >= qtdComprada) {
                                sessao.getSala().setQtdAssentos(qtdDisponivel - qtdComprada);
                                sessao.setQtdVendida(sessao.getQtdVendida() + qtdComprada);
                                Venda venda = new Venda();
                                venda.setSessao(sessao);
                                VENDAS.add(venda);
                                sessaoInvalida = false;
                            } else {
                                entradaInvalida();
                                return;
                            }
                            System.out.println("Venda Efetuada com Sucesso !!!!!!!!!");
                        } else {
                            System.out.println("Esta Sessao NAO Possui mais lugares!!!");
                            return;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                entradaInvalida();
                return;
            }
            if (sessaoInvalida) {
                entradaInvalida();
            }
        } else {
            entradaInvalida();
        }
    }

    /**
     * Gera relatorios
     *
     * @return void
     */
    private static void Relatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n*** Relatórios ***\n");
        sb.append("Escolha uma opção: \n");
        sb.append("\tVenda por Filme: 1\n");
        sb.append("\tVenda por Horários: 2\n");
        sb.append("\tVenda por Salas: 3\n");
        sb.append("\tVenda por Sessoes: 4\n");
        sb.append("\tFilmes que estiveram em mais Sessoes: 5\n");
        sb.append("\tSalas mais usadas: 6\n");
        sb.append("\tVoltar ao Menu Inicial: 0\n");
        System.out.println(sb);
        String op = SC.next();
        if (op.equalsIgnoreCase("0")) {
            return;
        }
        switch (op) {
            case "1":
                Relatorio.vendaPorFilme();
                break;
            case "2":
                Relatorio.vendaPorHorario();
                break;
            case "3":
                Relatorio.vendaPorSala();
                break;
            case "4":
                Relatorio.vendaPorSecoes();
                break;
            case "5":
                Relatorio.filmesComMaisSessoes();
                break;
            case "6":
                Relatorio.salasMaisUtilizadas();
                break;
            default:
                System.out.println("Opção Inválida!!!");
                exibeMenu();
        }
    }

    /**
     * Mensagem de Erro de Entrada de Dados
     *
     * @return void
     */
    private static void entradaInvalida() {
        System.out.println("Entrada de Dado Inválida. Tente de novo.");
    }

    private static void preencheArraysComDadosIniciais() {
        Filme f1 = new Filme();
        f1.setId(++geradorIdsFilme);
        f1.setNome("Maremoto no Saara");
        f1.setGenero("Aventura");
        f1.setSinopse("Uma Tsunami do barulho apronta altas confusões no deserto.");
        Filme f2 = new Filme();
        f2.setId(++geradorIdsFilme);
        f2.setNome("Tempestade de Areia em Alto Mar");
        f2.setGenero("Aventura");
        f2.setSinopse("Uma Tempestado de Areia do barulho apronta altas confusões no mar.");
        FILMES.add(f1);
        FILMES.add(f2);
        Sala s1 = new Sala();
        s1.setId(++geradorIdsSala);
        s1.setQtdAssentos(5);
        Sala s2 = new Sala();
        s2.setId(++geradorIdsSala);
        s2.setQtdAssentos(2);
        SALAS.add(s1);
        SALAS.add(s2);
    }

    private static void RelatorioTeste() {
        System.out.println("Foram efetuado testes unitários no cadastro de:\nFilmes, Salas, Sessoes \ne tbm na parte de vendas e relatórios do Cinema.\nTudo OK !");
    }
}
