package game;

public class Word {
    String secretWord;
    String tip;

    public Word(String secretWord, String tip) {
        this.secretWord = secretWord.toLowerCase();
        this.tip = tip;
    }
}
