//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Dorama meuDorama = new Dorama("Extraordinary You");
        System.out.println("Nome: " + meuDorama.getNome());

        meuDorama.setNome("Business Proposal");
        System.out.println("Novo nome: " + meuDorama.getNome());
    }
}