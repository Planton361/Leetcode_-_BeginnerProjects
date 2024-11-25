package Leetcode;

public class SearchInsertPosition {
    public static void main(String[] args) {
        Solution35 solution35 = new Solution35();
        System.out.println(solution35.searchInsert(new int[]{1, 3, 5, 6},2));

        String a = "absde";
        a.length();

    }
}

class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        int mid = 0;

        while (low <=high){
            mid = (low + high)/2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid]>target){
                high = mid-1;
            }
            else if(nums[mid]<target){
                low = mid+1;
            }
        }
        return mid+1;
    }
}

