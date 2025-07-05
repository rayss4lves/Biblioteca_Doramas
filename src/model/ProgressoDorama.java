package model;

import model.enuns.StatusAndamento;

public class ProgressoDorama {
    private Usuario usuario;
    private Dorama dorama;

    private int duracao; // Duração total em minutos
    private int episodiosAssistidos;
    private int totalEpisodios;
    private StatusAndamento status; // "EM ANDAMENTO", "PAUSADO", "FINALIZADO"

    public int getEpisodiosAssistidos() {
        return episodiosAssistidos;
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
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

    public void setEpisodiosAssistidos(int episodiosAssistidos) {
        if (episodiosAssistidos > totalEpisodios) {
            this.episodiosAssistidos = totalEpisodios;
        } else if (episodiosAssistidos < 0) {
            this.episodiosAssistidos = 0;
        } else {
            this.episodiosAssistidos = episodiosAssistidos;
        }

        if (this.episodiosAssistidos == totalEpisodios) {
            this.status = StatusAndamento.FINALIZADO;
        }
    }

    public void setTotalEpisodios(int totalEpisodios) {
        this.totalEpisodios = totalEpisodios;
    }
    public StatusAndamento getStatus() {
        return status;
    }
    public void setStatus(StatusAndamento status) {
        this.status = status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Dorama getDorama() {
        return dorama;
    }
    public void setDorama(Dorama dorama) {
        this.dorama = dorama;
    }


    public ProgressoDorama(Usuario usuario, Dorama dorama, int totalEpisodios, StatusAndamento status) {
        this.usuario = usuario;
        this.dorama = dorama;
        this.totalEpisodios = totalEpisodios;
        this.episodiosAssistidos = 0;
        this.status = status;
    }

    public void marcarEpisodioAssistido() {
        if (episodiosAssistidos < totalEpisodios) {
            episodiosAssistidos++;
            if (episodiosAssistidos == totalEpisodios) {
                this.status = StatusAndamento.FINALIZADO;
            }
        }
    }

    public double getProgresso() {
        if (this.totalEpisodios !=0) {
            return (double) episodiosAssistidos / totalEpisodios * 100;
        }else return 0.0;
    }

    @Override
    public String toString() {
        return String.format(
                "📺 %s (%d/%d episódios) - %.1f%% concluído - Status: %s",
                dorama.getTitulo(),
                episodiosAssistidos,
                totalEpisodios,
                getProgresso(),
                getStatus() // se implementar com descrição
        );
    }


}


