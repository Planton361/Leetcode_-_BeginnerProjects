package Leetcode;

public class LastWord {
    public static void main(String[] args) {
        Solution58 solution58 = new Solution58();
        System.out.println(solution58.lengthOfLastWord("Hello World"));
    }


}

class Solution58 {
    public int lengthOfLastWord(String s) {
        String[] words = s.split("\\s+");
        String lastWord = words[words.length-1];
        return lastWord.length();
    }
}
