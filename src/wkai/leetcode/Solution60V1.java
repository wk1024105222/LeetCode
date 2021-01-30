package wkai.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode60. 排列序列 V1.0版本
 * 给定n，k2个数字 计算1～n 组成的所有数字中第k个
 * 若n=9 则一共9*8*7*6*5*4*3*2*1个数字
 * 当选定第1个数时 可以组成8！个数字， k/8! 可以确定 在第那个阶段 则第1个数确定 k=k-k/8!*8!
 * 然后 当选定第2个数时 可以组成7！个数字， k/7! 可以确定 在第那个阶段 则第2个数确定
 * 然后递归 直到最后一个数
 * 测试结果：通过
 * 执行用时：3 ms, 在所有 Java 提交中击败了38.51%的用户
 * 内存消耗：35.9 MB, 在所有 Java 提交中击败了52.10%的用户
 */
public class Solution60V1 {
    public static void main(String[] args) {
        System.out.println(new Solution60V1().getPermutation(9, 100));
    }

    public String getPermutation(int n, int k) {
        StringBuffer rlt = new StringBuffer();
        // 存储1～n
        List<Integer> cells = new LinkedList<Integer>();
        for (int m=0;m<n;m++){
            cells.add(m+1);
        }

        // 存储1！～n！
        int[] jiechengList = new int[n+1];
        jiechengList[0]=1;
        jiechengList[1]=1;
        for(int i=2;i<n+1;i++){
            jiechengList[i]=i*jiechengList[i-1];
        }

        int a = -1;
        do {
            if(k==1){
                for(Integer tmp:cells)
                    rlt.append(tmp);
                break;
            }
            //计算
            a = k/(jiechengList[n-1]);
            if(k%(jiechengList[n-1])==0){
                a--;
            }
            rlt.append(cells.get(a));

            cells.remove(a);
            k = k-(jiechengList[n-1])*(a);
            n--;
        } while (n>=1);
        return rlt.toString();
    }
}
