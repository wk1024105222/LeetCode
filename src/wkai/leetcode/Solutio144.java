package wkai.leetcode;

import wkai.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode144. 二叉树的前序遍历
 */
public class Solutio144 {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,null,2,3};
        a[1]=null;
        System.out.println(new Solutio144().preorderTraversal(new TreeNode(a)));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.6 MB , 在所有 Java 提交中击败了 75.46% 的用户
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rlt = new ArrayList<>();
        pre(root, rlt);
        return rlt;
    }

    private void pre(TreeNode node, List<Integer> list) {
        if(node==null){
            return ;
        }
        list.add(node.val);
        pre(node.left,list);
        pre(node.right,list);
    }


}
