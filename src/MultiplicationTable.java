import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        MultiplicationTableController multiplicationTableController = new MultiplicationTableController();
        multiplicationTableController.startMultiplicatiuonTable();
    }
}

class MultiplicationTableController {
    private MultiplicationTableModel multiplicationTableModel;
    private MultiplicationTableView multiplicationTableView;

    public MultiplicationTableController() {
        multiplicationTableModel = new MultiplicationTableModel();
        multiplicationTableView = new MultiplicationTableView();
    }

    public void startMultiplicatiuonTable() {
        multiplicationTableView.announceMessage(MultiplicationTableView.WELCOME_MESSAGE);

        int size =multiplicationTableView.getUserInput();
        int[][] multiplicationTable = multiplicationTableModel.initializeMultiplicationTable(size+1);
        multiplicationTableView.printOutMultiplicationTable(multiplicationTable);

        multiplicationTableView.announceMessage(MultiplicationTableView.GOODBYE_MESSAGE);


    }

}

class MultiplicationTableView {
    public static final String WELCOME_MESSAGE = "Welcome to the program.\nI will give you a multiplication table";
    public static final String GOODBYE_MESSAGE = "Closing Program";
    public static final String DECISION_MESSAGE = "Please put in your decision";

    Scanner scanner = new Scanner(System.in);

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public int getUserInput() {
        System.out.println("How large should be the multiplication table?");
        System.out.println(DECISION_MESSAGE);
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public void printOutMultiplicationTable(int[][] multiplicationTable) {


        for (int i = 1; i < multiplicationTable.length; i++) {
            for (int j = 1; j < multiplicationTable.length; j++) {
                if(multiplicationTable[j][i] < 10)
                    System.out.print("  ");
                else if(multiplicationTable[j][i]<100)
                    System.out.print(" ");
                System.out.print(" " + multiplicationTable[j][i] + " ");
                System.out.print("|");

            }
            System.out.println();
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        }
    }
}

class MultiplicationTableModel {

    public int[][] initializeMultiplicationTable(int size) {
        int[][] multiplicationTable = new int[size][size];
        for (int i = 1; i < multiplicationTable.length; i++) {
            for (int j = 1; j < multiplicationTable.length; j++) {
                multiplicationTable[i][j] = i * j;
            }
        }
        return multiplicationTable;

    }
}