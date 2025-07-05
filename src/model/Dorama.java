package model;

import java.time.LocalDate;
import java.util.List;
 class Dorama {
    private String titulo;
    private String sinopse;
    private List<String> genero;
    private boolean status;
    private String emissora;
    private LocalDate dataPublicacao;
    private String avaliacaoIMDB;
    private List<Ator> principaisAtores;
    private Diretor diretor;
    private boolean assistido;
    private boolean favorito;
    private boolean assistindoAtualmente;
    private boolean queroAssistir;

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public List<String> getGenero() {
        return genero;
    }

    public boolean isStatus() {
        return status;
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

    public boolean isAssistido() {
        return assistido;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public boolean isAssistindoAtualmente() {
        return assistindoAtualmente;
    }

    public boolean isQueroAssistir() {
        return queroAssistir;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public void setAssistido(boolean assistido) {
        this.assistido = assistido;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public void setAssistindoAtualmente(boolean assistindoAtualmente) {
        this.assistindoAtualmente = assistindoAtualmente;
    }

    public void setQueroAssistir(boolean queroAssistir) {
        this.queroAssistir = queroAssistir;
    }


}
