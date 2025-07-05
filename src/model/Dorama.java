package model;

import model.enuns.*;

import java.time.LocalDate;
import java.util.List;
public class Dorama {
    private String titulo;
    private String sinopse;
    private List<Generos> genero;
    private String emissora;
    private String paisOrigem;
    private LocalDate dataPublicacao;
    private String avaliacaoIMDB;
    private List<Ator> principaisAtores;
    private Diretor diretor;
    private List<StatusDorama> statusDorama;

    public Dorama(String titulo, String sinopse, List<Generos> genero, String emissora, String paisOrigem,
        LocalDate dataPublicacao, String avaliacaoIMDB, List<Ator> principaisAtores,
        Diretor diretor, List<StatusDorama> statusDorama){
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.emissora = emissora;
        this.paisOrigem = paisOrigem;
        this.dataPublicacao = dataPublicacao;
        this.avaliacaoIMDB = avaliacaoIMDB;
        this.principaisAtores = principaisAtores;
        this.diretor = diretor;
        this.statusDorama = statusDorama;

    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public List<Generos> getGenero() {
        return genero;
    }

    public String getEmissora() {
        return emissora;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public String getAvaliacaoIMDB() {
        return avaliacaoIMDB;
    }

    public List<Ator> getPrincipaisAtores() {
        return principaisAtores;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public List<StatusDorama> getStatusDorama() {
        return statusDorama;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setGenero(List<Generos> genero) {
        this.genero = genero;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setAvaliacaoIMDB(String avaliacaoIMDB) {
        this.avaliacaoIMDB = avaliacaoIMDB;
    }

    public void setPrincipaisAtores(List<Ator> principaisAtores) {
        this.principaisAtores = principaisAtores;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public void setStatusDorama(List<StatusDorama> statusDorama) {
        this.statusDorama = statusDorama;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

}
