package model;

import java.util.List;

/**
 * Classe Filme
 */
public class Filme {

    private Integer id;
    private String nome, genero, sinopse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return "Nome do Filme: " + nome + ", Genero: " + genero + "\nSinopse=" + sinopse;
    }
}
