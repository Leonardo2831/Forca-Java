package game;

import Users.User;

import java.util.Scanner;

public class ForcaGame {
    Word word;
    User player;

    final byte maxErrors = 6;
    byte countError = 0;
    char[] revealedLetters;
    char[] usedLetters;
    String wordShow;

    public ForcaGame(Word word, User player) {
        this.word = word;
        this.player = player;

        StringBuilder concatRevealedLetters = new StringBuilder();
        for(int i = 0; i < this.word.secretWord.length(); i++) {
            if(this.word.secretWord.charAt(i) == ' ') {
                concatRevealedLetters.append(" ");
                continue;
            } else concatRevealedLetters.append("_");
        }

        this.wordShow = concatRevealedLetters.toString().toLowerCase();
    }

    boolean isOver() {
        return this.countError >= this.maxErrors || this.wordShow.equalsIgnoreCase(this.word.secretWord);
    }

    String messageGameFinal() {
        if(this.countError >= this.maxErrors) {
            return "Você perdeu! A palavra era: " + this.word.secretWord;
        } else {
            return "Parabéns " + this.player.getUsername() + ", você ganhou! Como descoberto, a palavra era: " + this.word.secretWord;
        }
    }

    boolean verifyLetter(char letter) {
        return this.word.secretWord.toLowerCase().indexOf(letter) >= 0;
    }

    public void initGame(){
        Scanner input = new Scanner(System.in);

        do{
            System.out.println("Dica: " + this.word.tip);
            System.out.println("Palavra: " + this.wordShow);
            System.out.println("Erros restantes: " + (this.maxErrors - this.countError));
            System.out.println("Letras usadas: " + String.valueOf(this.usedLetters));

            System.out.println("\n\nDigite uma letra: ");
            char guessedLetter = input.nextLine().toLowerCase().charAt(0);

            boolean isCorrect = verifyLetter(guessedLetter);
        } while(!isOver());
    }
}
