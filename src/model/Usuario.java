package model;
import atenticacao.Autentica;
import model.enuns.StatusAndamento;
import model.enuns.TipoUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa implements Autentica {

    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private List<ProgressoDorama> progressosDorama = new ArrayList<>();

    public Usuario(String nome, String nacionalidade, LocalDate dataNascimento,
                   String email, String senha, TipoUsuario tipo) {
        super(nome, nacionalidade, dataNascimento); // chama o construtor de Pessoa
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void listarUsuario(){
        System.out.println("Nome: "+this.getNome());
        System.out.println("Data de nascimento: "+this.getDataNascimento());
        System.out.println("Nacionalidade: "+this.getNacionalidade());
        System.out.println("Tipo de usuario: "+this.getTipoUsuario());

    }

    @Override
    public boolean autenticar(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

    public void setProgressoDoramaList(List<ProgressoDorama> progressosDorama) {
        this.progressosDorama = progressosDorama;
    }

    public void inicializarProgressoSerie(Dorama dorama, int totalEpisodios, int episodios, StatusAndamento status) {
        progressosDorama.add(new ProgressoSerie(dorama, totalEpisodios, episodios, status));
    }

    public void inicializarProgressoFilme(Dorama dorama, int duracao, int minutosAssistidos, StatusAndamento status) {
        progressosDorama.add(new ProgressoFilme(dorama, duracao, minutosAssistidos, status));
    }

    public ProgressoDorama getProgressoDoramaList(Dorama dorama) {
        for (ProgressoDorama progresso : progressosDorama) {
            if (progresso.getDorama().equals(dorama)) {
                return progresso;
            }
        }
        return null;
    }


}
