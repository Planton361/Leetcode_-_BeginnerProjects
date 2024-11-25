package GitHubProjects;

import java.util.*;

class MadLibsStoryMaker {
    public static void main(String[] args) {
        new StoryMakerController().startGame();
    }
}

class StoryMakerController {
    private final StoryManager storyManager = new StoryManager();
    private final UserInputManager userInputManager = new UserInputManager();
    private final StoryDisplay storyDisplay = new StoryDisplay();

    public void startGame() {
        storyDisplay.showIntroduction();

        while (true) {
            int option = storyDisplay.getMenuOption();
            switch (option) {
                case 1: // Play the game
                    playGame();
                    break;
                case 2: // Create a custom story
                    createCustomStory();
                    break;
                case 3: // Remove a story
                    removeStory();
                    break;
                default:
                    storyDisplay.showError("Invalid option");
            }

            if (!storyDisplay.askPlayAgain()) {
                storyDisplay.sayGoodbye();
                break;
            }
        }
    }

    private void playGame() {
        int selectedStory = storyManager.selectStory();
        storyDisplay.showStoryOptions(storyManager.getStoryPlaceholders(selectedStory));
        Map<PlaceHolderType, String> userInputs = userInputManager.collectUserInputs();
        String completedStory = storyManager.fillInTheBlanks(userInputs, selectedStory);
        storyDisplay.showCompletedStory(completedStory);
    }

    private void createCustomStory() {
        String customStory = storyDisplay.createCustomStory();
        storyManager.addStory(customStory);
    }

    private void removeStory() {
        int storyToDelete = storyDisplay.chooseStoryToDelete();
        storyManager.removeStory(storyToDelete);
    }
}

class StoryManager {
    private final Map<Integer, String> stories = new HashMap<>();
    private final Map<Integer, EnumMap<PlaceHolderType, Integer>> storyWordCounts = new HashMap<>();

    public StoryManager() {
        stories.put(1, "Once upon a time in the land of [NOUN], there was a [ADJECTIVE] [NOUN].");
        stories.put(2, "A [NOUN] walked into a bar and [VERB].");
    }

    public int selectStory() {
        Random random = new Random();
        return random.nextInt(stories.size()) + 1;
    }

    public EnumMap<PlaceHolderType, Integer> getStoryPlaceholders(int storyId) {
        return storyWordCounts.computeIfAbsent(storyId, id -> countPlaceholders(stories.get(id)));
    }

    private EnumMap<PlaceHolderType, Integer> countPlaceholders(String story) {
        EnumMap<PlaceHolderType, Integer> wordCounts = new EnumMap<>(PlaceHolderType.class);
        for (PlaceHolderType type : PlaceHolderType.values()) {
            wordCounts.put(type, (int) Arrays.stream(story.split("\\W+"))
                    .filter(word -> word.equalsIgnoreCase(type.name()))
                    .count());
        }
        return wordCounts;
    }

    public String fillInTheBlanks(Map<PlaceHolderType, String> userInputs, int storyId) {
        String story = stories.get(storyId);
        for (Map.Entry<PlaceHolderType, String> entry : userInputs.entrySet()) {
            story = story.replace("[" + entry.getKey() + "]", entry.getValue());
        }
        return story;
    }

    public void addStory(String story) {
        stories.put(stories.size() + 1, story);
        System.out.println("Story added!");
    }

    public void removeStory(int storyId) {
        stories.remove(storyId);
        System.out.println("Story removed!");
    }
}

class UserInputManager {
    private final Scanner scanner = new Scanner(System.in);

    public Map<PlaceHolderType, String> collectUserInputs() {
        Map<PlaceHolderType, String> userInputs = new EnumMap<>(PlaceHolderType.class);
        for (PlaceHolderType type : PlaceHolderType.values()) {
            System.out.print("Enter a " + type + ": ");
            userInputs.put(type, scanner.nextLine());
        }
        return userInputs;
    }
}

class StoryDisplay {
    private final Scanner scanner = new Scanner(System.in);

    public void showIntroduction() {
        System.out.println("Welcome to the Mad Libs game! Fill in the blanks with the requested words.");
    }

    public int getMenuOption() {
        System.out.println("\n1: Play the game\n2: Create a story\n3: Remove a story");
        return scanner.nextInt();
    }

    public void showStoryOptions(EnumMap<PlaceHolderType, Integer> placeholders) {
        for (Map.Entry<PlaceHolderType, Integer> entry : placeholders.entrySet()) {
            System.out.println("You need " + entry.getValue() + " " + entry.getKey() + "(s).");
        }
    }

    public void showCompletedStory(String story) {
        System.out.println("\nYour completed story:\n" + story);
    }

    public String createCustomStory() {
        scanner.nextLine(); // Clear the buffer
        System.out.println("Enter your custom story (use [NOUN], [VERB], etc. for placeholders):");
        return scanner.nextLine();
    }

    public int chooseStoryToDelete() {
        System.out.println("Enter the number of the story you want to delete:");
        return scanner.nextInt();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public boolean askPlayAgain() {
        System.out.println("Do you want to play again? (yes/no): ");
        return scanner.next().equalsIgnoreCase("yes");
    }

    public void sayGoodbye() {
        System.out.println("Goodbye, thanks for playing!");
    }
}

enum PlaceHolderType {
    NOUN, VERB, ADJECTIVE, ADVERB
}
