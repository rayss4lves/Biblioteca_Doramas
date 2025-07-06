package servicos;

import model.Diretor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiretorService {

    List<Diretor> diretores = new ArrayList<>();
    List<String> premios = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public Diretor criarDiretor(){
        System.out.println("Informe o nome do diretor: ");
        String nome= sc.nextLine();

        System.out.println("Insira a sua nascionalidade: ");
        String nascionalidade = sc.nextLine();

        System.out.println("Insira a data do seu nascimento: ");
        String dataNascStr = sc.nextLine();
        LocalDate dataNasc = LocalDate.parse(dataNascStr);

        int i = 1;
        System.out.println("Informe a quantidade de premios: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        while(i <= qtd) {
            System.out.println("Informe o nome do premio: ");
            String premio = sc.nextLine().trim();
            premios.add(premio);
            i++;
        }

        Diretor diretor = new Diretor(nome, nascionalidade, dataNasc, premios);

        if (diretor!=null) {
            System.out.println("Diretor criado com sucesso!");
        } else {
            System.out.println("Erro ao criar o diretor.");
        }
        this.diretores.add(diretor);
        return diretor;
    }

    public int excluirDiretor(){
        int excluiu = 0;
        System.out.println("Informe o nome do diretor: ");
        String nome= sc.nextLine();
        Diretor diretor = buscarDiretor(nome);
        if (diretor != null){
            this.diretores.remove(diretor);
            excluiu = 1;
        }

        return excluiu;
    }

    public int editarDiretor(){
        int editou = 0;
        System.out.println("Informe o nome do diretor: ");
        String nome= sc.nextLine();
        Diretor diretor = buscarDiretor(nome);
        if (diretor != null) {
            menuEdicao(diretor);
            editou = 1;
        }
        return editou;
    }

    public void menuEdicao(Diretor diretor){
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
                        diretor.setNome(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nova nacionalidade: ");
                        diretor.setNacionalidade(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Nova data de nascimento (AAAA-MM-DD): ");
                        try {
                            LocalDate novaData = LocalDate.parse(sc.nextLine());
                            diretor.setDataNascimento(novaData);
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

    public void listarDiretores(){
        for (Diretor act:this.diretores){
            act.listarDiretor();
        }
    }

    public Diretor buscarDiretor(String nome){
        Diretor diretorExcluir = null;
        for (Diretor us:this.diretores){
            if (nome.equals(us.getNome())){
                diretorExcluir = us;
            }
        }
        return diretorExcluir;
    }


}
