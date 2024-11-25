package GitHubProjects;

import java.util.Arrays;
import java.util.Scanner;

public class PythagoreanTriplesChecker {

    public static void main(String[] args) {
        new PythagoreanTriplesChecker().pythagoreanController();
    }

    /**
     * Kontrolliert den Ablauf der Anwendung.
     */
    public void pythagoreanController() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            try {
                int[] triangleSides = getInputFromUser(scanner);
                boolean isPythagorean = isPythagoreanTriangle(triangleSides);
                System.out.println(isPythagorean ? "Das Dreieck ist pythagoreisch." : "Das Dreieck ist nicht pythagoreisch.");
                continueLoop = askToContinue(scanner);
            } catch (Exception e) {
                System.out.println("Fehlerhafte Eingabe. Bitte versuchen Sie es erneut.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        scanner.close();
    }

    /**
     * Fragt den Benutzer nach den Seitenlängen des Dreiecks und gibt diese zurück.
     *
     * @param scanner der Scanner zum Lesen der Benutzereingabe
     * @return ein Array mit den Seitenlängen des Dreiecks
     */
    private int[] getInputFromUser(Scanner scanner) {
        System.out.println("Geben Sie die Seitenlängen des Dreiecks an (durch Komma oder Leerzeichen getrennt):");
        String input = scanner.nextLine().trim();
        String[] parts = input.split("[,\\s]+");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Exakte drei Werte sind erforderlich.");
        }

        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        return numbers;
    }

    /**
     * Überprüft, ob ein Dreieck mit den gegebenen Seitenlängen pythagoreisch ist.
     *
     * @param sides die Seitenlängen des Dreiecks
     * @return true, wenn das Dreieck pythagoreisch ist; false ansonsten
     */
    private boolean isPythagoreanTriangle(int[] sides) {
        Arrays.sort(sides); // Sortieren der Seiten
        int a = sides[0];
        int b = sides[1];
        int c = sides[2];
        return a * a + b * b == c * c; // Prüfen des Satzes von Pythagoras
    }

    /**
     * Fragt den Benutzer, ob er ein weiteres Dreieck überprüfen möchte.
     *
     * @param scanner der Scanner zum Lesen der Benutzereingabe
     * @return true, wenn der Benutzer weitermachen möchte; false, wenn er beenden möchte
     */
    private boolean askToContinue(Scanner scanner) {
        System.out.println("Möchten Sie ein weiteres Dreieck überprüfen? (0 für Ja, 1 für Nein)");
        int choice = scanner.nextInt();
        return choice == 0;
    }
}