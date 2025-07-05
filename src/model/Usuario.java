package model;
import atenticacao.Autentica;
import model.enuns.TipoUsuario;

public class Usuario extends Pessoa implements Autentica {

    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

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

    @Override
    public boolean autenticar(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }

}
