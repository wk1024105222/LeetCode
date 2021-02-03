package wkai.leetcode;

/**
 * leetcode9. 回文数
 */
public class Solution9 {
    public static void main(String[] args) {
        System.out.println(new Solution9().isPalindrome(-1));
    }

  /**
   * 不满足 不转String实现
   * 执行用时：10 ms, 在所有 Java 提交中击败了78.08%的用户
   * 内存消耗：38.2 MB, 在所有 Java 提交中击败了18.42%的用户
   * @param x
   * @return
   */
    public boolean isPalindrome_1(int x) {
        if(x<0){
            return false;
        }
        boolean rlt = true;
        String tmp = Integer.toString(x);
        for(int i=0,j=Integer.toString(x).length()-1; i<=j;i++,j--){
            if(tmp.charAt(i) != tmp.charAt(j)){
                rlt = false;
                break;
            }
        }
        return rlt;
    }

  /**
   * 没有用String
   * 执行用时： 12 ms , 在所有 Java 提交中击败了 34.80% 的用户
   * 内存消耗： 38.1 MB , 在所有 Java 提交中击败了 19.26% 的用户
   * @param x
   * @return
   */
  public boolean isPalindrome(int x) {
        if(x<0){
          return false;
        }
        boolean rlt = true;
        int[] arr = new int[10];
        int index = 0;
        while( x/10 >0){
            arr[index++] = x%10;
            x = x/10;
        }
        arr[index] = x%10;

        for(int i=0,j=index; i<=j;i++,j--){
            if(arr[i] != arr[j]){
                rlt = false;
                break;
            }
        }
        return rlt;
    }



}
