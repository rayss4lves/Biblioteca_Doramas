import servicos.UsuarioService;
import servicos.AtorService;
import servicos.DiretorService;
//import servicos.DoramaService;
import model.Usuario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        //DoramaService doramaService = new DoramaService();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🎥 BIBLIOTECA DE DORAMAS");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Atores");
            System.out.println("3 - Gerenciar Diretores");
            System.out.println("4 - Gerenciar Doramas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> menuUsuarios(usuarioService, sc);
                    case 2 -> menuAtores(atorService, sc);
                    case 3 -> menuDiretores(diretorService, sc);
                    case 0 -> System.out.println("Saindo... Até a próxima 💜");
                    default -> System.out.println("Opção inválida. Tente de novo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        sc.close();
    }

    // Submenu: USUÁRIOS
    public static void menuUsuarios(UsuarioService usuarioService, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n👤 MENU USUÁRIOS");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Editar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> usuarioService.criarPessoa();
                case 2 -> usuarioService.editarUsuario();
                case 3 -> {
                    int ok = usuarioService.excluirUser();
                    System.out.println(ok == 1 ? "Usuário excluído!" : "Usuário não encontrado.");
                }
                case 4 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    Usuario u = usuarioService.buscarUser(nome);
                    System.out.println(u != null ? "Encontrado: " + u.getNome() : "Usuário não encontrado.");
                }
                case 5 -> usuarioService.listarUsers();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Submenu: ATORES
    public static void menuAtores(AtorService atorService, Scanner sc) {
        int opcao;


        do {
            System.out.println("\n🎭 MENU ATORES");
            System.out.println("1 - Cadastrar ator");
            System.out.println("2 - Editar ator");
            System.out.println("3 - Excluir ator");
            System.out.println("4 - Buscar ator");
            System.out.println("5 - Listar atores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> atorService.criarAtor();
                case 2 -> atorService.editarAtor();
                case 3 -> atorService.excluirAtor();
                case 4 -> {
                    System.out.println("Informe o nome do ator que deseja busca: ");
                    String nome = sc.nextLine();
                    atorService.buscarAtor(nome);
                } // se quiser exibir direto
                case 5 -> atorService.listarActors();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }



    // Submenu: DIRETORES
    public static void menuDiretores(DiretorService diretorService, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🎬 MENU DIRETORES");
            System.out.println("1 - Cadastrar diretor");
            System.out.println("2 - Editar diretor");
            System.out.println("3 - Excluir diretor");
            System.out.println("4 - Buscar diretor");
            System.out.println("5 - Listar diretores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> diretorService.criarDiretor();
                    case 2 -> {
                        int sucesso = diretorService.editarDiretor();
                        System.out.println(sucesso == 1 ? "Diretor editado com sucesso." : "Diretor não encontrado.");
                    }
                    case 3 -> {
                        int excluido = diretorService.excluirDiretor();
                        System.out.println(excluido == 1 ? "Diretor removido!" : "Diretor não encontrado.");
                    }
                    case 4 -> {
                        System.out.print("Nome do diretor: ");
                        String nome = sc.nextLine();
                        var diretor = diretorService.buscarDiretor(nome);
                        System.out.println(diretor != null
                                ? "🎯 Diretor encontrado: " + diretor.getNome()
                                : "Nenhum diretor com esse nome foi encontrado.");
                    }
                    case 5 -> diretorService.listarDiretores();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }


    // Submenu: DORAMAS

}
