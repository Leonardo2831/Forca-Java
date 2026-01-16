import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String haveUser = "Não";

        System.out.println("Bem-vindo ao nosso jogo da forca!");

        do{
            System.out.println("Você já possuí um usuário? (Sim/Não)");
            haveUser = input.nextLine();

            if(haveUser.equalsIgnoreCase("Sim")){
                System.out.println("Bem-vindo de volta!");
            } else if(haveUser.equalsIgnoreCase("Não")){
                System.out.println("Por favor, crie um usuário.");
            } else {
                System.out.println("Resposta inválida. Por favor, responda com 'Sim' ou 'Não'.");
            }

        } while(haveUser.equalsIgnoreCase("Sim"));

        System.out.println("Obrigado por jogar!");
    }
}