package wkai.leetcode.util;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int[] a){
        ListNode head = new ListNode(a[0]);
        ListNode tmp =head;
        for(int i=1;i<a.length;i++){
            tmp.next= new ListNode(a[i]);
            tmp = tmp.next;
        }
        this.val = head.val;
        this.next = head.next;
    }

    @Override
    public String toString() {
        StringBuffer sb =new StringBuffer();
        ListNode index = this;
        while(index.next != null){
            sb.append(index.val).append("\t");
            index=index.next;
        }
        sb.append(index.val);
        return sb.toString();
    }
}
