package Leetcode;

import java.util.Arrays;

public class MergeTwoSortedArrays {
    public static void main(String[] args) {
    Solution88 solution88 = new Solution88();
    solution88.merge(new int[]{1, 2, 3, 0, 0, 0},3, new int[]{2, 5, 6},3);
    }
}

class Solution88{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0;i < m;i++){
            if(nums1[i]<nums2[1] && nums1[i+1]<nums2[i]&& i<n){
                nums2[i] = nums1[i+1];
            }
        }

        System.out.println(Arrays.toString(nums1));

    }
}

