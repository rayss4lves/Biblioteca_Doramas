package servicos;

import model.*;
import model.enuns.Generos;
import model.enuns.StatusDorama;
import model.enuns.TipoUsuario;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoramaService {
    private DiretorService diretorService;
    private AtorService atorService;

    private List<SerieDorama> seriesDoramas = new ArrayList<>();
    private List<FilmeDorama> filmeDoramas = new ArrayList<>();

    public DoramaService() {
        this.atorService = new AtorService();
        this.diretorService = new DiretorService();

    }

    Scanner sc = new Scanner(System.in);

    //SERIES

    public Dorama criarSerieDorama() {
        System.out.println("Insira o titulo da série dorama: ");
        String titulo = sc.nextLine();

        System.out.println("Insira a sinopse da série dorama: ");
        String sinopse = sc.nextLine();

        System.out.println("Insira o genero da série dorama: ");
        String genero = sc.nextLine();

        System.out.println("Escolha um gênero:");
        List<Generos> generos = new ArrayList<>();

        for (int i = 0; i < Generos.values().length; i++) {
            System.out.println(" - " + Generos.values()[i]);
        }
        String resposta;
        do {
            System.out.println("Selecione o número do gênero: ");
            int escolha = Integer.parseInt(sc.nextLine());
            generos.add(Generos.values()[escolha]);

            System.out.println("Deseja adicionar outro gênero? (s/n)");
            resposta = sc.nextLine().toLowerCase();

        }while(resposta.equals("s"));

        System.out.println("Insira a emissora da série dorama: ");
        String emissora = sc.nextLine();

        System.out.println("Insira o pais de origem da série dorama: ");
        String paisOrigem = sc.nextLine();

        System.out.println("Insira a data de publicação da série dorama (AAAA-MM-DD): ");
        String dataPublicacaoStr = sc.nextLine();

        System.out.println("Insira a avaliação IMDB da série dorama: ");
        String avaliacaoIMDB = sc.nextLine();

        System.out.println("Insira a quantidade de temporadas da série dorama: ");
        String qtdTemporadas = sc.nextLine();

        System.out.println("Insira a quantidade de episódios da série dorama: ");
        String qtdEpisodios = sc.nextLine();

        List<Ator> principaisAtores = new ArrayList<>();
        System.out.println("Deseja adicionar atores principais? (s/n)");
        String respostaAtores = sc.nextLine();

        while (respostaAtores.equals("s")) {
            System.out.println("Insira o nome do ator: ");
            String nomeAtor = sc.nextLine();
            Ator ator = atorService.buscarAtor(nomeAtor);
            if (ator == null)
                ator = atorService.criarAtor();
            principaisAtores.add(ator);

            System.out.println("Deseja adicionar outro ator principal? (s/n)");
            respostaAtores = sc.nextLine();
        }

        System.out.println("Insira o nome do diretor: ");
        String nomeDiretor = sc.nextLine();
        Diretor diretor = diretorService.buscarDiretor(nomeDiretor);
        if (diretor == null)
            diretor = diretorService.criarDiretor();

        StatusDorama statusDorama;
        for (int i = 0; i < StatusDorama.values().length; i++) {
            System.out.println(i + " - " + StatusDorama.values()[i]);
        }

        List<StatusDorama> status = new ArrayList<>();
        System.out.println("Deseja adicionar o status da série dorama? (s/n)");
        String respostaStatus = sc.nextLine().toLowerCase();
        while (respostaStatus.toLowerCase().equals("s")){
            System.out.println("Insira o status da série dorama: ");
            String statusStr = sc.nextLine().trim();

            try {
                statusDorama = StatusDorama.valueOf(statusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido.");
                return null;
            }
            status.add(statusDorama);

            System.out.println("Deseja adicionar outro status? (s/n)");
            respostaStatus = sc.nextLine().toLowerCase();
        }


        SerieDorama serieDorama = new SerieDorama(titulo, sinopse, generos, emissora, paisOrigem,
                LocalDate.parse(dataPublicacaoStr), avaliacaoIMDB, principaisAtores, diretor, status,
                qtdTemporadas, qtdEpisodios);
        seriesDoramas.add(serieDorama);
        return serieDorama;
    }

    public void editarSeries(){
        System.out.println("Insira o titulo da série dorama que deseja editar: ");
        String titulo = sc.nextLine();

        SerieDorama serieEncontrada = buscarSerie(titulo);
        if (serieEncontrada != null) {
            menuEdicaoSerie(serieEncontrada, sc);
        } else {
            System.out.println("Série dorama não encontrada.");
        }
    }

    public void menuEdicaoSerie(SerieDorama serieDorama, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nO que deseja editar?");
            System.out.println("1 - Título");
            System.out.println("2 - Sinopse");
            System.out.println("3 - Gênero");
            System.out.println("4 - Emissora");
            System.out.println("5 - País de Origem");
            System.out.println("6 - Data de Publicação");
            System.out.println("7 - Avaliação IMDB");
            System.out.println("8 - Quantidade de Temporadas");
            System.out.println("9 - Quantidade de Episódios");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Insira o novo título: ");
                    serieDorama.setTitulo(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Insira a nova sinopse: ");
                    serieDorama.setSinopse(sc.nextLine());
                    break;
                case 3:
                    for (int i = 0; i < StatusDorama.values().length; i++) {
                        System.out.println(i + " - " + StatusDorama.values()[i]);
                    }

                    List<StatusDorama> status = new ArrayList<>();
                    System.out.println("Deseja adicionar o status da série dorama? (s/n)");
                    String respostaStatus = sc.nextLine().toLowerCase();

                    while (respostaStatus.equals("s")) {
                        System.out.println("Insira o status da série dorama:");
                        String statusStr = sc.nextLine().trim();

                        try {
                            StatusDorama statusDorama = StatusDorama.valueOf(statusStr.toUpperCase());
                            status.add(statusDorama);
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠️ Status inválido.");
                        }

                        System.out.println("Deseja adicionar outro status? (s/n)");
                        respostaStatus = sc.nextLine().toLowerCase();
                    }

                    serieDorama.setStatusDorama(status); // ou outro método apropriado
                    break;

                case 4:
                    System.out.println("Insira a nova emissora: ");
                    serieDorama.setEmissora(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Insira o novo país de origem: ");
                    serieDorama.setPaisOrigem(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Insira a nova data de publicação (AAAA-MM-DD): ");
                    serieDorama.setDataPublicacao(LocalDate.parse(sc.nextLine()));
                    break;
                case 7:
                    System.out.println("Insira a nova avaliação IMDB: ");
                    serieDorama.setAvaliacaoIMDB(sc.nextLine());
                    break;
                case 8:
                    System.out.println("Insira a nova quantidade de temporadas: ");
                    serieDorama.setQtdTemporadas(sc.nextLine());
                    break;
                case 9:
                    System.out.println("Insira a nova quantidade de episódios: ");
                    serieDorama.setQtdEpisodios(sc.nextLine());
                    break;
            }
        }
    }

    public SerieDorama excluirSerie() {
        System.out.println("Insira o titulo da série dorama que deseja excluir: ");
        String titulo = sc.nextLine();

        SerieDorama serieEncontrada = buscarSerie(titulo);
        if (serieEncontrada != null) {
            seriesDoramas.remove(serieEncontrada);
            System.out.println("Série dorama excluída com sucesso.");
        } else {
            System.out.println("Série dorama não encontrada.");
        }
        return serieEncontrada;
    }

    public void listarSeries() {
        if (seriesDoramas.isEmpty()) {
            System.out.println("Nenhuma série dorama cadastrada.");
        } else {
            for (SerieDorama serie : seriesDoramas) {
                serie.listaDoramasFormatado();
            }
        }
    }

    public SerieDorama buscarSerie(String titulo) {
        for (SerieDorama serie : seriesDoramas) {
            if (serie.getTitulo().equalsIgnoreCase(titulo)) {
                return serie;
            }
        }
        System.out.println("Série dorama não encontrada.");
        return null;
    }

    //FILMES

    public Dorama criarFilmeDorama() {
        System.out.println("Insira o titulo do filme dorama: ");
        String titulo = sc.nextLine();

        System.out.println("Insira a sinopse do filme dorama: ");
        String sinopse = sc.nextLine();

        System.out.println("Insira o genero do filme dorama: ");
        String genero = sc.nextLine();

        System.out.println("Escolha um gênero:");
        List<Generos> generos = new ArrayList<>();

        for (int i = 0; i < Generos.values().length; i++) {
            System.out.println(i + " - " + Generos.values()[i]);
        }
        String resposta;
        do {
            System.out.println("Selecione o número do gênero: ");
            int escolha = Integer.parseInt(sc.nextLine());
            generos.add(Generos.values()[escolha]);

            System.out.println("Deseja adicionar outro gênero? (s/n)");
            resposta = sc.nextLine().toLowerCase();

        }while(resposta.equals("s"));

        System.out.println("Insira a emissora do filme dorama: ");
        String emissora = sc.nextLine();

        System.out.println("Insira o pais de origem do filme dorama: ");
        String paisOrigem = sc.nextLine();

        System.out.println("Insira a data de publicação do filme dorama (AAAA-MM-DD): ");
        String dataPublicacaoStr = sc.nextLine();

        System.out.println("Insira a avaliação IMDB do filme dorama: ");
        String avaliacaoIMDB = sc.nextLine();

        System.out.println("Insira a duracao do filme dorama (em segundos): ");
        int duracao = sc.nextInt();
        sc.nextLine();

        List<Ator> principaisAtores = new ArrayList<>();
        System.out.println("Deseja adicionar atores principais? (s/n)");
        String respostaAtores = sc.nextLine();

        while (respostaAtores.equals("s")) {
            System.out.println("Insira o nome do ator: ");
            String nomeAtor = sc.nextLine();
            Ator ator = atorService.buscarAtor(nomeAtor);
            if (ator == null)
                ator = atorService.criarAtor();
            principaisAtores.add(ator);

            System.out.println("Deseja adicionar outro ator principal? (s/n)");
            respostaAtores = sc.nextLine();
        }


        System.out.println("Insira o nome do diretor: ");
        String nomeDiretor = sc.nextLine();
        Diretor diretor = diretorService.buscarDiretor(nomeDiretor);
        if (diretor == null)
            diretor = diretorService.criarDiretor();

        StatusDorama statusDorama;
        for (int i = 0; i < StatusDorama.values().length; i++) {
            System.out.println(" - " + StatusDorama.values()[i]);
        }

        List<StatusDorama> status = new ArrayList<>();
        System.out.println("Deseja adicionar o status do filme dorama? (s/n)");
        String respostaStatus = sc.nextLine().toLowerCase();
        while (respostaStatus.toLowerCase().equals("s")){
            System.out.println("Insira o status do filme dorama: ");
            String statusStr = sc.nextLine().trim();

            try {
                statusDorama = StatusDorama.valueOf(statusStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido.");
                return null;
            }
            status.add(statusDorama);

            System.out.println("Deseja adicionar outro status? (s/n)");
            respostaStatus = sc.nextLine().toLowerCase();
        }


        FilmeDorama filmeDorama = new FilmeDorama(titulo, sinopse, generos, emissora, paisOrigem,
                LocalDate.parse(dataPublicacaoStr), avaliacaoIMDB, principaisAtores, diretor, status, duracao);
        filmeDoramas.add(filmeDorama);
        return filmeDorama;
    }

    public void editarFilmes(){
        System.out.println("Insira o titulo da série dorama que deseja editar: ");
        String titulo = sc.nextLine();

        FilmeDorama filmeEncontrado = buscarFilme(titulo);
        if (filmeEncontrado != null) {
            menuEdicaoFilme(filmeEncontrado, sc);
        } else {
            System.out.println("Série dorama não encontrada.");
        }
    }

    public void menuEdicaoFilme(FilmeDorama filmeDorama, Scanner sc) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nO que deseja editar?");
            System.out.println("1 - Título");
            System.out.println("2 - Sinopse");
            System.out.println("3 - Gênero");
            System.out.println("4 - Emissora");
            System.out.println("5 - País de Origem");
            System.out.println("6 - Data de Publicação");
            System.out.println("7 - Avaliação IMDB");
            System.out.println("8 - Duracao");
            System.out.println("0 - Sair");

            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Insira o novo título: ");
                    filmeDorama.setTitulo(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Insira a nova sinopse: ");
                    filmeDorama.setSinopse(sc.nextLine());
                    break;
                case 3:
                    for (int i = 0; i < StatusDorama.values().length; i++) {
                        System.out.println(i + " - " + StatusDorama.values()[i]);
                    }

                    List<StatusDorama> status = new ArrayList<>();
                    System.out.println("Deseja adicionar o status da série dorama? (s/n)");
                    String respostaStatus = sc.nextLine().toLowerCase();

                    while (respostaStatus.equals("s")) {
                        System.out.println("Insira o status da série dorama:");
                        String statusStr = sc.nextLine().trim();

                        try {
                            StatusDorama statusDorama = StatusDorama.valueOf(statusStr.toUpperCase());
                            status.add(statusDorama);
                        } catch (IllegalArgumentException e) {
                            System.out.println("⚠️ Status inválido.");
                        }

                        System.out.println("Deseja adicionar outro status? (s/n)");
                        respostaStatus = sc.nextLine().toLowerCase();
                    }

                    filmeDorama.setStatusDorama(status); // ou outro método apropriado
                    break;

                case 4:
                    System.out.println("Insira a nova emissora: ");
                    filmeDorama.setEmissora(sc.nextLine());
                    break;
                case 5:
                    System.out.println("Insira o novo país de origem: ");
                    filmeDorama.setPaisOrigem(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Insira a nova data de publicação (AAAA-MM-DD): ");
                    filmeDorama.setDataPublicacao(LocalDate.parse(sc.nextLine()));
                    break;
                case 7:
                    System.out.println("Insira a nova avaliação IMDB: ");
                    filmeDorama.setAvaliacaoIMDB(sc.nextLine());
                    break;
                case 8:
                    System.out.println("Insira a nova duracao: ");
                    filmeDorama.setDuracao(sc.nextInt());
                    break;
            }
        }
    }

    public FilmeDorama excluirFilme() {
        System.out.println("Insira o titulo da série dorama que deseja excluir: ");
        String titulo = sc.nextLine();

        FilmeDorama filmeEncontrado = buscarFilme(titulo);
        if (filmeEncontrado != null) {
            filmeDoramas.remove(filmeEncontrado);
            System.out.println("Série dorama excluída com sucesso.");
        } else {
            System.out.println("Série dorama não encontrada.");
        }
        return filmeEncontrado;
    }

    public void listarFilmes() {
        if (filmeDoramas.isEmpty()) {
            System.out.println("Nenhum filme dorama cadastrado.");
        } else {
            for (FilmeDorama filme : filmeDoramas) {
                filme.listaDoramasFormatado();
            }
        }
    }

    public FilmeDorama buscarFilme(String titulo) {
        for (FilmeDorama filme : filmeDoramas) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                return filme;
            }
        }
        System.out.println("Série dorama não encontrada.");
        return null;
    }


    //FILTROS

    public List<Dorama> filtrarGenero(String genero){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            for (Generos gen : dorama.getGenero()) {
                if (gen.name().equalsIgnoreCase(genero)) {
                    doramasFiltrados.add(dorama);
                }
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarStatus(String status){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            for (StatusDorama sts : dorama.getStatusDorama()) {
                if (sts.name().equalsIgnoreCase(status)) {
                    doramasFiltrados.add(dorama);
                }
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarEmissora(String emissora){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            if (emissora.equalsIgnoreCase(dorama.getEmissora())) {
                doramasFiltrados.add(dorama);
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarPorIntervaloDeData(LocalDate inicio, LocalDate fim) {
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);

        List<Dorama> doramasFiltrados = new ArrayList<>();

        for (Dorama d : todosDoramas) {
            LocalDate data = d.getDataPublicacao();
            if ((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))) {
                doramasFiltrados.add(d);
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarAtores(String nomeAtor){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            for (Ator ator: dorama.getPrincipaisAtores())
            if (nomeAtor.equalsIgnoreCase(ator.getNome())) {
                doramasFiltrados.add(dorama);
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarPais(String paisOrigem){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            if (paisOrigem.equalsIgnoreCase(dorama.getPaisOrigem())) {
                doramasFiltrados.add(dorama);
            }
        }

        return doramasFiltrados;
    }

    public List<Dorama> filtrarIMDB(String nota){
        List<Dorama> doramasFiltrados = new ArrayList<>();
        List<Dorama> todosDoramas = new ArrayList<>();
        todosDoramas.addAll(filmeDoramas);
        todosDoramas.addAll(seriesDoramas);
        for (Dorama dorama : todosDoramas) {
            if (nota.equalsIgnoreCase(dorama.getAvaliacaoIMDB())) {
                doramasFiltrados.add(dorama);
            }
        }

        return doramasFiltrados;
    }

}
