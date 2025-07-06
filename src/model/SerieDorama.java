package model;
import model.enuns.Generos;
import model.enuns.StatusDorama;

import java.time.LocalDate;
import java.util.List;

public class SerieDorama extends Dorama{

    private int qtdTemporadas;
    private int qtdEpisodios;

    public SerieDorama(String titulo, String sinopse, List<Generos> genero, String emissora, String paisOrigem,
                       LocalDate dataPublicacao, String avaliacaoIMDB, List<Ator> principaisAtores, Diretor diretor,
                       List<StatusDorama> status,
                       int qtdTemporadas, int qtdEpisodios) {
        super(titulo, sinopse, genero, emissora, paisOrigem, dataPublicacao, avaliacaoIMDB,
                principaisAtores, diretor, status);
        this.qtdTemporadas = qtdTemporadas;
        this.qtdEpisodios = qtdEpisodios;
    }

    public int getQtdTemporadas() {
        return qtdTemporadas;
    }

    public void setQtdTemporadas(int qtdTemporadas) {
        this.qtdTemporadas = qtdTemporadas;
    }

    public int getQtdEpisodios() {
        return qtdEpisodios;
    }

    public void setQtdEpisodios(int qtdEpisodios) {
        this.qtdEpisodios = qtdEpisodios;
    }

    public void listaDoramasFormatado(){
        System.out.println("Título: " + getTitulo());
        System.out.println("Sinopse: " + getSinopse());
        System.out.println("Gêneros: " + getGenero());
        System.out.println("Emissora: " + getEmissora());
        System.out.println("País de Origem: " + getPaisOrigem());
        System.out.println("Data de Publicação: " + getDataPublicacao());
        System.out.println("Avaliação IMDB: " + getAvaliacaoIMDB());
        System.out.println("Quantidade de Temporadas: " + getQtdTemporadas());
        System.out.println("Quantidade de Episódios: " + getQtdEpisodios());
        System.out.println("Diretor: " + getDiretor().getNome());
        System.out.println("Atores Principais: ");
        for (Ator ator : getPrincipaisAtores()) {
            System.out.println("- " + ator.getNome());
        }
    }
}
