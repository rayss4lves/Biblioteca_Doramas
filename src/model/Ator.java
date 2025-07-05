package model;

import model.enuns.TipoUsuario;

import java.time.LocalDate;
import java.util.List;

public class Ator extends Pessoa {
    private List<String> premios;
    public Ator(String nome, String nacionalidade, LocalDate dataNascimento, List<String> premios) {
        super(nome, nacionalidade, dataNascimento); // chama o construtor de Pessoa
        this.premios = premios;
    }
    public List<String> getPremios() {
        return premios;
    }

    public void setPemios(List<String> pemios) {
        this.premios = pemios;
    }

    public void listarActor(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Data de nascimento: "+this.getDataNascimento());
        System.out.println("Nacionalidade: "+this.getNacionalidade());
        System.out.println("Data de nascimento: "+this.getPremios());

    }

}
