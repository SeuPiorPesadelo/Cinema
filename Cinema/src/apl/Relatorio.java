package apl;

import java.util.Scanner;
import model.Sessao;
import java.util.Date;
import model.Sala;
import static apl.Cinema.*;
import java.util.Objects;
import model.Filme;
import static util.FormatadorUtil.*;

/**
 * Classe Relatorio
 */
public class Relatorio {

    private static final Scanner SC = new Scanner(System.in);

    /**
     * vendaPorFilme() Mostra a venda por filme
     *
     */
    public static void vendaPorFilme() {
        System.out.println(" --- Filmes Disponíveis --- ");
        for (Filme filme : FILMES) {
            System.out.print("ID: " + filme.getId());
            System.out.println(" Nome: " + filme.getNome());
            System.out.println("");
        }
        System.out.println("Digite o ID do Filme");
        String nome = SC.next();
        int soma = 0;
        try {
            int id = Integer.parseInt(nome);
            for (Sessao sessao : SESSOES) {
                if (sessao.getFilme().getId() == id) {
                    soma += sessao.getQtdVendida();
                }
            }
            System.out.println("Total: " + soma + " ingressos vendidos.");
        } catch (Exception e) {
            exibeErro();
        }
    }

    /**
     * vendaPorHorario() Mostra a venda por Horario
     *
     */
    public static void vendaPorHorario() {
        System.out.println(" --- Horários Disponíveis --- ");
        for (Sessao sessao : SESSOES) {
            System.out.println("Horário das " + formataHora(sessao.getHora()));
            System.out.println("");
        }
        System.out.println("Digite o Horario - Ex.: 22:00");
        String hora = SC.next();
        Date date = formataDateHora(hora);
        if (date != null) {
            int soma = 0;
            for (Sessao sessao : SESSOES) {
                if (sessao.getHora().getHours() == date.getHours() && sessao.getHora().getMinutes() == date.getMinutes()) {
                    soma += sessao.getQtdVendida();
                }
            }
            System.out.println("Total: " + soma + " ingressos vendidos no horario das " + formataHora(date));
        } else {
            System.out.println("Horário Inválido");
        }
    }

    /**
     * vendaPorSala() Mostra a venda por Sala
     *
     */
    public static void vendaPorSala() {
        System.out.println(" --- Salas Disponíveis --- ");
        for (Sala sala : SALAS) {
            System.out.println("Sala ID: " + sala.getId());
        }
        System.out.println("Digite o ID da Sala");
        String sala = SC.next();
        int salaID;
        int soma = 0;
        try {
            salaID = Integer.parseInt(sala);
            for (Sessao sessao : SESSOES) {
                if (sessao.getSala().getId() == salaID) {
                    soma += sessao.getQtdVendida();
                }
            }
        } catch (Exception e) {
            exibeErro();
            return;
        }
        System.out.println("Total: " + soma + " ingressos vendidos para a Sala de ID " + salaID);
    }

    /**
     * vendaPorSecoes() Mostra a venda por Secoes
     *
     */
    public static void vendaPorSecoes() {
        System.out.println(" --- Horários de Seções Disponíveis --- ");
        for (Sessao sessao : SESSOES) {
            System.out.println("Seção das " + formataHora(sessao.getHora()) + " da Sala " + sessao.getSala().getId());
            System.out.println("");
        }
        System.out.println("Digite o Horário da Seção");
        String hora = SC.next();
        Date date = formataDateHora(hora);
        if (date != null) {
            int soma = 0;
            for (Sessao sessao : SESSOES) {
                if (sessao.getHora().getHours() == date.getHours() && sessao.getHora().getMinutes() == date.getMinutes()) {
                    soma += sessao.getQtdVendida();
                }
            }
            System.out.println("Total: " + soma + " ingressos vendidos para a Seção das " + hora);
        } else {
            exibeErro();
        }
    }

    /**
     * filmesComMaisSessoes() Mostra os filmes com mais sessoes
     *
     */
    public static void filmesComMaisSessoes() {
        for (Filme filme : FILMES) {
            int somaQtd = 0;
            for (Sessao sessao : SESSOES) {
                if (sessao.getFilme().getNome().equals(filme.getNome())) {
                    somaQtd++;
                }
            }
            System.out.println("Filme " + filme.getNome() + " tem " + somaQtd + " Sessões");
        }
    }

    /**
     * salasMaisUtilizadas() Mostra as salas mais usadas
     *
     */
    public static void salasMaisUtilizadas() {
        for (Sala sala : SALAS) {
            int somaQtd = 0;
            for (Sessao sessao : SESSOES) {
                if (Objects.equals(sessao.getSala().getId(), sala.getId())) {
                    somaQtd++;
                }
            }
            System.out.println("A Sala de ID " + sala.getId() + " foi usada " + somaQtd + " vezes");
        }
    }

    /**
     * Mensagem de Erro de Entrada de Dados
     *
     * @return void
     */
    private static void exibeErro() {
        System.out.println("Entrada de dado inválida");
    }
}
