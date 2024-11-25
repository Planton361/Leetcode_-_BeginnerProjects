package GitHubProjects;

import java.util.Scanner;

public class FibanocciSequence {

    public static void main(String[] args) {
        FibanocciSequenceController fibanocciSequenceController = new FibanocciSequenceController();
        fibanocciSequenceController.runFibanocciSequenceView();
    }
}

class FibanocciSequenceController {
    private final FibanocciSequenceModel fibanocciSequenceModel;
    private final FibanocciSequenceView fibanocciSequenceView;

    public FibanocciSequenceController() {
        fibanocciSequenceModel = new FibanocciSequenceModel();
        fibanocciSequenceView = new FibanocciSequenceView();
    }

    public void runFibanocciSequenceView() {
        fibanocciSequenceView.announceMessage(FibanocciSequenceView.WELCOME_MESSAGE);
        int userInput = fibanocciSequenceView.getUserInputInt();

        int fabinocciNumberForLoop = fibanocciSequenceModel.calculateFibanocciForLoop(userInput);
        int fabinocciNumberRecurison = fibanocciSequenceModel.calculateFibanocciRecursion(userInput-1);

        fibanocciSequenceView.announceMessage("The number is: " + fabinocciNumberForLoop);
        fibanocciSequenceView.announceMessage("The number is: " + fabinocciNumberRecurison);


    }

}

class FibanocciSequenceModel {
    public int calculateFibanocciForLoop(int userInput) {
        int[] fabinoccinumber = new int[userInput];
        fabinoccinumber[0] = 0;
        fabinoccinumber[1] = 1;
        for (int i = 2; i < userInput; i++) {
            fabinoccinumber[i] = fabinoccinumber[i - 2] + fabinoccinumber[i - 1];
        }
        return fabinoccinumber[userInput - 1];

    }

    public int calculateFibanocciRecursion(int userInput) {
        if (userInput <= 0) {
            return 0;
        } else if (userInput == 1) {
            return 1;
        } else {
            return calculateFibanocciRecursion(userInput - 1) + calculateFibanocciRecursion(userInput - 2);
        }
    }

}

class FibanocciSequenceView {
    public final static String WELCOME_MESSAGE = "Welcome to the program. I will calculate the value of the term";
    public final static String GOODBYE_MESSAGE = "Leaving the program, goodbye";
    public final static String ASKFORINPUT_MESSAGE = "Please write your desired value";

    Scanner scanner = new Scanner(System.in);

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public int getUserInputInt() {
        System.out.println(ASKFORINPUT_MESSAGE);
        return scanner.nextInt();
    }

}