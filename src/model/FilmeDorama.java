package model;

import model.enuns.Generos;
import model.enuns.StatusDorama;

import java.time.LocalDate;
import java.util.List;

public class FilmeDorama extends Dorama{

    private int duracao;

    public FilmeDorama(String titulo, String sinopse, List<Generos> genero, String emissora, String paisOrigem,
                       LocalDate dataPublicacao, String avaliacaoIMDB, List<Ator> principaisAtores, Diretor diretor,
                       List<StatusDorama> status, int duracao) {
        super(titulo, sinopse, genero, emissora, paisOrigem, dataPublicacao, avaliacaoIMDB,
                principaisAtores, diretor, status);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        if (duracao < 0) {
            this.duracao = 0;
        } else {
            this.duracao = duracao;
        }
    }

    public void listaDoramasFormatado(){
        System.out.println("Título: " + getTitulo());
        System.out.println("Sinopse: " + getSinopse());
        System.out.println("Gêneros: " + getGenero());
        System.out.println("Emissora: " + getEmissora());
        System.out.println("País de Origem: " + getPaisOrigem());
        System.out.println("Data de Publicação: " + getDataPublicacao());
        System.out.println("Avaliação IMDB: " + getAvaliacaoIMDB());
        System.out.println("Duração: " + getDuracao());
        System.out.println("Diretor: " + getDiretor().getNome());
        System.out.println("Atores Principais: ");
        for (Ator ator : getPrincipaisAtores()) {
            System.out.println("- " + ator.getNome());
        }
    }
}
