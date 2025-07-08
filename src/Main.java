import model.*;
import model.enuns.Generos;
import model.enuns.StatusAndamento;
import model.enuns.StatusDorama;
import model.enuns.TipoUsuario;
import servicos.DoramaService;
import servicos.UsuarioService;
import servicos.AtorService;
import servicos.DiretorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        DoramaService doramaService = new DoramaService();

        System.out.println("=====================================");
        System.out.println("🎬 BEM-VINDO À BIBLIOTECA DE DORAMAS 🎬");
        System.out.println("=====================================");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("================================");
            System.out.println("\n📚 MENU PRINCIPAL");
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
                break;
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
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        menuDoramasAdmin(usuarioService, sc, doramaService);
                        System.out.println("---------------------------------");
                        break;
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
                break;
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
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");
                        menuAtores(atorService, sc);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 3:{
                        System.out.println("---------------------------------");
                        menuDiretores(diretorService, sc);
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
                break;
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
                    break;
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
                break;
            }
        }
    }

    // Submenu: DORAMAS

    public static void menuProgresso(UsuarioService usuarioService, DoramaService doramaService, Scanner sc) {

        int opcao = -1;

        while (opcao != 0){
            System.out.println("=================================");
            System.out.println("\nMENU PROGRESSO DORAMAS");
            System.out.println("1 - Iniciar Progressos dos doramas");
            System.out.println("2 - Visualizar Progresso dos usuários");
            System.out.println("3 - Atualizar Progresso dos usuários");
            System.out.println("0 - Voltar");
            System.out.println("=================================");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1: {
                        System.out.println("---------------------------------");
                        System.out.println("📊 Visualizando progressos dos usuários");

                        usuarioService.getUsers();
                        System.out.println("Digite o nome do usuário:");
                        String nomeUsuario = sc.nextLine();
                        Usuario usuario = usuarioService.buscarUser(nomeUsuario);


                        if (usuario == null) {
                            System.out.println("Usuário não encontrado.");
                            System.out.println("---------------------------------");
                            break;
                        }

                        System.out.println("Selecione o tipo de dorama:");
                        System.out.println("1 - Série");
                        System.out.println("2 - Filme");
                        int tipoDorama = Integer.parseInt(sc.nextLine());


                        if (tipoDorama == 1) {
                            doramaService.listarSeries();

                            System.out.println("Digite o título do dorama:");
                            String tituloDorama = sc.nextLine();

                            SerieDorama dorama = doramaService.buscarSerie(tituloDorama);
                            usuario.inicializarProgressoSerie(dorama, dorama.getQtdEpisodios(), 0, StatusAndamento.EM_ANDAMENTO);
                        } else if (tipoDorama == 2) {
                            doramaService.listarFilmes();

                            System.out.println("Digite o título do dorama:");
                            String tituloDorama = sc.nextLine();
                            FilmeDorama dorama = doramaService.buscarFilme(tituloDorama);
                            usuario.inicializarProgressoFilme(dorama, dorama.getDuracao(), 0, StatusAndamento.EM_ANDAMENTO);
                        } else {
                            System.out.println("Tipo de dorama inválido.");
                            System.out.println("---------------------------------");
                            break;
                        }

                        System.out.println("Progresso iniciado.");
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:
                    {
                            System.out.println("---------------------------------");
                            System.out.println("📊 Visualizando progresso dos usuários");

                            usuarioService.getUsers();
                            System.out.println("Digite o nome do usuário:");
                            String nomeUsuario = sc.nextLine();
                            Usuario usuario = usuarioService.buscarUser(nomeUsuario);

                            if (usuario == null) {
                                System.out.println("Usuário não encontrado.");
                                break;
                            }

                            System.out.println("Serie ou Filme?");
                            String tipo = sc.nextLine().trim().toLowerCase();

                            if (tipo.equals("serie")) {
                                doramaService.listarSeries();
                                System.out.println("Digite o título do dorama:");
                                String tituloDorama = sc.nextLine();
                                SerieDorama dorama = doramaService.buscarSerie(tituloDorama);
                                verProgressoAtual(usuario, dorama);
                            } else {
                                doramaService.listarFilmes();
                                System.out.println("Digite o título do dorama:");
                                String tituloDorama = sc.nextLine();
                                FilmeDorama dorama = doramaService.buscarFilme(tituloDorama);
                                verProgressoAtual(usuario, dorama);
                            }

                            System.out.println("---------------------------------");
                            break;

                    }
                    case 3:{
                            System.out.println("---------------------------------");
                            System.out.println("Atualizando o progresso dos usuários");
                            usuarioService.getUsers();

                            System.out.println("Digite o nome do usuário:");
                            String nomeUsuario = sc.nextLine();
                            Usuario usuario = usuarioService.buscarUser(nomeUsuario);

                            if (usuario == null) {
                                System.out.println("Usuário não encontrado.");
                                break;
                            }

                            System.out.println("Serie ou Filme?");
                            String tipo = sc.nextLine().trim().toLowerCase();

                            if (tipo.equals("serie")) {
                                doramaService.listarSeries();
                                System.out.println("Digite o título do dorama:");
                                String tituloDorama = sc.nextLine();
                                SerieDorama dorama = doramaService.buscarSerie(tituloDorama);
                                atualizarProgressoSerie(usuario, dorama, sc);
                            } else {
                                doramaService.listarFilmes();
                                System.out.println("Digite o título do dorama:");
                                String tituloDorama = sc.nextLine();
                                FilmeDorama dorama = doramaService.buscarFilme(tituloDorama);

                                ProgressoDorama progresso = usuario.getProgressoDoramaList(dorama);
                                if (progresso == null) {
                                    System.out.println("⚠️ O usuário não tem progresso para este dorama.");
                                    break;
                                }

                                System.out.println("Quantos minutos você assistiu agora?");
                                int minutosAssistidos = Integer.parseInt(sc.nextLine());

                                atualizarProgressoFilme(usuario, dorama, sc);
                            }

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
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                break;
            }
        }


    }

    public static void menuDoramasAdmin(UsuarioService usuarioService, Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=================================");
            System.out.println("\n📺 MENU DORAMAS");
            System.out.println("1 - Menu Séries Doramas");
            System.out.println("2 - Menu Filmes Doramas");
            System.out.println("3 - Filtrar Doramas");
            System.out.println("4 - Gerenciar Progresso dos Doramas");
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
                    case 4:{
                        System.out.println("---------------------------------");
                        menuProgresso(usuarioService, doramaService, sc);
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
                break;
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
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                break;
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
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                break;
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
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                break;
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

                        for (int i = 0; i < Generos.values().length; i++) {
                            System.out.println(" - " + Generos.values()[i]);
                        }
                        System.out.println("Selecione o gênero:");
                        String generoInput = sc.nextLine();

                        Generos genero = null;
                        try {
                            genero = Generos.valueOf(generoInput.toUpperCase());
                            List<Dorama> doramas= doramaService.filtrarGenero(generoInput);
                            doramaService.listarTodosDoramas(doramas);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Gênero inválido! Tente novamente com um dos listados.");
                        }

                        System.out.println("---------------------------------");
                        break;
                    }
                    case 2:{
                        System.out.println("---------------------------------");

                        for (int i = 0; i < StatusDorama.values().length; i++) {
                            System.out.println(" - " + StatusDorama.values()[i]);
                        }

                        StatusDorama status = null;

                        System.out.println("Selecione o status:");
                        String statusInput = sc.nextLine();

                        try {
                            status = StatusDorama.valueOf(statusInput.toUpperCase());
                            List<Dorama> doramas= doramaService.filtrarGenero(statusInput);
                            doramaService.listarTodosDoramas(doramas);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Gênero inválido! Tente novamente com um dos listados.");
                        }

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
                        List<Dorama> doramas= doramaService.filtrarPorIntervaloDeData(inicio, fim);
                        doramaService.listarTodosDoramas(doramas);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 4:
                    {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o nome da emissora: ");
                        String emissora = sc.nextLine();
                        List<Dorama> doramas = doramaService.filtrarEmissora(emissora);
                        doramaService.listarTodosDoramas(doramas);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 5: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o país de origem: ");
                        String paisOrigem = sc.nextLine();
                        List<Dorama> doramas = doramaService.filtrarPais(paisOrigem);
                        doramaService.listarTodosDoramas(doramas);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 6: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite a avaliação IMDB: ");
                        String avaliacaoIMDB = sc.nextLine();
                        List<Dorama> doramas = doramaService.filtrarIMDB(avaliacaoIMDB);
                        doramaService.listarTodosDoramas(doramas);
                        System.out.println("---------------------------------");
                        break;
                    }
                    case 7: {
                        System.out.println("---------------------------------");
                        System.out.println("Digite o nome do ator: ");
                        String nomeAtor = sc.nextLine();
                        List<Dorama> doramas = doramaService.filtrarAtores(nomeAtor);
                        doramaService.listarTodosDoramas(doramas);
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

    public static void verProgressoAtual(Usuario usuario, Dorama dorama) {
        ProgressoDorama progresso = usuario.getProgressoDoramaList(dorama);

        if (progresso != null) {
            System.out.println(progresso.toString());
        } else {
            System.out.println("O usuário não tem progresso para este dorama.");
        }

        System.out.println("---------------------------------");
    }


    public static void atualizarProgressoSerie(Usuario usuario, Dorama dorama, Scanner sc) {
        ProgressoDorama progresso = usuario.getProgressoDoramaList(dorama);

        if (progresso instanceof ProgressoSerie) {
            ProgressoSerie progressoSerie = (ProgressoSerie) progresso;

            System.out.println("Quantidade de episódios assistidos: " + progressoSerie.getEpisodiosAssistidos());
            System.out.println("Quantos episódios você assistiu agora? ");
            int novosEpisodios = Integer.parseInt(sc.nextLine());

            progressoSerie.setEpisodiosAssistidos(progressoSerie.getEpisodiosAssistidos() + novosEpisodios);
            System.out.println("✅ Progresso atualizado com sucesso!");
        } else {
            System.out.println("⚠️ O progresso informado não é de uma série.");
        }
    }


    public static void atualizarProgressoFilme(Usuario usuario, Dorama dorama, Scanner sc) {
        ProgressoDorama progresso = usuario.getProgressoDoramaList(dorama);

        if (progresso instanceof ProgressoFilme) {
            ProgressoFilme progressoFilme = (ProgressoFilme) progresso;

            System.out.println("Minutos assistidos até agora: " + progressoFilme.getMinutosAssistidos());
            System.out.println("Quantos minutos você assistiu agora?");
            int novosMinutos = Integer.parseInt(sc.nextLine());

            progressoFilme.adicionarMinutosAssistidos(novosMinutos);
            System.out.println("✅ Progresso atualizado com sucesso!");
        } else {
            System.out.println("⚠️ O progresso informado não é de um filme.");
        }
    }



}
