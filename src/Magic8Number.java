/*Magic 8 Ball
Simulate a magic 8-ball.
Allow the user to enter their question.
Display an in progress message(i.e. "thinking").
Create 20 responses, and show a random response.
Allow the user to ask another question or quit.
        Bonus:
Add a gui.
It must have box for users to enter the question.
It must have at least 4 buttons:
ask
clear (the text box)
play again
quit (this must close the window)*/

import java.util.Random;
import java.util.Scanner;

public class Magic8Number {

    public static void main(String[] args) throws InterruptedException {
        controller();
    }

    public static void controller() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        boolean continueLoop = true;
        while (continueLoop) {
            enterQuestion(scanner);
            processQuestion();
            continueLoop = nextAction(scanner);
        }
        scanner.close();
    }


    public static void enterQuestion(Scanner scanner) throws InterruptedException {
        System.out.println("Ich bin ein magischer 8 Ball, was ist deine Frage?");
        String question = scanner.nextLine();
        System.out.println("Thinking...");
        Thread.sleep(3000);
    }

    public static void processQuestion() {
        Random random = new Random();
        int number = random.nextInt(10);

        switch (number) {
            case 0:
                System.out.println("Es ist sicher.");
                break;
            case 1:
                System.out.println("Frage später erneut.");
                break;
            case 2:
                System.out.println("Ohne Zweifel.");
                break;
            case 3:
                System.out.println("Darauf kannst du dich verlassen.");
                break;
            case 4:
                System.out.println("Meine Antwort ist nein.");
                break;
            case 5:
                System.out.println("Sehr wahrscheinlich.");
                break;
            case 6:
                System.out.println("Unklar, versuche es erneut.");
                break;
            case 7:
                System.out.println("Besser nicht jetzt.");
                break;
            case 8:
                System.out.println("Ziemlich unsicher.");
                break;
            case 9:
                System.out.println("Die Zeichen stehen auf Ja.");
                break;
        }


    }

    public static boolean nextAction(Scanner scanner) {
        System.out.println("Möchtest du weiter Fragen stellen?\n" +
                "Drücke 0 für noch eine Frage\n" +
                "Drücke 1 zum Verlassen");
        int exit = scanner.nextInt();
        while (exit != 0 && exit != 1) {
            System.out.println("Ungültige Eingabe");
            nextAction(scanner);
        }
        return exit == 0;
    }
}
