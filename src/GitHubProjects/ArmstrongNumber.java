package GitHubProjects;

public class ArmstrongNumber {


    public static void main(String[] args) {
        isArmstrongNumber(407);
    }

    private static void isArmstrongNumber(int number){
        boolean isArmstrongNumber = isArmstrongNumberController(number);
        if (isArmstrongNumber){
            System.out.println(number + " is an armstrong number");
        }
        else{
            System.out.println(number + " is not an armstrong number");
        }

    }


    private static boolean isArmstrongNumberController(int number){
        int numberOfDigits;
        boolean isArmstrongNumber;
        //Anzahl Ziffern bestimmen
        numberOfDigits = getNumberOfDigits(number);
        //Einzelne Ziffern bestimmen
        int[] arrayOfNumbers = getDigits(number, numberOfDigits);
        //Armstrongnummer berechnen
        int armstrong = calculateArmstrong(arrayOfNumbers, numberOfDigits);

        isArmstrongNumber = armstrong == number;
        return isArmstrongNumber;
    }

    private static int getNumberOfDigits(int number){
        String numberString = Integer.toString(number);
        return numberString.length();
    }

    private static int[]getDigits(int number, int numberOfDigits){
        final int base = 10;
        int multiplikator;
        int[] arrayOfNumbers = new int[numberOfDigits];
            for(int exponent = numberOfDigits; exponent>0; exponent--){
                multiplikator = (int)(Math.pow(base,exponent-1));
                arrayOfNumbers[exponent-1] = number / multiplikator;
                number %= multiplikator;
            }
        return arrayOfNumbers;
    }

    public static int calculateArmstrong(int[] arrayOfNumbers, int numberOfDigits){
        int armstrongSum = 0;
        for (int number:arrayOfNumbers){
            armstrongSum += (int)Math.pow(number,numberOfDigits);
        }
        return armstrongSum;
    }

}

