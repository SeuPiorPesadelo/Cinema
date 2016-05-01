package model;

import java.util.Date;
import static util.FormatadorUtil.*;

/**
 * Classe Sessao
 */
public class Sessao {

    private Sala sala;
    private Date hora;
    private Filme filme;
    private int qtdVendida;

    public int getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public String toString() {
        return "Sala: " + sala + ", Hora: " + formataHora(hora) + ", Filme: " + filme.getNome();
    }

}
