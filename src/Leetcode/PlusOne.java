package Leetcode;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        int[] i = {9};
        System.out.println(Arrays.toString(solution4.plusOne(i)));
    }
}

class Solution4 {
    public int[] plusOne(int[] digits) {
        int number=0;

        for(int i = 0; i<digits.length;i++){
            number += (int) ((digits[i])*Math.pow(10,digits.length-i-1));
        }
        number++;


        int counter = 0;
        while(number!= 0){
            if(number/Math.pow(10,digits.length)!=0){
                digits[counter]= (int) (number/Math.pow(10,digits.length-1));
                number %= (int) Math.pow(10,digits.length-1);
                counter++;
            }
            digits[counter]= (int) (number/Math.pow(10,digits.length-counter-1));
            if(digits[counter]%10 == 0)
            number %= (int) (Math.pow(10,digits.length-counter-1));
            counter++;
        }


        return digits;
    }
}
