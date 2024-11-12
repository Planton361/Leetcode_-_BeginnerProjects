/*Mean, Median, and Mode
In a set of numbers, the mean is the average, the mode is the number that occurs the most, and if you rearrange all the numbers numerically, the median is the number in the middle.

Create three functions that allow the user to find the mean, median, and mode of a list of numbers. If you have access or know of functions that already complete these tasks, do not use them.
        Subgoals
In the mean function, give the user a way to select how many decimal places they want the answer to be rounded to.
If there is an even number of numbers in the list, return both numbers that could be considered the median.
If there are multiple modes, return all of them.*/


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MeanMedianMode {
    public static void main(String[] args) {
        MeanMedianModeController meanMedianModeController = new MeanMedianModeController();
        meanMedianModeController.runMeanMedianMode();
    }
}

class MeanMedianModeController {
    private final MeanMedianModeModel meanMedianModeModel;
    private final MeanMedianModeView meanMedianModeView;
    private static boolean continueLoop = true;

    public MeanMedianModeController() {
        meanMedianModeModel = new MeanMedianModeModel();
        meanMedianModeView = new MeanMedianModeView();

    }

    public void runMeanMedianMode() {
        meanMedianModeView.announceMessage(MeanMedianModeView.WELCOME_MESSAGE);
        while (continueLoop) {
            String userInput = meanMedianModeView.askUserForNumber();
            double[] numbers = meanMedianModeModel.createArrayOfUserInput(userInput);

            meanMedianModeView.announceMessage(MeanMedianModeView.OPTIONS_PROGRAM);
            int option = meanMedianModeView.getOptionInput();
            switch (option) {
                case 1:
                    double mean = meanMedianModeModel.meanOfList(numbers);
                    meanMedianModeView.announceMessage(String.valueOf(mean));
                    break;

                case 2:
                    double mode = meanMedianModeModel.modeOfList(numbers);
                    meanMedianModeView.announceMessage(String.valueOf(mode));
                    break;
                case 3:

                    break;
            }


            continueLoop = askUserToContinueProgram();
        }

        meanMedianModeView.announceMessage(MeanMedianModeView.GOODBYE_MESSAGE);
    }

    public boolean askUserToContinueProgram() {
        meanMedianModeView.announceMessage(MeanMedianModeView.CONTINUE_PROGRAM);
        int userInput = meanMedianModeView.askUserToContinue();
        return userInput == 1;
    }


}

class MeanMedianModeView {
    Scanner scanner = new Scanner(System.in);
    public static final String WELCOME_MESSAGE = "Welcome! In this program you can get the MeanMedianMode of your given numbers.";
    public static final String CONTINUE_PROGRAM = "Do you want to continue?\nPress 1 for yes.\nPress 0 for no.";
    public static final String OPTIONS_PROGRAM = "What do you want to do with the numbers?\n1 for average\n2 for mode\n3 for Median";
    public static final String GOODBYE_MESSAGE = "Leaving the program, thanks for using.";


    public void announceMessage(String message) {
        System.out.println(message);
    }

    public String askUserForNumber() {
        System.out.println("Please put in your numbers");
        return scanner.nextLine();
    }

    public int getOptionInput() {
        System.out.println("Please pick an option");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public int askUserToContinue() {
        System.out.println("Please give your decision");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;

    }
}

class MeanMedianModeModel {

    public double[] createArrayOfUserInput(String userInput) {
        String[] inputArray = userInput.split(",");
        double[] numbersDouble = new double[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numbersDouble[i] = Integer.parseInt(inputArray[i].trim()); // .trim() removes any extra spaces

        }
        return numbersDouble;
    }

    public double meanOfList(double[] arrayOfNumbers) {
        double sum = 0;
        for (double arrayOfNumber : arrayOfNumbers) {
            sum += arrayOfNumber;
        }
        return sum / arrayOfNumbers.length;
    }

    public double modeOfList(double[] arrayOfNumbers) {

        HashMap<Double, Integer> occurrenceOfNumbers = new HashMap<>();

        // Count occurrences of each number in the array
        for (double number : arrayOfNumbers) {
            occurrenceOfNumbers.put(number, occurrenceOfNumbers.getOrDefault(number, 0) + 1);
        }

        // Identify the mode by finding the key with the highest count
        double mode = arrayOfNumbers[0];
        int maxCount = 0;

        for (Map.Entry<Double, Integer> entry : occurrenceOfNumbers.entrySet()) {
            if (entry.getValue() > maxCount) {
                mode = entry.getKey();  // Set mode to the key (number)
                maxCount = entry.getValue();  // Update maxCount to the highest count found
            }
        }

        return mode;
    }

    public double medianOfList(double[] arrayOfNumbers) {
        double median = 0;

        return median;
    }


}
