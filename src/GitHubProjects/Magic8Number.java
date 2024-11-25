package GitHubProjects;/*Magic 8 Ball
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
        magic8NumberController();

    }

    public static void magic8NumberController() throws InterruptedException {
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
        System.out.print("Thinking");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        Thread.sleep(1000);
        System.out.println();
    }

    public static void processQuestion() {
        Random random = new Random();
        int number = random.nextInt(10);
        String[] answers = new String[]{
                "Es ist sicher.",
                "Frage später erneut.",
                "Ohne Zweifel.",
                "Darauf kannst du dich verlassen.",
                "Meine Antwort ist nein.",
                "Sehr wahrscheinlich.",
                "Unklar, versuche es erneut.",
                "Besser nicht jetzt.",
                "Ziemlich unsicher.",
                "Die Zeichen stehen auf Ja."
        };
        System.out.println(answers[number] +"\n");
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
