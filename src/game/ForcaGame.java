package game;

import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ForcaGame {
    private Word word;
    private final User player;

    final byte maxErrors = 6;
    byte countError = 0;
    int countVictories = 0;
    ArrayList<Character> revealedLetters;
    ArrayList<Character> usedLetters;
    String wordShow;

    public ForcaGame(User player) {
        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();
        this.player = player;
    }

    public void setSecretWord(Word newSecretWord) {
        this.word = newSecretWord;
        this.wordShow = this.showLetters();
    }

    String messageGameFinal() {
        if(this.countError >= this.maxErrors) {
            return "Você perdeu! A palavra era: " + this.word.secretWord;
        } else {
            this.countVictories++;
            return "Parabéns " + this.player.getUsername() + ", você ganhou! Como descoberto, a palavra era: " + this.word.secretWord;
        }
    }

    private String showLetters(){
        StringBuilder concatRevealedLetters = new StringBuilder();
        for(int i = 0; i < this.word.secretWord.length(); i++) {
            boolean isRevealed = false;

            if(this.word.secretWord.charAt(i) == ' ') {
                concatRevealedLetters.append(' ');
                continue;
            }

            if(!this.revealedLetters.isEmpty()) {
                for(char revealedLetter : this.revealedLetters) {
                    String[] secretWordChars = this.word.secretWord.split("");
                    if(secretWordChars[i].equalsIgnoreCase(String.valueOf(revealedLetter))) {
                        concatRevealedLetters.append(this.word.secretWord.charAt(i));
                        isRevealed = true;
                        break;
                    }
                }
            }

            if(!isRevealed) {
                concatRevealedLetters.append('_');
            }
        }
        return concatRevealedLetters.toString().toLowerCase();
    }

    private boolean verifyLetter(char letter) {
        return this.word.secretWord.toLowerCase().indexOf(letter) >= 0;
    }

    private boolean isOver() {
        return this.countError >= this.maxErrors || this.wordShow.equalsIgnoreCase(this.word.secretWord);
    }

    public void initGame(){
        Scanner input = new Scanner(System.in);

        this.revealedLetters = new ArrayList<>();
        this.usedLetters = new ArrayList<>();

        do{
            System.out.println("Dica: " + this.word.tip);
            System.out.println("Palavra: " + this.wordShow);
            System.out.println("Erros Restantes: " + (this.maxErrors - this.countError));
            System.out.println("Letras Usadas: ");
            for(char letter : this.usedLetters){
                System.out.print(letter + " ");
            }

            System.out.println("\n\nDigite uma Letra: ");
            char choiceLetter = input.nextLine().toLowerCase().charAt(0);

            while(this.usedLetters.contains(choiceLetter)){
                System.out.println("Letra já utilizada, por favor escolha outra letra: ");
                choiceLetter = input.nextLine().toLowerCase().charAt(0);
            }

            boolean isCorrect = verifyLetter(choiceLetter);
            if(isCorrect) {
                this.revealedLetters.add(choiceLetter);
            } else {
                this.countError++;
            }
            this.usedLetters.add(choiceLetter);
            this.wordShow = this.showLetters();

        } while(!isOver());

        System.out.println(this.messageGameFinal());
        if(this.countVictories != 0) System.out.println("Total de Vitórias: " + this.countVictories);
    }
}
