package wkai.leetcode;

import wkai.leetcode.util.ListNode;

/**
 * leetcode21. 合并两个有序链表
 * 测试结果：通过
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：38.1 MB, 在所有 Java 提交中击败了12.09%的用户
 */
public class Solution21 {
    public static void main(String[] args) {
//        System.out.println(new Solution21().mergeTwoLists(new int[][]{{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}}));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode rlt = new ListNode();
        ListNode tmp = new ListNode() ;
        rlt.next=tmp;
        while(l1 !=null || l2!=null){
            if(l1 !=null && l2!=null){
                if(l1.val<l2.val){
                    tmp.next = l1;
                    l1=l1.next;
                } else {
                    tmp.next = l2;
                    l2=l2.next;
                }
            } else if(l1 !=null){
                tmp.next = l1;
                l1=l1.next;
            } else {
                tmp.next = l2;
                l2=l2.next;
            }
            tmp = tmp.next;
        }

        return rlt.next.next;
    }
}
