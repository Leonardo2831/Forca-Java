import Users.DatabaseUser;
import Users.User;
import game.ForcaGame;
import game.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String haveUser = "Não";

        System.out.println("Bem-vindo ao nosso jogo da forca!");

        DatabaseUser users = new DatabaseUser("src/database/users.txt");
        ArrayList<User> usersList = users.getAllUsers();
        User yourUser = null;

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
                } else {
                    yourUser = DatabaseUser.getUser(usersList, username, password);
                }

                if(yourUser == null) {
                    System.err.println("Não foi possível carregar seu usuário, tente novamente.");
                    haveUser = "Não";
                    continue;
                }

                System.out.println("Bem-vindo de volta!");
            } else if(haveUser.equalsIgnoreCase("Não")){
                System.out.println("Por favor, crie um usuário.");

                System.out.println("Digite seu nome de usuário: ");
                String username = input.next();
                System.out.println("Digite uma senha para o cadastro: ");
                String password = input.next();

                yourUser = users.createUser(username, password);
                System.out.println("Usuário criado com sucesso! Agora você pode jogar.");

                haveUser = "Sim";
            } else {
                System.out.println("Resposta inválida. Por favor, responda com 'Sim' ou 'Não'.");
                haveUser = "Não";
            }

        } while(!haveUser.equalsIgnoreCase("Sim"));

        System.out.println(yourUser.getUsername() + ", deseja jogar uma partida? (Sim/Não)");
        String wantPlay = input.nextLine();

        do{
            System.out.println("Digite a palavra para ser adivinhada:");
            String secretWord = input.nextLine();
            System.out.println("Digite uma dica para a palavra:");
            String tip = input.nextLine();

            Word word = new Word(secretWord, tip);
            ForcaGame game = new ForcaGame(word, yourUser);

            System.out.println("Você deseja jogar novamente? (Sim/Não)");
            wantPlay = input.nextLine();
        } while(wantPlay.equalsIgnoreCase("Sim"));
    }
}