public class BottlesOfBeer {

    private static final int TOTAL_BOTTLES = 99;

    public static void main(String[] args) {
        singBottlesOfBeerSong();
    }

    /**
     * Sings the "99 Bottles of Beer" song from TOTAL_BOTTLES down to 0.
     */
    public static void singBottlesOfBeerSong() {
        for (int i = TOTAL_BOTTLES; i > 0; i--) {
            printVerse(i);
        }
        printLastVerse();
    }

    /**
     * Prints a verse of the song for the given bottle count.
     * @param bottleCount The current number of bottles.
     */
    private static void printVerse(int bottleCount) {
        if (bottleCount > 1) {
            System.out.println(bottleCount + " bottles of beer on the wall, " +
                    bottleCount + " bottles of beer.\n" +
                    "Take one down and pass it around, " +
                    (bottleCount - 1) + " bottles of beer on the wall.");
        } else {
            System.out.println(bottleCount + " bottle of beer on the wall, " +
                    bottleCount + " bottle of beer.\n" +
                    "Take one down and pass it around, no more bottles of beer on the wall.");
        }
    }

    /**
     * Prints the final verse of the song when there are no bottles left.
     */
    private static void printLastVerse() {
        System.out.println("No more bottles of beer on the wall, no more bottles of beer.\n" +
                "Go to the store and buy some more, " +
                TOTAL_BOTTLES + " bottles of beer on the wall.");
    }
}