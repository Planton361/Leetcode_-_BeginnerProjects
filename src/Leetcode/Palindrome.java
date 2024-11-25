package Leetcode;

public class Palindrome {


    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        solution.isPalindrome(1221);

    }

}

class Solution1 {
    public boolean isPalindrome(int x) {
        String num = String.valueOf(x);

        String palindrome = new StringBuilder(num).reverse().toString();

        return  (palindrome.equals(num));


    }
}
