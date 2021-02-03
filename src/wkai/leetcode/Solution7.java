package wkai.leetcode;

/**
 * leetcode7. 整数反转
 */
public class Solution7 {
    public static void main(String[] args) {
        System.out.println(new Solution7().reverse_v2(-123456789));
    }

  /**
   * 执行用时： 5 ms , 在所有 Java 提交中击败了 11.25% 的用户
   * 内存消耗： 35.8 MB , 在所有 Java 提交中击败了 22.74% 的用户
   * @param x
   * @return
   */
  public int reverse_v1(int x) {
        StringBuffer s = new StringBuffer();
        if(x<0){
            s.append('-');
        }
        int num_abs = Math.abs(x);
        while( num_abs/10 >0){
            s.append(num_abs%10);
            num_abs = num_abs/10;
        }
        s.append(num_abs%10);

        int rlt = 0;
        try {
            rlt = Integer.parseInt(s.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rlt;
    }

  /**
   * 执行用时：3 ms, 在所有 Java 提交中击败了20.94%的用户
   * 内存消耗：35.9 MB, 在所有 Java 提交中击败了13.60%的用户
   * @param x
   * @return
   */
  public int reverse_v2(int x) {
        StringBuffer s = new StringBuffer(Integer.toString(Math.abs(x))).reverse();
        if(x<0){
            s.insert(0,'-');
        }

        int rlt = 0;
        try {
            rlt = Integer.parseInt(s.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rlt;
    }

}
