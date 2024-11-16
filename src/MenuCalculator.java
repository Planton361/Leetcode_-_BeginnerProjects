import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuCalculator {
    public static void main(String[] args) {
        MenuCalculatorController menuCalculatorController = new MenuCalculatorController();
        try {
            menuCalculatorController.runMenuCalculator();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class MenuCalculatorController {

    private final MenuCalculatorModel menuCalculatorModel;
    private final MenuCalculatorView menuCalculatorView;
    private final MenuRepository menuRepository;

    public MenuCalculatorController() {
        menuCalculatorView = new MenuCalculatorView();
        menuCalculatorModel = new MenuCalculatorModel();
        menuRepository = new MenuRepository();
    }

    public void runMenuCalculator() throws IOException {
        menuCalculatorView.announceMessage(MenuCalculatorView.WELCOME_MESSAGE);
        menuRepository.loadMenu("C:\\Users\\anton\\Desktop\\Menu.txt"); // Menu file can be passed dynamically
        menuCalculatorView.printMenu(menuRepository.getMenuCard());

        boolean continueOrdering = true;
        while (continueOrdering) {
            String userInput = menuCalculatorView.handleUserInput();
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }
            int[] processedInput = menuCalculatorModel.processInput(userInput);
            double totalPrice = menuCalculatorModel.calculatePrice(processedInput, menuRepository.getMenuCard());
            menuCalculatorView.announceMessage("Your total price is: $" + totalPrice);
            continueOrdering = menuCalculatorView.askForAnotherOrder();
        }

        menuCalculatorView.announceMessage(MenuCalculatorView.GOODBYE_MESSAGE);
    }
}

class MenuCalculatorModel {

    public int[] processInput(String userInput) {
        int[] userInputArray = new int[userInput.length()];
        for (int i = 0; i < userInput.length(); i++) {
            char digitChar = userInput.charAt(i); // Get each character
            if (Character.isDigit(digitChar)) {
                userInputArray[i] = Character.getNumericValue(digitChar); // Convert char to int
            } else {
                throw new IllegalArgumentException("Invalid input. Please enter numbers only.");
            }
        }
        return userInputArray;
    }

    public double calculatePrice(int[] processedInput, HashMap<Integer, MenuItems> menuCard) {
        double totalPrice = 0.0;

        for (int id : processedInput) {
            // Get the MenuItem object corresponding to the current ID from the menuCard
            MenuItems item = menuCard.get(id);

            // If the item exists (in case of invalid ID, this handles the case where null is returned)
            if (item != null) {
                totalPrice += item.getMenuPrice();
            } else {
                System.out.println("Invalid item ID: " + id);
            }
        }

        return totalPrice;
    }
}

class MenuCalculatorView {
    public final static String WELCOME_MESSAGE = "Welcome to our restaurant. Look at our menu and make an order";
    public final static String GOODBYE_MESSAGE = "Thanks for ordering. Bye.";
    public final static String MORE_ORDERS_MESSAGE = "Do you want to order more?";
    public final static String CALCULATING_MESSAGE = "Calculating your order...";

    private final Scanner scanner = new Scanner(System.in);

    public void announceMessage(String message) {
        System.out.println(message);
    }

    public void printMenu(HashMap<Integer, MenuItems> menuCard) {
        System.out.println("Menu Items:");
        for (Map.Entry<Integer, MenuItems> entry : menuCard.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public String handleUserInput() {
        System.out.println("Please enter your order.");
        return scanner.nextLine();
    }

    public boolean askForAnotherOrder() {
        System.out.println("Do you want to order more? (yes/no)");
        String response = scanner.nextLine().toLowerCase();
        return response.equals("yes");
    }
}

class MenuRepository {
    private final HashMap<Integer, MenuItems> menuCard;

    public MenuRepository() {
        this.menuCard = new HashMap<>();
    }

    public void loadMenu(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    menuCard.put(id, new MenuItems(id, name, price));
                } else {
                    System.out.println("Error: Invalid line in menu file: " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading the menu file: " + e.getMessage());
        }
    }

    public HashMap<Integer, MenuItems> getMenuCard() {
        return menuCard;
    }
}

class MenuItems {
    private final int menuID;
    private final String menuName;
    private final double menuPrice;

    public MenuItems(int menuID, String menuName, double menuPrice) {
        this.menuID = menuID;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public int getMenuID() {
        return menuID;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    @Override
    public String toString() {
        return menuName + " ($" + menuPrice + ")";
    }
}
