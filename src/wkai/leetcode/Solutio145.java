package wkai.leetcode;

import wkai.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode145. 二叉树的后序遍历
 */
public class Solutio145 {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,null,2,3};
        a[1]=null;
        System.out.println(new Solutio145().postorderTraversal(new TreeNode(a)));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 85.83% 的用户
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rlt = new ArrayList<>();
        post(root, rlt);
        return rlt;
    }

    private void post(TreeNode node, List<Integer> list) {
        if(node==null){
            return ;
        }
        post(node.left,list);
        post(node.right,list);
        list.add(node.val);
    }
}
