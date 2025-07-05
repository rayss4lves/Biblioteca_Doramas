package servicos;

import model.Ator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtorService {
    List<Ator> actors = new ArrayList<>();
    List<String> premios = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Ator criarAtor() {
        System.out.println("Informe o nome do ator: ");
        String nome = sc.nextLine();

        System.out.println("Insira a nacionalidade: ");
        String nacionalidade = sc.nextLine();

        System.out.println("Insira a data de nascimento (AAAA-MM-DD): ");
        String dataNascStr = sc.nextLine();
        LocalDate dataNasc = LocalDate.parse(dataNascStr);

        System.out.println("Informe a quantidade de prêmios: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        List<String> premios = new ArrayList<>();
        for (int i = 1; i <= qtd; i++) {
            System.out.println("Informe o nome do prêmio #" + i + ": ");
            String premio = sc.nextLine().trim();
            premios.add(premio);
        }

        Ator ator = new Ator(nome, nacionalidade, dataNasc, premios);
        this.actors.add(ator);
        return ator;
    }


    public int excluirAtor(){
        int excluiu = 0;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();
        Ator ator = buscarAtor(nome);
        if (ator != null){
            this.actors.remove(ator);
            excluiu = 1;
        }

        return excluiu;
    }

    public int editarAtor(){
        int editou = 0;
        System.out.println("Informe o seu nome: ");
        String nome= sc.nextLine();
        Ator ator = buscarAtor(nome);
        if (ator != null) {
            menuEdicao(ator);
            editou = 1;
        }
        return editou;
    }

    public void menuEdicao(Ator ator){
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nO que deseja editar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Nacionalidade");
            System.out.println("3 - Data de nascimento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        ator.setNome(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nova nacionalidade: ");
                        ator.setNacionalidade(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                        try {
                            LocalDate novaData = LocalDate.parse(sc.nextLine());
                            ator.setDataNascimento(novaData);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido!");
                        }
                    }
                    case 0 -> System.out.println("Edição finalizada!");
                    default -> System.out.println("Opção inválida.");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida. Digite um número.");
            }}
    }

    public void listarActors(){
        for (Ator act:this.actors){
            act.listarActor();
        }
    }

    public Ator buscarAtor(String nome){
        Ator atorEncontrado = null;
        for (Ator us:this.actors){
            if (nome.equals(us.getNome())){
                atorEncontrado = us;
            }
        }
        return atorEncontrado;
    }

}
