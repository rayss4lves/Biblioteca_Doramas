import model.enuns.Generos;
import model.enuns.StatusDorama;
import model.enuns.TipoUsuario;
import servicos.DoramaService;
import servicos.UsuarioService;
import servicos.AtorService;
import servicos.DiretorService;
//import servicos.DoramaService;
import model.Usuario;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UsuarioService usuarioService = new UsuarioService();
        AtorService atorService = new AtorService();
        DiretorService diretorService = new DiretorService();
        DoramaService doramaService = new DoramaService();

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🎥 BIBLIOTECA DE DORAMAS");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Realizar Login");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        //adicionar verificacao se deu certo o cadastro
                        usuarioService.criarPessoa();
                        break;
                    }
                    case 2:{
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
                                menuGerenciadorAdmin(sc, usuarioService, doramaService);
                            }else{
                                System.out.println("Usuário ou senha inválidos.");
                            }
                        }else{
                            menuDoramasConvidados(sc, doramaService);
                        }
                    }
                    case 0:{
                        System.out.println("Saindo... Até a próxima 💜");
                        break;
                    }
                    default:{
                        System.out.println("Opção inválida. Tente de novo.");
                        break;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
        sc.close();
    }

    public static void menuGerenciadorAdmin(Scanner sc, UsuarioService usuarioService, DoramaService doramaService) {
        System.out.println("Bem-vindo ao menu de gerenciamento!");
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🎥 BIBLIOTECA DE DORAMAS");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Doramas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:{
                        menuUsuarios(usuarioService, sc);
                    }
                    case 2:{
                        menuDoramasAdmin(sc, doramaService);
                    }
                    case 0:{
                        System.out.println("Saindo... Até a próxima 💜");
                    }
                    default:{
                        System.out.println("Opção inválida. Tente de novo.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
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
                case 1:{
                    usuarioService.criarPessoa();
                }
                case 2:{
                    usuarioService.editarUsuario();
                }
                case 3:{
                    {
                        int ok = usuarioService.excluirUser();
                        System.out.println(ok == 1 ? "Usuário excluído!" : "Usuário não encontrado.");
                    }
                }
                case 4:{
                    {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        Usuario u = usuarioService.buscarUser(nome);
                        System.out.println(u != null ? "Encontrado: " + u.getNome() : "Usuário não encontrado.");
                    }
                }
                case 5:{
                    usuarioService.listarUsers();
                }
                case 0:{
                    System.out.println("Voltando...");
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
                case 1:{
                    atorService.criarAtor();
                }
                case 2:{
                    atorService.editarAtor();
                }
                case 3:{
                    atorService.excluirAtor();
                }
                case 4:{
                    {
                        System.out.println("Informe o nome do ator que deseja busca: ");
                        String nome = sc.nextLine();
                        atorService.buscarAtor(nome);
                    } // se quiser exibir direto
                }
                case 5:{
                    atorService.listarActors();
                }
                case 0:{
                    System.out.println("Voltando...");
                }
                default:{
                    System.out.println("Opção inválida.");
                }
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
                    case 1:{
                        diretorService.criarDiretor();
                    }
                    case 2:{
                        {
                            int sucesso = diretorService.editarDiretor();
                            System.out.println(sucesso == 1 ? "Diretor editado com sucesso." : "Diretor não encontrado.");
                        }
                    }
                    case 3:{
                        {
                            int excluido = diretorService.excluirDiretor();
                            System.out.println(excluido == 1 ? "Diretor removido!" : "Diretor não encontrado.");
                        }
                    }
                    case 4:{
                        {
                            System.out.print("Nome do diretor: ");
                            String nome = sc.nextLine();
                            var diretor = diretorService.buscarDiretor(nome);
                            System.out.println(diretor != null
                                    ? "🎯 Diretor encontrado: " + diretor.getNome()
                                    : "Nenhum diretor com esse nome foi encontrado.");
                        }
                    }
                    case 5:{
                        diretorService.listarDiretores();
                    }
                    case 0:{
                        System.out.println("Voltando ao menu principal...");
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


    // Submenu: DORAMAS

    public static void menuDoramasAdmin(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n📺 MENU DORAMAS");
            System.out.println("1 - Cadastrar série dorama");
            System.out.println("2 - Cadastrar filme dorama");
            System.out.println("3 - Listar séries doramas");
            System.out.println("4 - Listar filmes doramas");
            System.out.println("5 - Filtrar doramas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        doramaService.criarSerieDorama();
                        break;
                    }
                    case 2:{
                        doramaService.criarFilmeDorama();
                    }
                    case 3:{
                        doramaService.listarSeries();
                    }
                    case 4:{
                        doramaService.listarFilmes();
                    }
                    case 5:{
                        menuFiltrarDoramas(sc, doramaService);
                    }
                    case 0:{
                        System.out.println("Voltando ao menu principal...");
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

    public static void menuDoramasConvidados(Scanner sc, DoramaService doramaService){
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n📺 MENU DORAMAS");
            System.out.println("1 - Listar series doramas");
            System.out.println("2 - Listar filmes doramas");
            System.out.println("3 - Filtrar doramas");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1:{
                        doramaService.listarSeries();
                    }
                    case 2:{
                        doramaService.listarFilmes();
                    }
                    case 3:{
                        menuFiltrarDoramas(sc, doramaService);
                    }
                    case 0:{
                        System.out.println("Voltando ao menu principal...");
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

    public static void menuFiltrarDoramas(Scanner sc, DoramaService doramaService) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n🔍 FILTRO DE DORAMAS");
            System.out.println("1 - Filtrar por gênero");
            System.out.println("2 - Filtrar por status");
            System.out.println("3 - Filtrar por intervalo de data");
            System.out.println("4 - Filtrar por Emissora");
            System.out.println("5 - Filtrar por País de Origem");
            System.out.println("6 - Filtrar por Avaliação IMDB");
            System.out.println("7 - Filtrar por Atores");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {

                    case 1: {
                        System.out.println("Selecione o gênero:");
                        for (int i = 0; i < Generos.values().length; i++) {
                            System.out.println(i + " - " + Generos.values()[i]);
                        }
                        String generoInput = sc.nextLine();
                        doramaService.filtrarGenero(generoInput);
                        break;
                    }
                    case 2:{
                        System.out.println("Selecione o status:");
                        for (int i = 0; i < StatusDorama.values().length; i++) {
                            System.out.println(i + " - " + StatusDorama.values()[i]);
                        }
                        String statusInput = sc.nextLine();
                        doramaService.filtrarStatus(statusInput);
                        break;
                    }
                    case 3:{
                        System.out.println("Digite o ano de lançamento (AAAA): ");
                        String anoLancamento = sc.nextLine();
                        LocalDate inicio = LocalDate.parse(anoLancamento);
                        System.out.println("Digite o ano final (AAAA): ");
                        String anoFinal = sc.nextLine();
                        LocalDate fim = LocalDate.parse(anoFinal);
                        doramaService.filtrarPorIntervaloDeData(inicio, fim);
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Digite o nome da emissora: ");
                        String emissora = sc.nextLine();
                        doramaService.filtrarEmissora(emissora);
                        break;
                    }
                    case 5: {
                        System.out.println("Digite o país de origem: ");
                        String paisOrigem = sc.nextLine();
                        doramaService.filtrarPais(paisOrigem);
                        break;
                    }
                    case 6: {
                        System.out.println("Digite a avaliação IMDB: ");
                        String avaliacaoIMDB = sc.nextLine();
                        doramaService.filtrarIMDB(avaliacaoIMDB);
                        break;
                    }
                    case 7: {
                        System.out.println("Digite o nome do ator: ");
                        String nomeAtor = sc.nextLine();
                        doramaService.filtrarAtores(nomeAtor);
                        break;
                    }
                    case 0:{
                        System.out.println("Voltando ao menu principal...");
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
