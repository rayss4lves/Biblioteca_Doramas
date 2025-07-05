package servicos;
import model.Usuario;
import model.enuns.TipoUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioService {
    List<Usuario> users = new ArrayList<>();

    Scanner sc = new Scanner(System.in);
    public void criarPessoa(){
        String email, senha;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();

        System.out.println("Insira a sua nascionalidade: ");
        String nascionalidade = sc.nextLine();

        System.out.println("Insira a data do seu nascimento: ");
        String dataNascStr = sc.nextLine();
        LocalDate dataNasc = LocalDate.parse(dataNascStr);

        System.out.println("Informe se voce e admin ou visitante: ");
        String tipostr = sc.nextLine().trim().toUpperCase();

        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(tipostr);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido. Digite ADMIN ou USUARIO.");
            return;
        }

        if (tipo == TipoUsuario.ADMIN){
            System.out.println("Informe o seu email: ");
            email = sc.nextLine();

            System.out.println("Informe a sua senha: ");
            senha = sc.nextLine();
        }else{
           email = null;
           senha = null;
        }

        Usuario user = new Usuario(nome, nascionalidade, dataNasc, email, senha, tipo);
        users.add(user);
    }

    public int excluirUser(){
        int excluiu = 0;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();
        Usuario user = buscarUser(nome);
        if (user != null){
            this.users.remove(user);
            excluiu = 1;
        }

        return excluiu;
    }

    public int editarUsuario(){
        int editou = 0;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();
        Usuario user = buscarUser(nome);
        if (user != null) {
            menuEdicao(user);
            editou = 1;
        }
        return editou;
    }

    public void menuEdicao(Usuario user){
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nO que deseja editar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Nacionalidade");
            System.out.println("3 - Data de nascimento");
            System.out.println("4 - E-mail");
            System.out.println("5 - Senha");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        user.setNome(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nova nacionalidade: ");
                        user.setNacionalidade(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                        try {
                            LocalDate novaData = LocalDate.parse(sc.nextLine());
                            user.setDataNascimento(novaData);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido!");
                        }
                    }
                    case 4 -> {
                        if (user.getTipoUsuario() == TipoUsuario.ADMIN) {
                            System.out.print("Novo e-mail: ");
                            user.setEmail(sc.nextLine());
                        } else {
                            System.out.println("Usuário visitante não pode editar e-mail.");
                        }
                    }
                    case 5 -> {
                        if (user.getTipoUsuario() == TipoUsuario.ADMIN) {
                            System.out.print("Nova senha: ");
                            user.setSenha(sc.nextLine());
                        } else {
                            System.out.println("Usuário visitante não possui senha.");
                        }
                    }
                    case 0 -> System.out.println("Edição finalizada!");
                    default -> System.out.println("Opção inválida.");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite um número.");
            }}
    }

    public void listarUsers(){
        for (Usuario us:this.users){
            us.listarUsuario();
        }
    }

    public Usuario buscarUser(String nome){
        Usuario userExcluir = null;
        for (Usuario us:this.users){
            if (nome.equals(us.getNome())){
                userExcluir = us;
            }
        }
        return userExcluir;
    }

    public Usuario login() {
        System.out.println("Informe o email:");
        String email = sc.nextLine();

        System.out.println("Informe a senha:");
        String senha = sc.nextLine();

        for (Usuario user : users) {
            if (user.autenticar(email, senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + user.getNome());
                return user;
            }
        }

        System.out.println("Email ou senha incorretos.");
        return null;
    }


}
