package GitHubProjects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HangmanGame {
    public static void main(String[] args) throws IOException {
        HangmanGameController hangmanGameController = new HangmanGameController();
        hangmanGameController.runHangmanGame();
    }

}

class HangmanGameController {
    private final HangmanGameView hangmanGameView;
    private final HangmanGameModel hangmanGameModel;

    public HangmanGameController() {
        hangmanGameModel = new HangmanGameModel();
        hangmanGameView = new HangmanGameView();
    }

    public void runHangmanGame() throws IOException {
        boolean gameRunning = true;
        int tries = 10;
        int options;


        hangmanGameView.announceMessage(HangmanGameView.WELCOME_MESSAGE);

        hangmanGameModel.RandomWordGenerator("C:\\Users\\anton\\Desktop\\GitHubProjects.HangmanGame.txt");
        String chosenWord = hangmanGameModel.getRandomWord();
        hangmanGameModel.initializeHashMapforWord(chosenWord);

        while (gameRunning) {
            hangmanGameView.announceMessage("tries left: " + tries);
            hangmanGameView.printHiddenWord(hangmanGameModel.getRightCharacters());
            hangmanGameView.announceMessage(HangmanGameView.OPTION_MESSAGE);
            options = hangmanGameView.getUserInputOption();

            if (tries == 0) {
                hangmanGameView.announceMessage(HangmanGameView.NO_TRIES_LEFT_MESSAGE);

            }

            switch (options) {
                case 1:
                    char inputCharacter = hangmanGameView.getUserInputCharacter();
                    if (!(hangmanGameModel.isRightCharacter(inputCharacter))) {
                        hangmanGameView.announceMessage(HangmanGameView.WRONG_CHAR_MESSAGE);
                        tries--;

                    } else {
                        hangmanGameView.rightLetter(inputCharacter);
                    }
                    break;
                case 2:
                    String word = hangmanGameView.getUserInputWord();
                    if (hangmanGameModel.finishWord(word, chosenWord)) {
                        hangmanGameView.announceMessage(HangmanGameView.WINNER_MESSAGE);
                        hangmanGameView.announceMessage(chosenWord);
                        gameRunning = false;
                    } else {
                        hangmanGameView.announceMessage(HangmanGameView.WRONG_WORD_MESSAGE);
                        tries -= 2;
                    }
                    break;
                case 3:
                    hangmanGameView.announceMessage(HangmanGameView.NO_TRIES_LEFT_MESSAGE);
                    gameRunning = false;
                    break;
            }


        }
        hangmanGameView.announceMessage(HangmanGameView.PLAY_AGAIN_MESSAGE);
        playAgain(hangmanGameView.wantToPlayAgain());


    }

    public void playAgain(boolean playAgain) throws IOException {

        if (playAgain) {
            runHangmanGame();
        }

    }

}

class HangmanGameView {

    public static final String WELCOME_MESSAGE = "Welcome to the GitHubProjects.HangmanGame.\nYou can start by guessing a letter of the word";
    public static final String WINNER_MESSAGE = "The word is correct. You won. Good Job";
    public static final String WRONG_WORD_MESSAGE = "The word is not correct, i will deduct two tries";
    public static final String OPTION_MESSAGE = "Your turn\n1.Guess character (Cost: 1 try)\n2.Guess word(Cost:2 tries)\n3.Give up";
    public static final String WRONG_CHAR_MESSAGE = "Wrong character, try again";
    public static final String NO_TRIES_LEFT_MESSAGE = "You gave up or have no more tries left.";
    public static final String PLAY_AGAIN_MESSAGE = "Do you want to play again?\n1:Yes\n2:NO";


    Scanner scanner = new Scanner(System.in);

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public String getUserInputWord() {
        return scanner.nextLine();
    }

    public int getUserInputOption() {
        return scanner.nextInt();
    }

    public char getUserInputCharacter() {
        System.out.println("Pick a character");
        return scanner.next().charAt(0);
    }

    public void rightLetter(char userInput) {
        System.out.println("The letter" + userInput + "was right");
    }

    public void printHiddenWord(HashMap<Character, Boolean> rightCharacters) {
        for (Map.Entry<Character, Boolean> entry : rightCharacters.entrySet()) {
            if (entry.getValue()) {
                System.out.print(entry.getKey());
            } else {
                System.out.print("_");
            }
        }
        System.out.println();

    }

    public boolean wantToPlayAgain() {
        return (1 == scanner.nextInt());
    }

}

class HangmanGameModel {
    private List<String> words;
    public HashMap<Character, Boolean> rightCharacters = new HashMap<>();

    public void initializeHashMapforWord(String chosenWord) {
        for (char c : chosenWord.toCharArray()) {
            rightCharacters.put(c, false); // Use word as key and length as value
        }
    }


    public void RandomWordGenerator(String filePath) throws IOException {
        words = Files.readAllLines(Paths.get(filePath));
    }

    public String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public boolean finishWord(String userInputWord, String chosenWord) {
        if (Objects.equals(chosenWord, userInputWord)) {
            System.out.println(HangmanGameView.WINNER_MESSAGE);
            return true;
        } else {
            System.out.println(HangmanGameView.WRONG_WORD_MESSAGE);
            return false;
        }
    }

    public boolean isRightCharacter(char inputCharacter) {
        boolean isRightCharacter = false;

        for (Map.Entry<Character, Boolean> characters : rightCharacters.entrySet())
            if (characters.getKey() == inputCharacter) {
                rightCharacters.put(characters.getKey(), true);
                isRightCharacter = true;
            }
        return isRightCharacter;
    }

    public HashMap<Character, Boolean> getRightCharacters() {
        return rightCharacters;
    }


}


