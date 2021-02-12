package wkai.leetcode;

/**
 * leetcode189. 旋转数组
 */
public class Solutio189 {
    public static void main(String[] args) {
        int[] source = new int[]{1,2,3,4,5,6,7};

        int[] source1 = new int[]{-1,-100,3,99};
        new Solutio189().rotate_v2(source1,2);
        for(int i : source1){
            System.out.printf("%d\t" , i);
        }
    }

    /**
    * 题目目的是将k%=nums.length 是将数组重组为 后K个挪到前面，前nums.length-k个挪到后面
    * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
    * 内存消耗： 38.9 MB , 在所有 Java 提交中击败了 57.71% 的用户
    * 使用了临时数组不满足 "你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？"
    * @param nums
    * @param k
    */
    public void rotate_v2(int[] nums, int k) {
        int len  = nums.length;
        k%=len;
        int[] tmpArr = new int[k];
        int index=0;
        for(int i=len-k;i!=len;i++){
            tmpArr[index++]=nums[i];
        }

        for(int i =len-k-1; i>=0; i--){
            nums[(i+k)%len]=nums[i];
        }

        for(int i=0;i!=k;i++){
            nums[i]=tmpArr[i];
        }
    }

    /**
     * 超出时间限制
     * @param nums
     * @param k
     */
    public void rotate_v1(int[] nums, int k) {
        int len  = nums.length;
        k%=len;
        int tmp =nums[0];
        for(int i =0; i<k; i++){
            for(int j=0;j<len;j++){
                int a = nums[(i+j+1)%len];
                nums[(i+j+1)%len]=tmp;
                tmp=a;
            }
        }
    }
}
