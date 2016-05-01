package model;

import static util.FormatadorUtil.*;

/**
 * Classe Venda
 */
public class Venda {

    private Sessao sessao;
    private Integer qtdAssento;

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Integer getQtdAssento() {
        return qtdAssento;
    }

    public void setQtdAssento(Integer qtdAssento) {
        this.qtdAssento = qtdAssento;
    }

    public void descontaAssento(int n) {
        int qtd = this.sessao.getSala().getQtdAssentos();
        if (qtd > n) {
            this.sessao.getSala().setQtdAssentos(qtd - qtdAssento);
        } else {
            System.out.println("Quantidade de assentos disponiveis: " + qtd);
        }
    }

    @Override
    public String toString() {
        return "Sessao das: " + formataHora(sessao.getHora()) + ", Filme: " + sessao.getFilme();
    }

}
