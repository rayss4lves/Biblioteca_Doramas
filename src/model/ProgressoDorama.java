package model;

import model.enuns.StatusAndamento;

public class ProgressoDorama {
    private final Dorama dorama;
    private StatusAndamento status;

    public ProgressoDorama(Dorama dorama, StatusAndamento status) {
        this.dorama = dorama;
        this.status = status;
    }

    public Dorama getDorama() {
        return dorama;
    }

    public StatusAndamento getStatus() {
        return status;
    }

    public void setStatus(StatusAndamento status) {
        this.status = status;
    }


}
