package wkai.leetcode;

import java.util.*;

/**
 * leetcode17. 电话号码的字母组合
 */
public class Solution17 {
    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations_v2("23"));
    }

    public static Map<Character,String[]> staticMap = new HashMap<>();

    static {
        staticMap.put('2', new String[] {"a", "b", "c"});
        staticMap.put('3', new String[] {"d", "e", "f"});
        staticMap.put('4', new String[] {"g", "h", "i"});
        staticMap.put('5', new String[] {"j", "k", "l"});
        staticMap.put('6', new String[] {"m", "n", "o"});
        staticMap.put('7', new String[] {"p", "q", "r", "s"});
        staticMap.put('8', new String[] {"t", "u", "v"});
        staticMap.put('9', new String[] {"w", "x", "y", "z"});
    }
    /**
    * 使用分治思想 8个数 第一次12 34 56 78 第二次 1234 5678 第三次 12345678
    * 执行用时： 1 ms , 在所有 Java 提交中击败了 83.87% 的用户
    * 内存消耗： 37.3 MB , 在所有 Java 提交中击败了 50.56% 的用户
    * @param digits
    * @return
    */
    public List<String> letterCombinations(String digits) {
        if(digits.equals("") || digits==null){
            return new ArrayList<String>();
        }
        List<List<String[]>> a = new ArrayList<>();
        int num = 0;
        a.add(new ArrayList<String[]>() );
        for(int i=0; i<digits.length();i++){
            a.get(num).add(staticMap.get(digits.charAt(i)));
        }
        num++;
        while(a.get(a.size()-1).size()>1){
            List<String[]> curr = a.get(a.size()-1);
            a.add(new ArrayList<String[]>() );
            for(int i=0; i<curr.size();i+=2){
                int j=i+1;
                if(j <curr.size()){
                    a.get(num).add(dikaer(curr.get(i), curr.get(j)));
                } else {
                    a.get(num).add(curr.get(i));
                }
            }
            num++;
        }
        return Arrays.asList(a.get(a.size()-1).get(0));
    }

    /**
    * 执行用时： 1 ms , 在所有 Java 提交中击败了 83.87% 的用户
    * 内存消耗： 37 MB , 在所有 Java 提交中击败了 90.95% 的用户
    *
    * @param digits
    * @return
    */
    public List<String> letterCombinations_v2(String digits) {
        if(digits.equals("") || digits==null){
            return new ArrayList<String>();
        }
        String[] tmp = staticMap.get(digits.charAt(0));
        for(int i=1; i<digits.length();i++){
            tmp = dikaer(tmp,staticMap.get(digits.charAt(i)));
        }
        return Arrays.asList(tmp);
    }

    private String[] dikaer(String[] arr1, String[] arr2) {
        String[] rlt = new String[arr1.length*arr2.length];
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr2.length;j++){
                rlt[i*arr2.length+j] = arr1[i]+arr2[j];
            }
        }
        return rlt;
    }


}
