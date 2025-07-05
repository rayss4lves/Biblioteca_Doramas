package model;

import java.util.List;

public class Diretor extends Pessoa {

    private List<String> pemios;

    public List<String> getPemios() {
        return pemios;
    }

    public void setPemios(List<String> pemios) {
        this.pemios = pemios;
    }

}
