public class _99_Bottles {

    private static final int TOTAL_BOTTLES = 99;

    public static void main(String[] args) {
        singBottlesOfBeerSong();

    }

    public static void singBottlesOfBeerSong() {
        for (int i = 99; i > 0; i--) {
            printVerse(i);

        }
        printLastVerse();
    }

    private static void printVerse(int bottleCount) {
        if (bottleCount > 1) {
            System.out.println(bottleCount + " bottles of beer on the wall, " +
                    bottleCount + "bottles of beer. \n" +
                    "Take down and pass it around" +
                    (bottleCount - 1) + " bottles of ber on the wall.");
        } else {
            System.out.println(bottleCount + " bottles of beer on the wall, " +
                    bottleCount + "bottle of beer. \n" +
                    "Take down and pass it around, no more bottles of beer on the wall");
        }
    }

    private static void printLastVerse(){
        System.out.println("No more bottles of beer on thewall, no more bottles of beer.\n" +
                            "Go to the store and buy some more, " +
                            TOTAL_BOTTLES + " bottles of beer on the wall.");
    }
}
