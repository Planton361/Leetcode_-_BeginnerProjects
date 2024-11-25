package GitHubProjects;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Scanner;

public class ChangeCalculator {
    public static void main(String[] args) {
        ChangeCalculatorController changeCalculatorController = new ChangeCalculatorController();
        changeCalculatorController.runChangeCalculator();
    }

}

class ChangeCalculatorController {

    private final ChangeCalculatorModel changeCalculatorModel;
    private final ChangeCalculatorView changeCalculatorView;
    private boolean continueLoop = true;

    public ChangeCalculatorController() {
        changeCalculatorModel = new ChangeCalculatorModel();
        changeCalculatorView = new ChangeCalculatorView();
    }

    public void runChangeCalculator() {
        changeCalculatorView.announceMessage(ChangeCalculatorView.WELCOME_MESSAGE);
        while (continueLoop) {
            changeCalculatorModel.setChange(changeCalculatorView.getUserInputOfChange());
            changeCalculatorModel.processAmountOfCoinsNeeded();
            changeCalculatorView.outputNeededCoins(changeCalculatorModel.amountOfCoinsNeeded);
            askToContinueProgram();
        }
        changeCalculatorView.announceMessage(ChangeCalculatorView.GOODBYE_MESSAGE);
    }

    public void askToContinueProgram() {
        changeCalculatorView.announceMessage(ChangeCalculatorView.CONTINUE_PROGRAM);
        continueLoop = (1 == changeCalculatorView.getUserInputContinueProgram());

    }

}

class ChangeCalculatorModel {
    private int change = 0;
    EnumMap<CoinType2, Integer> amountOfCoinsNeeded = new EnumMap<>(CoinType2.class);

    public void setChange(int change) {
        this.change = change;
    }

    public void processAmountOfCoinsNeeded() {
        amountOfCoinsNeeded.put(CoinType2.QUARTER, (int) (change / CoinType2.QUARTER.getCoinWorth()));
        change -= (int) (CoinType2.QUARTER.getCoinWorth() * amountOfCoinsNeeded.get(CoinType2.QUARTER));
        amountOfCoinsNeeded.put(CoinType2.DIME, (int) (change / CoinType2.DIME.getCoinWorth()));
        change -= (int) (CoinType2.DIME.getCoinWorth() * amountOfCoinsNeeded.get(CoinType2.DIME));
        amountOfCoinsNeeded.put(CoinType2.NICKEL, (int) (change / CoinType2.NICKEL.getCoinWorth()));
        change -= (int) (CoinType2.NICKEL.getCoinWorth() * amountOfCoinsNeeded.get((CoinType2.NICKEL)));
        amountOfCoinsNeeded.put(CoinType2.PENNY, (int) (change / CoinType2.PENNY.getCoinWorth()));

    }


}

class ChangeCalculatorView {
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    public static final String WELCOME_MESSAGE = "Welcome! Please put in your change.";
    public static final String CONTINUE_PROGRAM = "Do you want to continue?\nPress 1 for yes.\nPress 0 for no.\n";
    public static final String GOODBYE_MESSAGE = "Leaving the program, thanks for using.";

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public int getUserInputOfChange() {
        System.out.println("Please put in your change");
        double change = scanner.nextDouble();
        scanner.nextLine();
        return (int) (change * 100);
    }

    public void outputNeededCoins(EnumMap<CoinType2, Integer> amountOfCoinsNeeded) {
        for (CoinType2 coinType2 : amountOfCoinsNeeded.keySet()) {
            System.out.println("You need " + amountOfCoinsNeeded.get(coinType2) + " " + coinType2);
        }
        System.out.println();
    }


    public int getUserInputContinueProgram() {
        System.out.println("Please put in your decision");
        return scanner.nextInt();

    }
}

enum CoinType2 {
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);

    private final int coinWorth;

    CoinType2(int coinWorth) {
        this.coinWorth = coinWorth;
    }

    public double getCoinWorth() {
        return coinWorth;
    }
}