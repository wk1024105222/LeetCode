package wkai.leetcode;

import wkai.leetcode.util.ListNode;

/**
 * leetcode2. 两数相加
 */
public class Solution2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{2,4,9});
        ListNode l2 = new ListNode(new int[]{5,6,4});
        System.out.println(new Solution2().addTwoNumbers(l1,l2));
    }

    /**
    * 执行用时： 2 ms , 在所有 Java 提交中击败了 99.95% 的用户
    * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 32.73% 的用户
    * @param l1
    * @param l2
    * @return
    */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode index1 = l1;
        ListNode index2 = l2;
        ListNode kong = new ListNode(-1);
        ListNode rlt = kong;
        int jinwei = 0;
        while(index1!=null || index2!=null ||jinwei!=0){
            int sum=jinwei;
            if(index1!=null){
                sum+=index1.val;
                index1 = index1.next;
            }
            if(index2!=null){
                sum+=index2.val;
                index2 = index2.next;
            }
            rlt.next = new ListNode(sum%10);
            rlt = rlt.next;
            jinwei=sum/10;

        }
        return kong.next;
    }
}
