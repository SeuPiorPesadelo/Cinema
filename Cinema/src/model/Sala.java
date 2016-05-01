package model;

import java.util.List;

/**
 * Classe Filme
 */
public class Sala {

    private Integer id, qtdAssentos;
    private List<Sessao> sessoes;

    public List<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtdAssentos() {
        return qtdAssentos;
    }

    public void setQtdAssentos(Integer qtdAssentos) {
        this.qtdAssentos = qtdAssentos;
    }

    public void diminuiAssentos(int n) {
        this.qtdAssentos -= n;
    }

    @Override
    public String toString() {
        return "Qauntidade de Assentos: " + qtdAssentos;
    }

}
