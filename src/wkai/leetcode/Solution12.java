package wkai.leetcode;

/**
 * leetcode12. 整数转罗马数字
 */
public class Solution12 {
    public static void main(String[] args) {
        System.out.println(new Solution12().intToRoman(1994));
    }

  /**
   * 执行用时： 8 ms , 在所有 Java 提交中击败了 24.16% 的用户
   * 内存消耗： 38.5 MB , 在所有 Java 提交中击败了 27.51% 的用户
   * @param num
   * @return
   */
  public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        String[][] base = new String[][]{
                {"1","I"},
                {"4","IV"},
                {"5","V"},
                {"9","IX"},
                {"10","X"},
                {"40","XL"},
                {"50","L"},
                {"90","XC"},
                {"100","C"},
                {"400","CD"},
                {"500","D"},
                {"900","CM"},
                {"1000","M"}
        };
        for(int i=base.length-1; i>=0; i--){
            int a = num/Integer.parseInt(base[i][0]);
            if( a != 0 ){
                for(int j=0;j!=a;j++){
                    sb.append(base[i][1]);
                    num = num%Integer.parseInt(base[i][0]);
                }
            }
        }
        return sb.toString();
    }
}
