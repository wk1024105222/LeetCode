package wkai.leetcode;

/**
 * leetcode45. 跳跃游戏 II
 */
public class Solution45 {
    public static void main(String[] args) {
        int[] test1 = new int[]{2,3,1,1,4};
        int[] test2 = new int[]{10,9,8,7,6,5,4,3,2,1,1,0};
        System.out.println(new Solution45().jump(test1));
    }

    /**
    * 执行用时： 2 ms , 在所有 Java 提交中击败了 96.14% 的用户
    * 内存消耗： 40.7 MB , 在所有 Java 提交中击败了 22.14% 的用户
    * 当前节点arr[i]=N 遍历arr[i+1]~arr[i+N] 找到某个节点arr[j] + j-i(步数) 最大
    * @param nums
    * @return
    */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int rlt = 0;
        for (int i = 0; i < nums.length; ) {
            if(nums[i]>=nums.length-(i+1)){
                rlt++;
                break;
            }
            int max = -1;
            int index = -1;
            int j = -1;
            // 寻找目标点后 步数j-i + nums[j]最大的点
            for(j=i+1;j<=i+nums[i];j++){
                if(j-i+nums[j]>=max){
                    max = j-i+nums[j];
                    index = j;
                }
            }
            i=index;
            rlt++;
        }
        return rlt;
    }
}
