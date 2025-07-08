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

    public void getUsers() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Usuários cadastrados:");
            for (Usuario user : users) {
                user.getNome();
            }
        }
    }

    public Usuario criarPessoa(){
        String email, senha;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();
        while (buscarNome(nome, users) == 1) {
            System.out.println("Nome já cadastrado. Por favor, insira um nome diferente: ");
            nome = sc.nextLine();
        }

        System.out.println("Insira a sua nascionalidade: ");
        String nascionalidade = sc.nextLine();

        System.out.println("Insira o dia do seu nascimento: ");
        String dia = sc.nextLine();
        System.out.println("Insira o mes do seu nascimento: ");
        String mes = sc.nextLine();
        System.out.println("Insira o ano do seu nascimento: ");
        String ano = sc.nextLine();
        String dataNascStr = ano + "-" + mes + "-" + dia;
        LocalDate dataNasc = LocalDate.parse(dataNascStr);

        System.out.println("Informe se voce e admin ou visitante: ");
        String tipostr = sc.nextLine().trim().toUpperCase();

        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(tipostr);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido. Digite ADMIN ou USUARIO.");
            return null;
        }

        if (tipo == TipoUsuario.ADMIN){
            String emailExemplo = "@gmail.com";
            System.out.println("Informe o seu email: ");
            email = sc.nextLine();
            while (!email.endsWith(emailExemplo)){
                System.out.println("Email deve conter '@gmail.com'");
                email = sc.nextLine();
            }
            while (buscarEmail(email, users) == 1) {
                System.out.println("Email já cadastrado. Por favor, insira um email diferente: ");
                email = sc.nextLine();
            }


            System.out.println("Informe a sua senha: ");
            senha = sc.nextLine();
        }else{
           email = null;
           senha = null;
        }

        Usuario user = new Usuario(nome, nascionalidade, dataNasc, email, senha, tipo);
        users.add(user);
        return user;
    }

    public static int buscarNome(String nome, List<Usuario> users) {
        int encontrou = 0;
        for (Usuario user : users) {
            if (user.getNome().equalsIgnoreCase(nome)) {
                encontrou = 1; // Nome encontrado
            }
        }
        return encontrou; // Nome não encontrado
    }

    public int buscarEmail(String email, List<Usuario> users) {
        int encontrou = 0;
        for (Usuario user : users) {
            if (user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
                encontrou = 1; // Email encontrado
            }
        }
        return encontrou; // Email não encontrado
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
                System.out.println("Login realizado com sucesso!");
                System.out.println("---------------------------------");
                return user;

            }
        }

        System.out.println("Email ou senha incorretos.");
        System.out.println("---------------------------------");
        return null;
    }


}
