package model;

import model.enuns.StatusAndamento;

public class ProgressoSerie extends ProgressoDorama {

    private int episodiosAssistidos;
    private int totalEpisodios;

    public ProgressoSerie(Dorama dorama, int totalEpisodios, int episodiosAssistidos, StatusAndamento status) {
        super(dorama, status);
        this.totalEpisodios = totalEpisodios;
        this.episodiosAssistidos = episodiosAssistidos;
    }

    public int getEpisodiosAssistidos() {
        return episodiosAssistidos;
    }

    public void setEpisodiosAssistidos(int episodiosAssistidos) {
        if (episodiosAssistidos < 0) {
            this.episodiosAssistidos = 0;
        } else if (episodiosAssistidos > totalEpisodios) {
            this.episodiosAssistidos = totalEpisodios;
        } else {
            this.episodiosAssistidos = episodiosAssistidos;
        }

        atualizarStatusAutomaticamente();
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public void setTotalEpisodios(int totalEpisodios) {
        this.totalEpisodios = totalEpisodios;
        atualizarStatusAutomaticamente();
    }

    public void marcarEpisodioAssistido() {
        if (episodiosAssistidos < totalEpisodios) {
            episodiosAssistidos++;
            atualizarStatusAutomaticamente();
        }
    }

    public double getProgresso() {
        if (totalEpisodios > 0) {
            return (episodiosAssistidos * 100.0) / totalEpisodios;
        }
        return 0.0;
    }

    public void atualizarStatusAutomaticamente() {
        if (episodiosAssistidos >= totalEpisodios && totalEpisodios > 0) {
            setStatus(StatusAndamento.FINALIZADO);
        } else if (episodiosAssistidos > 0) {
            setStatus(StatusAndamento.EM_ANDAMENTO);
        } else {
            setStatus(StatusAndamento.PAUSADO);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "📺 %s (%d/%d episódios) - %.1f%% concluído - Status: %s",
                getDorama().getTitulo(),
                episodiosAssistidos,
                totalEpisodios,
                getProgresso(),
                getStatus()
        );
    }
}