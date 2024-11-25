package Leetcode;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        System.out.println(solution6.climbStairs(2));
    }
}

class Solution6 {
    public int climbStairs(int n) {
        int[] fabinoccinumber = new int[n+1];
        fabinoccinumber[0] = 1;
        fabinoccinumber[1] = 1;
        for (int i = 2; i <= n; i++) {
            fabinoccinumber[i] = fabinoccinumber[i - 2] + fabinoccinumber[i - 1];
        }
        return fabinoccinumber[n];


    }
}
