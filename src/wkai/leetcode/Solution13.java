package wkai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode13. 罗马数字转整数
 */
public class Solution13 {
    public static void main(String[] args) {
        System.out.println(new Solution13().romanToInt("MCMXCIV"));
    }

  /**
   * 执行用时： 8 ms , 在所有 Java 提交中击败了 29.45% 的用户
   * 内存消耗： 39 MB , 在所有 Java 提交中击败了 24.19% 的用户
   * @param s
   * @return
   */
  public int romanToInt(String s) {
        int rlt = 0;

        Map<Character, Integer > map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        for(int i=0; i<s.length();i++){
            int j=i+1;
            if(j<s.length()){
                int left = map.get(s.charAt(i));
                int right = map.get(s.charAt(j));
                if(left < right){
                    rlt = rlt - map.get(s.charAt(i));
                    rlt += map.get(s.charAt(j));
                    i++;
                } else {
                    rlt += map.get(s.charAt(i));
                }
            } else {
                rlt += map.get(s.charAt(i));
            }
        }
        return rlt;
    }
}
