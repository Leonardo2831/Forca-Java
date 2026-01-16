import Users.DatabaseUser;
import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String haveUser = "Não";

        System.out.println("Bem-vindo ao nosso jogo da forca!");

        DatabaseUser users = new DatabaseUser("src/database/users.txt");
        ArrayList<User> usersList = users.getAllUsers();

        // verificação de usuário
        do{
            System.out.println("Você já possuí um usuário? (Sim/Não)");
            haveUser = input.nextLine();

            if(haveUser.equalsIgnoreCase("Sim")){
                System.out.println("Por favor, faça o login.");

                System.out.println("Digite seu nome de usuário: ");
                String username = input.nextLine();

                System.out.println("Digite sua Senha: ");
                String password = input.nextLine();

                boolean isUser = DatabaseUser.verifyData(usersList, username, password);

                if(!isUser){
                    System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                    haveUser = "Não";
                    continue;
                }

                System.out.println("Bem-vindo de volta!");
            } else if(haveUser.equalsIgnoreCase("Não")){
                System.out.println("Por favor, crie um usuário.");

                System.out.print("Digite seu nome de usuário: ");
                String username = input.nextLine();
                System.out.print("Digite uma senha para o cadastro: ");
                String password = input.nextLine();

                users.createUser(username, password);
                System.out.println("Usuário criado com sucesso! Agora você pode jogar.");

                haveUser = "Sim";
            } else {
                System.out.println("Resposta inválida. Por favor, responda com 'Sim' ou 'Não'.");
                haveUser = "Não";
            }

        } while(!haveUser.equalsIgnoreCase("Sim"));

        System.out.println("Obrigado por jogar!");
    }
}