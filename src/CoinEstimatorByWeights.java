import java.util.HashMap;
import java.util.Scanner;

public class CoinEstimatorByWeights {
    public static void main(String[] args) {
        CoinEstimatorController coinEstimatorController = new CoinEstimatorController();
        coinEstimatorController.start();
    }
}

enum CoinType {
    PENNY(2.5),
    NICKEL(5),
    DIME(2.27),
    QUARTER(5.67);

    private final double weight;

    CoinType(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

class CoinEstimatorController {
    private Scanner scanner = new Scanner(System.in);
    private boolean continueLoop = true;
    private HashMap<CoinType, Double> weights = new HashMap<>();

    public void start() {
        try {
            while (continueLoop) {
                int totalWrappersNeeded = 0;

                for (CoinType coinType : CoinType.values()) {
                    System.out.printf("Wie viel wiegt dein(e) %s in Gramm insgesamt? ", coinType.name());
                    double weight = getUserInput();
                    weights.put(coinType, weight);
                    int wrappersNeeded = calculateWrappersNeeded(coinType, weight);
                    totalWrappersNeeded += wrappersNeeded;
                    System.out.printf("Du brauchst %d Wrapper für %s Münzen\n", wrappersNeeded, coinType.name());
                }

                System.out.printf("Insgesamt brauchst du %d Wrappers\n", totalWrappersNeeded);

                continueLoop = askForMoreCoins();
            }
        } finally {
            scanner.close();
        }
    }

    private double getUserInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Ungültige Eingabe, bitte eine Zahl eingeben.");
            scanner.next(); // Ignoriert ungültige Eingabe
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // Konsumiert die neue Zeile
        return input;
    }

    private int calculateWrappersNeeded(CoinType coinType, double weight) {
        return (int) (weight / coinType.getWeight() / 50);
    }

    private boolean askForMoreCoins() {
        System.out.println("Hast du mehr Münzen? \n 0 = nein \n 1 = ja");
        while (!scanner.hasNextInt()) {
            System.out.println("Ungültige Eingabe, bitte 0 oder 1 eingeben.");
            scanner.next(); // Ignoriert ungültige Eingabe
        }
        int response = scanner.nextInt();
        scanner.nextLine(); // Konsumiert die neue Zeile nach nextInt
        return response == 1;
    }
}