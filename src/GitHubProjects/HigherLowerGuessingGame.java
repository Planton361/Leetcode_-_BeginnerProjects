package GitHubProjects;

import java.util.Random;
import java.util.Scanner;

public class HigherLowerGuessingGame {
    public static void main(String[] args) {
        HigherLowerGuessingGameController higherLowerGuessingGameController = new HigherLowerGuessingGameController();
        higherLowerGuessingGameController.runHigherLowerGuessingGame();
    }
}

class HigherLowerGuessingGameController {
    private final HigherLowerGuessingGameView higherLowerGuessingGameView;
    private final HigherLowerGuessingGameModel higherLowerGuessingGameModel;

    HigherLowerGuessingGameController() {
        this.higherLowerGuessingGameView = new HigherLowerGuessingGameView();
        this.higherLowerGuessingGameModel = new HigherLowerGuessingGameModel();
    }

    public void runHigherLowerGuessingGame() {
        higherLowerGuessingGameView.announceMessage(HigherLowerGuessingGameView.WELCOME_MESSAGE);
        boolean continueLoop = true;

        while (continueLoop) {
            boolean wrongNumber = true;
            int randomNumber = higherLowerGuessingGameModel.generateRandomNumber();
            while (wrongNumber) {
                int userInput = higherLowerGuessingGameView.getUserInputNumber();
                wrongNumber = higherLowerGuessingGameModel.isItTheRightNumber(userInput, randomNumber);
            }


            higherLowerGuessingGameView.announceMessage(HigherLowerGuessingGameView.CONTINUE_PROGRAM);
            continueLoop = higherLowerGuessingGameView.askToContinue();
        }
    }
}

class HigherLowerGuessingGameView {
    Scanner scanner = new Scanner(System.in);

    public static final String WELCOME_MESSAGE = "Welcome to the HigherLowerGame.\nPlease guess a number";
    public static final String CONTINUE_PROGRAM = "Do you want to continue?\nPress 1 for yes.\nPress 0 for no.";

    public int getUserInputNumber() {
        System.out.println("Please input a number");
        int userInputNumber = scanner.nextInt();
        scanner.nextLine();
        return userInputNumber;
    }

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public boolean askToContinue() {
        System.out.println("Please enter your option.");
        boolean askContinue = scanner.nextInt() == 1;
        scanner.nextLine();
        return askContinue;
    }
}

class HigherLowerGuessingGameModel {

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public boolean isItTheRightNumber(int userInput, int randomNumber) {
        if (userInput == randomNumber) {
            System.out.println("You got the right number");
            return false;
        } else if (userInput < randomNumber) {
            System.out.println("Number is higher");
            return true;
        } else {
            System.out.println("Number is lower");
            return true;
        }

    }


}
