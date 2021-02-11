package wkai.leetcode;

/**
 * leetcode504. 七进制数
 * 测试结果：通过
 * 执行用时：1 ms, 在所有 Java 提交中击败了90.81%的用户
 * 内存消耗：35.6 MB, 在所有 Java 提交中击败了85.97%的用户
 */
public class Solution504 {
    public static void main(String[] args) {
        System.out.println(new Solution504().convertToBaseN(-81,7));
    }

    public String convertToBase7(int num) {
        StringBuffer s = new StringBuffer();
        int num_abs = Math.abs(num);
        double tmp ;
        while((tmp = num_abs/7) >0){
            s.append(num_abs%7);
            num_abs = num_abs/7;
        }
        s.append(num_abs%7);
        return num<0?s.append('-').reverse().toString():s.reverse().toString();
    }

    public String convertToBaseN(int num, int n) {
        StringBuffer s = new StringBuffer();
        int num_abs = Math.abs(num);
        double tmp ;
        while((tmp = num_abs/n) >0){
            s.append(num_abs%n);
            num_abs = num_abs/n;
        }
        s.append(num_abs%n);
        return num<0?s.append('-').reverse().toString():s.reverse().toString();
    }
}
