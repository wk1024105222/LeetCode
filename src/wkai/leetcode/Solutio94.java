package wkai.leetcode;

import wkai.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode94. 二叉树的中序遍历
 */
public class Solutio94 {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,null,2,3};
        a[1]=null;
        System.out.println(new Solutio94().inorderTraversal_v1(new TreeNode(a)));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.7 MB , 在所有 Java 提交中击败了 56.86% 的用户
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_v1(TreeNode root) {
        List<Integer> rlt = new ArrayList<>();
        middle(root, rlt);
        return rlt;
    }

    private void middle(TreeNode node, List<Integer> list) {
        if(node==null){
            return ;
        }
        middle(node.left,list);
        list.add(node.val);
        middle(node.right,list);
    }
}
