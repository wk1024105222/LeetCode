package wkai.leetcode.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Integer[] arr){
        List<TreeNode> list = new ArrayList<>();
        this.val=arr[0];
        list.add(this);
        for(int i=1;i!=arr.length;i++){
            if(arr[i]==null){
                list.add(null);
            } else {
                list.add(new TreeNode(arr[i]));
            }
        }
        for(int i=0;i!=arr.length;i++){
            if(list.get(i) == null ){
                continue;
            }
            list.get(i).left = i*2+1<list.size()?list.get(i*2+1):null;
            list.get(i).right = i*2+2<list.size()?list.get(i*2+2):null;
        }
    }
}