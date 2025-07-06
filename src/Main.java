import model.Pessoa;
import model.enuns.Generos;
import model.enuns.StatusDorama;
import model.enuns.TipoUsuario;
import servicos.DoramaService;
import servicos.UsuarioService;
import servicos.AtorService;
import servicos.DiretorService;
//import servicos.DoramaService;
import model.Usuario;
import model.Dorama;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        DoramaService doramaService = new DoramaService();

        System.out.println("==================================");
        System.out.println("🎬 BEM-VINDO À BIBLIOTECA DE DORAMAS 🎬");
        System.out.println("==================================");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("================================");
            System.out.println("\n🎥 BIBLIOTECA DE DORAMAS");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Realizar Login");
            System.out.println("0 - Sair");
            System.out.println("================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
                System.out.println("---------------------------------");

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        //adicionar verificacao se deu certo o cadastro
                        Usuario user = usuarioService.criarPessoa();
                        if (user != null) {
                            System.out.println("Usuário cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar usuário.");
                        }
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        System.out.println("Informe o tipo de usuário:");
                        for (TipoUsuario tipo : TipoUsuario.values()) {
                            System.out.println(tipo.ordinal() + " - " + tipo.name());
                        }

                        String tipoUsuario = sc.nextLine().trim().toUpperCase();

                        TipoUsuario tipo;
                        tipo = TipoUsuario.valueOf(tipoUsuario);
                        if (tipo == TipoUsuario.ADMIN){
                            Usuario user = usuarioService.login();
                            if (user!=null){
                                menuGerenciadorAdmin(sc, usuarioService, doramaService, atorService, diretorService);
                            }else{
                                System.out.println("Usuário ou senha inválidos.");
                            }
                            System.out.println("---------------------------------");
                        }else{
                            menuDoramasConvidados(sc, doramaService);
                        }
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Saindo... Até a próxima 💜");
                        System.out.println("---------------------------------");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente de novo.");
                        System.out.println("---------------------------------");
                        break;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        sc.close();
    }

    public static void menuGerenciadorAdmin(Scanner sc, UsuarioService usuarioService, DoramaService doramaService, AtorService atorService, DiretorService diretorService) {
        System.out.println("Bem-vindo ao menu de gerenciamento!");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n🎥 BIBLIOTECA DE DORAMAS");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Doramas");
            System.out.println("0 - Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
                System.out.println("---------------------------------");
                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        menuPessoas(sc, usuarioService, atorService, diretorService);
                        System.out.println("---------------------------------");
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        menuDoramasAdmin(sc, doramaService);
                        System.out.println("---------------------------------");
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Saindo... Até a próxima 💜");
                        System.out.println("---------------------------------");
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente de novo.");
                        System.out.println("---------------------------------");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public static void menuPessoas(Scanner sc, UsuarioService usuarioService, AtorService atorService, DiretorService diretorService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n📋 MENU PESSOAS");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Atores");
            System.out.println("3 - Gerenciar Diretores");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        menuUsuarios(usuarioService, sc);
                        System.out.println("---------------------------------");
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        menuAtores(atorService, sc);
                        System.out.println("---------------------------------");
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        menuDiretores(diretorService, sc);
                        System.out.println("---------------------------------");
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("---------------------------------");
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
    // Submenu: USUÁRIOS
    public static void menuUsuarios(UsuarioService usuarioService, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n👤 MENU USUÁRIOS");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Editar usuário");
            System.out.println("3 - Excluir usuário");
            System.out.println("4 - Buscar usuário");
            System.out.println("5 - Listar usuários");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:{
                    System.out.println("---------------------------------");
                    Usuario user = usuarioService.criarPessoa();
                    if (user != null) {
                        System.out.println("Usuário cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar usuário.");
                    }
                    System.out.println("---------------------------------");
                    break;
                }
                case 2:{
                    System.out.println("---------------------------------");
                    int editou = usuarioService.editarUsuario();

                    if (editou == 1) System.out.println("Usuário editado com sucesso.");
                    else System.out.println("Usuário não encontrado.");
                    System.out.println("---------------------------------");
                    break;
                }
                case 3:{
                    System.out.println("---------------------------------");
                    int ok = usuarioService.excluirUser();
                    System.out.println(ok == 1 ? "Usuário excluído!" : "Usuário não encontrado.");
                    System.out.println("---------------------------------");
                    break;
                }
                case 4:{
                    System.out.println("---------------------------------");
                    System.out.print("Nome do usuario: ");
                    String nome = sc.nextLine();
                    Usuario u = usuarioService.buscarUser(nome);
                    if(u != null) u.listarUsuario();
                    else System.out.println("Usuário não encontrado.");
                    System.out.println("---------------------------------");
                    break;
                }
                case 5:{
                    System.out.println("---------------------------------");
                    usuarioService.listarUsers();
                    System.out.println("---------------------------------");
                    break;
                }
                case 0:{
                    System.out.println("---------------------------------");
                    System.out.println("Voltando...");
                    System.out.println("---------------------------------");
                    break;
                }
                default:{
                    System.out.println("Opção inválida.");
                }
            }
        }
    }

    // Submenu: ATORES
    public static void menuAtores(AtorService atorService, Scanner sc) {
        int opcao;


        do {
            System.out.println("=================================");
            System.out.println("\n🎭 MENU ATORES");
            System.out.println("1 - Cadastrar ator");
            System.out.println("2 - Editar ator");
            System.out.println("3 - Excluir ator");
            System.out.println("4 - Buscar ator");
            System.out.println("5 - Listar atores");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:{
                    System.out.println("---------------------------------");
                    atorService.criarAtor();
                    System.out.println("---------------------------------");
                    break;
                }
                case 2:{
                    System.out.println("---------------------------------");
                    atorService.editarAtor();
                    System.out.println("---------------------------------");
                    break;
                }
                case 3:{
                    System.out.println("---------------------------------");
                    atorService.excluirAtor();
                    System.out.println("---------------------------------");
                    break;
                }
                case 4:{
                    System.out.println("---------------------------------");
                    System.out.println("Informe o nome do ator que deseja busca: ");
                    String nome = sc.nextLine();
                    atorService.buscarAtor(nome);
                    System.out.println("---------------------------------");
                    break;

                }
                case 5:{
                    System.out.println("---------------------------------");
                    atorService.listarActors();
                    System.out.println("---------------------------------");
                    break;
                }
                case 0:{
                    System.out.println("---------------------------------");
                    System.out.println("Voltando...");
                    System.out.println("---------------------------------");
                    break;
                }
                default:{
                    System.out.println("---------------------------------");
                    System.out.println("Opção inválida.");
                    System.out.println("---------------------------------");
                    break;
                }
            }
        } while (opcao != 0);
    }

    // Submenu: DIRETORES
    public static void menuDiretores(DiretorService diretorService, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n🎬 MENU DIRETORES");
            System.out.println("1 - Cadastrar diretor");
            System.out.println("2 - Editar diretor");
            System.out.println("3 - Excluir diretor");
            System.out.println("4 - Buscar diretor");
            System.out.println("5 - Listar diretores");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        diretorService.criarDiretor();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        int sucesso = diretorService.editarDiretor();
                        System.out.println(sucesso == 1 ? "Diretor editado com sucesso." : "Diretor não encontrado.");
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        int excluido = diretorService.excluirDiretor();
                        System.out.println(excluido == 1 ? "Diretor removido!" : "Diretor não encontrado.");
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4:{
                        System.out.println("---------------------------------");
                        System.out.print("Nome do diretor: ");
                        String nome = sc.nextLine();
                        var diretor = diretorService.buscarDiretor(nome);
                        if(diretor != null) System.out.println(" Diretor encontrado: "+ diretor.getNome());
                        else System.out.println("Nenhum diretor com esse nome foi encontrado.");
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 5:{
                        System.out.println("---------------------------------");
                        diretorService.listarDiretores();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("---------------------------------");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    // Submenu: DORAMAS

    public static void menuDoramasAdmin(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n📺 MENU DORAMAS");
            System.out.println("1 - Menu Séries Doramas");
            System.out.println("2 - Menu Filmes Doramas");
            System.out.println("3 - Filtrar Doramas");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        menuSeries(sc, doramaService);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        menuFilmes(sc, doramaService);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        menuFiltrarDoramas(sc, doramaService);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("---------------------------------");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    public static void menuSeries(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n📺 MENU SÉRIES DORAMAS");
            System.out.println("1 - Cadastrar série dorama");
            System.out.println("2 - Listar séries doramas");
            System.out.println("3 - Editar série dorama");
            System.out.println("4 - Excluir série dorama");
            System.out.println("5 - Buscar série dorama");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        Dorama dorama = doramaService.criarSerieDorama();
                        if (dorama != null) {
                            System.out.println("Série dorama cadastrada com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar série dorama.");
                        }
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        doramaService.listarSeries();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        doramaService.editarSeries();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4:{
                        System.out.println("---------------------------------");
                        doramaService.excluirSerie();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("=================================");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("=================================");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    public static void menuFilmes(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n🎬 MENU FILMES DORAMAS");
            System.out.println("1 - Cadastrar filme dorama");
            System.out.println("2 - Listar filmes doramas");
            System.out.println("3 - Editar filme dorama");
            System.out.println("4 - Excluir filme dorama");
            System.out.println("5 - Buscar filme dorama");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        Dorama dorama = doramaService.criarFilmeDorama();
                        if (dorama != null) {
                            System.out.println("Filme dorama cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar filme dorama.");
                        }
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        doramaService.listarFilmes();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        doramaService.editarFilmes();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4:{
                        System.out.println("---------------------------------");
                        doramaService.excluirFilme();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("=================================");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("=================================");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    public static void menuDoramasConvidados(Scanner sc, DoramaService doramaService){
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n📺 MENU DORAMAS");
            System.out.println("1 - Listar series doramas");
            System.out.println("2 - Listar filmes doramas");
            System.out.println("3 - Filtrar doramas");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        System.out.println("---------------------------------");
                        doramaService.listarSeries();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        doramaService.listarFilmes();
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        menuFiltrarDoramas(sc, doramaService);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("=================================");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("=================================");
                        break;
                    }
                    default:{
                        System.out.println("---------------------------------");
                        System.out.println("Opção inválida. Tente novamente.");
                        System.out.println("---------------------------------");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

    public static void menuFiltrarDoramas(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n🔍 FILTRO DE DORAMAS");
            System.out.println("1 - Filtrar por gênero");
            System.out.println("2 - Filtrar por status");
            System.out.println("3 - Filtrar por intervalo de data");
            System.out.println("4 - Filtrar por Emissora");
            System.out.println("5 - Filtrar por País de Origem");
            System.out.println("6 - Filtrar por Avaliação IMDB");
            System.out.println("7 - Filtrar por Atores");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {

                    case 1: {
                        System.out.println("---------------------------------");
                        System.out.println("Selecione o gênero:");
                        for (int i = 0; i < Generos.values().length; i++) {
                            System.out.println(i + " - " + Generos.values()[i]);
                        }
                        String generoInput = sc.nextLine();
                        doramaService.filtrarGenero(generoInput);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        System.out.println("Selecione o status:");
                        for (int i = 0; i < StatusDorama.values().length; i++) {
                            System.out.println(i + " - " + StatusDorama.values()[i]);
                        }
                        String statusInput = sc.nextLine();
                        doramaService.filtrarStatus(statusInput);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        System.out.println("Digite o ano de lançamento (AAAA): ");
                        String anoLancamento = sc.nextLine();
                        LocalDate inicio = LocalDate.parse(anoLancamento);
                        System.out.println("Digite o ano final (AAAA): ");
                        String anoFinal = sc.nextLine();
                        LocalDate fim = LocalDate.parse(anoFinal);
                        doramaService.filtrarPorIntervaloDeData(inicio, fim);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o nome da emissora: ");
                        String emissora = sc.nextLine();
                        doramaService.filtrarEmissora(emissora);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 5: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o país de origem: ");
                        String paisOrigem = sc.nextLine();
                        doramaService.filtrarPais(paisOrigem);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 6: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite a avaliação IMDB: ");
                        String avaliacaoIMDB = sc.nextLine();
                        doramaService.filtrarIMDB(avaliacaoIMDB);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 7: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o nome do ator: ");
                        String nomeAtor = sc.nextLine();
                        doramaService.filtrarAtores(nomeAtor);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 0:{
                        System.out.println("---------------------------------");
                        System.out.println("Voltando ao menu principal...");
                        System.out.println("---------------------------------");
                        break;
                    }
                    default:{
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }

}
