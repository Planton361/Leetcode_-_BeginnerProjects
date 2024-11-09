public class ArmstrongNumber {
    public static void main(String[] args) {
        armstrongNumber(153);
    }

    public static void armstrongNumber(int a) {

        String number = String.valueOf(a);
        int digits = number.length();
        int b = a;
        int potenz = digits;
        int sum = 0;

        for (int i = 0; i < digits; i++) {
            int multiplikator = (int) Math.pow(10, (potenz - 1));
            int digitnumber = b / multiplikator;
            b %=multiplikator;
            sum +=(int)(Math.pow(digitnumber, digits));
            potenz--;

        }
        System.out.println((a == sum));
    }
}

