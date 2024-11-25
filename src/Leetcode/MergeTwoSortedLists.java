package Leetcode;


public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution21 solution21 = new Solution21();
        solution21.mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(3))), new ListNode(1, new ListNode(3, new ListNode(4))));
    }
}

class Solution21{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {


    }
}

/*
 * Definition for singly-linked list.
 */
 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
