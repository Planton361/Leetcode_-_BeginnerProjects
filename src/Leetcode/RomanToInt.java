package Leetcode;

import java.util.HashMap;

class RomanToInt {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt(""));
    }
}

class Solution {
    public int romanToInt(String s) {

        int sum = 0;

        HashMap<Character, Integer> romanNumber = new HashMap<>();

        romanNumber.put('I',1);
        romanNumber.put('V',5);
        romanNumber.put('X',10);
        romanNumber.put('L',50);
        romanNumber.put('C',100);
        romanNumber.put('D',500);
        romanNumber.put('M',1000);

        for(int i = 0; i<s.length()-1;i++){
            if(romanNumber.get(s.charAt(i))<romanNumber.get(s.charAt(i+1))){
                sum -= romanNumber.get(s.charAt(i));
            }
            else{
                sum += romanNumber.get(s.charAt(i));
            }
        }

        return sum + romanNumber.get(s.charAt(s.length()-1));

    }
}

