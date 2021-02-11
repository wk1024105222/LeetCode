package wkai.leetcode;

/**
 * leetcode326. 3的幂
 */
public class Solutio326 {
    public static void main(String[] args) {
        System.out.println(new Solutio326().isPowerOfThree(1));
    }

    /**
    * 执行用时： 15 ms , 在所有 Java 提交中击败了 99.50% 的用户
    * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 22.20% 的用户
    * @param n
    * @return
    */
    public boolean isPowerOfThree(int n) {
        if(n==0){
            return false;
        }

        while(n!=1){
            if(n%3==0 ){
                n/=3;
            } else {
                return false;
            }
        }
        return true;
    }
}
