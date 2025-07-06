package model;

import model.enuns.StatusAndamento;

public class ProgressoFilme extends ProgressoDorama {
    private int duracaoMinutos; // duração total do filme
    private int minutosAssistidos;

    public ProgressoFilme(Dorama dorama, int duracaoMinutos, int minutosAssistidos, StatusAndamento status) {
        super(dorama, status);
        this.duracaoMinutos = duracaoMinutos;
        this.minutosAssistidos = minutosAssistidos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public int getMinutosAssistidos() {
        return minutosAssistidos;
    }

    public void setMinutosAssistidos(int minutos) {
        if (minutos < 0) {
            this.minutosAssistidos = 0;
        } else if (minutos > duracaoMinutos) {
            this.minutosAssistidos = duracaoMinutos;
        } else {
            this.minutosAssistidos += minutos;
        }
        atualizarStatusAutomaticamente();
    }

    public void adicionarMinutosAssistidos(int minutos) {
        setMinutosAssistidos(this.minutosAssistidos + minutos);
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public double getProgresso() {
        if (duracaoMinutos > 0) {
            return (minutosAssistidos * 100.0) / duracaoMinutos;
        }
        return 0.0;
    }

    protected void atualizarStatusAutomaticamente() {
        if (minutosAssistidos >= duracaoMinutos && duracaoMinutos > 0) {
            setStatus(StatusAndamento.FINALIZADO);
        } else if (minutosAssistidos > 0) {
            setStatus(StatusAndamento.EM_ANDAMENTO);
        } else {
            setStatus(StatusAndamento.PAUSADO);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "🎬 %s (%d/%d minutos) - %.1f%% concluído - Status: %s",
                getDorama().getTitulo(),
                minutosAssistidos,
                duracaoMinutos,
                getProgresso(),
                getStatus()
        );
    }
}
