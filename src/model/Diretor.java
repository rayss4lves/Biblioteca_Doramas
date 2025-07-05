package model;

import java.time.LocalDate;
import java.util.List;

public class Diretor extends Pessoa {

    private List<String> premios;

    public Diretor(String nome, String nacionalidade, LocalDate dataNascimento, List<String> premios) {
        super(nome, nacionalidade, dataNascimento); // chama o construtor de Pessoa
        this.premios = premios;
    }

    public List<String> getPremios() {
        return premios;
    }

    public void setPremios(List<String> premios) {
        this.premios = premios;
    }

    public void listarDiretor(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Data de nascimento: "+this.getDataNascimento());
        System.out.println("Nacionalidade: "+this.getNacionalidade());
        System.out.println("Data de nascimento: "+this.getPremios());

    }

}
