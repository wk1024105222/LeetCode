package wkai.leetcode;

import wkai.leetcode.util.ListNode;

/**
 * leetcode25. K 个一组翻转链表
 */
public class Solution25 {
    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1,2,3,4,5});
        ListNode b = new Solution25().reverseKGroup_1(head, 3);
        System.out.println(b);
    }

    /**
    * 创建一个数组 存储当前K个节点 倒叙遍历数组 重塑节点next 注意上一组的新end 要更新为 重塑组的begin
    * 不确定 是否符合你的算法只能使用常数的额外空间 的要求
    * 执行用时： 1 ms , 在所有 Java 提交中击败了 35.65% 的用户
    * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 70.00% 的用户
    * @param head
    * @param k
    * @return
    */
    public ListNode reverseKGroup_1(ListNode head, int k) {
        ListNode[] arr = new ListNode[k];
        ListNode kong = new ListNode(-1,head);
        ListNode end = kong;

        ListNode preGroupEnd = kong;
        while(true){
            int i=0;
            while(i<k && (end=end.next)!=null){
                arr[i]=end;
                i++;
            }
            if(i!=k){
                break;
            }

            ListNode tmp = arr[k-1].next;

            for(int a = k-1;a>0;a--){
                arr[a].next=arr[a-1];
            }
            arr[0].next=tmp;
            preGroupEnd.next=arr[k-1];
            preGroupEnd = arr[0];
            end = arr[0];
        }
        return kong.next;
    }

    /**
     * 失败 2个 逐个替换 12345,3 ->231 45
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode kong = new ListNode(-1,head);
        ListNode begin = kong;
        ListNode end = kong;

        while(true){
            int i=0;
            while(i<k && (end=end.next)!=null){
                i++;
            }
            if(i!=k){
                break;
            }

            while(begin!=end){
                ListNode next = begin.next;
                ListNode next2 = next.next;
                next.next=next2.next;
                begin.next=next2;
                next2.next=next;
                begin=begin.next;
            }
            end = end.next;
            begin = end;
        }
        return kong.next;
    }
}
