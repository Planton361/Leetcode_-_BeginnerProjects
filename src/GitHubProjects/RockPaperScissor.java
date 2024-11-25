package GitHubProjects;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {
    public static void main(String[] args) throws InterruptedException {
        GameController gameController = new GameController();
    }
}

class Game {
    Player player1 = new Player(0, "GitHubProjects.Player 1", false);
    Player player2 = new Player(0, "GitHubProjects.Player 2", true);

    public Game(Scanner scanner) throws InterruptedException {
        boolean continueGame = true;
        while (continueGame) {
            System.out.println("Willkommen bei Schere, Stein, Papier");
            printScores();
            System.out.println("Wähle deine Optionen GitHubProjects.Player 1: Schere, Stein, Papier");
            player1.setPick(scanner);
            System.out.println("Wähle deine Optionen GitHubProjects.Player 2: Schere, Stein, Papier");
            player2.setPick(scanner);
            printPicks();
            Thread.sleep(3000);
            decideWinner(player1, player2);
            printScores();
            Thread.sleep(2000);
            continueGame = nextRound(scanner);
            Thread.sleep(2000);
        }
    }

    public void decideWinner(Player pick1, Player pick2) {
        if (Objects.equals(player1.getPick(), player2.getPick())) {
            System.out.println("Unentschieden");
        } else if (
                Objects.equals(player1.getPick(), "Stein") && Objects.equals(player2.getPick(), "Schere") ||
                        Objects.equals(player1.getPick(), "Schere") && Objects.equals(player2.getPick(), "Papier") ||
                        Objects.equals(player1.getPick(), "Papier") && Objects.equals(player2.getPick(), "Stein")
        ) {
            System.out.println("Spieler 1 hat gewonnen");
            player1.setScore(1);
        } else {
            System.out.println("Spieler 2 hat gewonnen");
            player2.setScore(1);
        }
    }

    public boolean nextRound(Scanner scanner) {
        System.out.println("Noch eine Runde?\n 0 für nein \n 1 für ja");
        while (!scanner.hasNextInt()) {
            scanner.next(); // ignoriert ungültige Eingaben
            System.out.println("Bitte geben Sie 0 oder 1 ein:");
        }
        int response = scanner.nextInt();
        scanner.nextLine(); // puffert die neue Zeile nach nextInt()
        return response == 1;
    }

    public void printScores() {
        System.out.println("Spielstand: Player1: " + player1.getScore() + " Player2 " + player2.getScore());
    }

    public void printPicks() {
        System.out.println("GitHubProjects.Player 1: " + player1.getPick());
        System.out.println("GitHubProjects.Player 2: " + player2.getPick());
    }
}

class Player {
    private int score;
    private String name;
    boolean isNPC;
    String pick;

    public Player(int score, String name, boolean isNPC) {
        this.score = 0;
        this.name = name;
        this.isNPC = isNPC;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public String getPick() {
        return pick;
    }

    public void setPick(Scanner scanner) {
        if (!isNPC) {
            pick = scanner.nextLine();
        } else {
            Random random = new Random();
            int randomInt = random.nextInt(3);
            switch (randomInt) {
                case 0:
                    pick = "Schere";
                    break;
                case 1:
                    pick = "Stein";
                    break;
                case 2:
                    pick = "Papier";
                    break;
            }
        }
    }
}

class GameController {
    Scanner scanner = new Scanner(System.in);
    Game game = new Game(scanner);

    GameController() throws InterruptedException {
    }
}