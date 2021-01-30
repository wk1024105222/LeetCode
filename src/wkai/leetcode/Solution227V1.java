package wkai.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode227. 基本计算器 II V1.0版本
 * str.split([四则运算]) 使用四则运算符 分割str 获取数值数组
 * str.split([1-9]) 使用数值 分割str 获取运算符数组
 * 第一次遍历运算符数组 处理乘除法 将乘除法元算 完成计算 剩余都是加减法
 * 第一次遍历运算符数组 顺序处理加减法即可
 * 测试结果:超出时间限制 109测试 测试到109个 超时了 109是个16W长的String
 */
public class Solution227V1 {
    public static void main(String[] args) {
        String str = "1+7-7+3+3+6-3+1-8-2-6-1+8-0+0-2+0+10-6-9-9+0+6+4+2+7+1-4-6-6-0+6+3-7+0-4+10" +
                "-2-5+6-1-3+7+7+2+0+2-8+7+2-3-8-9-6+10-7-6+3-8+5+6-7-10-6-8-10-8+1+9+1-9-1+10+10+3" +
                "+7-1-10+1-0-7+0-3-3+4+7-9-10-1+4-8-3-0-1-0-3+5-10+6-6-0-6-6-7+7+10+10-5-9-10-2-8+9" +
                "-2-8-7-9-0-6-5-1+1+3+8-5-8+3-9+9+6-5+0-2+0+8+8-4+6+1-2-0-10-8+1-2-8+2-2-2-4+2+5+3-9" +
                "+1+9-8+9-8+7+10+1+10-9+2+2+8+7-10-8+6+6+3+0+4-1+0+7-3+8-8-4+8-6-6+3-3-9+6+4+6+7-2-0" +
                "+6-10+8-2-4+3-8+1-2+8+1-2-4-3-9-4-1-3+5+9+7-8-2+7-10+7+9+1+5-5+8-3-10-7-1-7+10+3+2-8" +
                "-8+0+9+3+6+8+4+2+10+8+6-1+2+10-5+5+4-2+10+7-6-5+9-9+5-5-2+5+2-1+7-8+4-2+2+2+5-10-7-0" +
                "+5-8-6-10-5+9-1+1-8+10-7+2-3-3+2+3-8+4-6-7+3-0+6-6-3+1+2-6+2+3+0-4-0+3-5-1-4-0+9+5-6" +
                "+3-10+0+10-4+6-6-5-6+5+3+7-4+6+2+0+10+4-3+10-10-0-10-4-8+9-5-0-0-9-8-3-2+6-2+5-4-6+7" +
                "-8+8-8+10-0+10-3-9-5+0+10+6+9-3-0-8+4+5-4-9+0-2-3-0-9+1-4-1-6-9+1-0-0-5+1-6+1+6+0-4-9" +
                "+10+2+0-8+0-10-3+5+6-3+5-1-0+6-5+5-0+0-4-1-0-4-6-5+5+1+10+10+6+0+3-6-9-9+2+8+3-2+10-5" +
                "-8-4+9-6+7+3+2-9-8+8-9-6+3-7+7+10+3-10-2-7-4+3+3+10+5-6-8+10-6+1-8-5+10-0-6-8-7-10-0+5" +
                "-3+10+9-8-7+2-2+2-8-5+6-6+9+3+1+0+7-9+10-0+8-0+2+8-2+4+10-6-2-9+3-9+4-10-2-6+6+8-10+7+9" +
                "-4-8-1-9-8+2+8+10-3-4-8-0-7-10-6-6-2+4-2-1-7+1+8+5-0+8+7+0+0-6-10-7+9-3+10+7*10-3-7-4-2" +
                "+2+9+8+5+2+0-3+2-5+10-3+3+2-10+5-4-1+7+1-3-8-2-1-1-6-7+6+10+3-10+2+2+8+6+10+7+9+8+3+4+10" +
                "+4+1+2-1-1+0-7-7+8-10-7-1+4-9+1+8+3+9-9+8-1+0-9-7+7+4-3-5-8+10+7+9-8-5-8+4+10-3-3+1+7/2-9+3" +
                "+8-1+8-5-10-9-0+9-8+1+1-8-7-4-3-8-7-9+6-1+1-2+6-6+8-7-10-3+8-7-0-3+6+1+10+8+3+9-2+8+3-9-2" +
                "+0-4+2+2-10+7-2-8+2-9-1+8-9-9-2-8-7+3-1+9-5+0-2-10-9+7+10-2+8-7-8-8+0-3-7+2+10+4+4+2-9+5+2" +
                "+2-7+1-2+10-9+5+5-7-8+8-6+2-8+1-7-1-0+6-5+4-4-0-4-1-1-9-6-9-2-10+4+6-3+5+8-6+3+8+3-3-7+4-5" +
                "-3-7+8+9+0-3-5+0-10+9+10-1+1-8-9+1-9+2-8+10+2-7+10+9-9-7-4-10-0-8+10-4-4-9+2-8-10+2-6-6-9-1";
        System.out.println(new Solution227V1().calculate(str));
    }

    public int calculate(String s) {
        //去空格
        String exp_move_apace = s.replace(" ", "");
        //以运算符做分隔符获取数宇列表
        String[] nums = exp_move_apace.split("[/+-/*//]");
        //以数宇做分隔符获取操作符列表
        String[] opers_tmp = exp_move_apace.split("\\d+");
        String[] opers;
        if(opers_tmp.length==0){
            return Integer.parseInt(nums[0]);
        }
        //操作符列表第一个可能是"" 处理一下
        if (opers_tmp[0].equals("")) {
            opers = new String[opers_tmp.length - 1];
            for (int i = 0; i < opers.length; i++) {
                opers[i] = opers_tmp[i + 1];
            }
        } else {
            opers = opers_tmp;
        }
        //2个array处理成LinkedList 便于remove
        List<String> numList = new LinkedList<String>();
        Collections.addAll(numList, nums);
        List<String> opersList = new LinkedList<String>();
        Collections.addAll(opersList, opers);

        //第一次遍历操作符处理*/操作
        for (int a = 0; a < opersList.size(); ) {
            String oper = opersList.get(a);
            int rlt_tmp = -1;
            if (oper.equals("*")) {
                rlt_tmp = Integer.parseInt(numList.get(a)) * Integer.parseInt(numList.get(a + 1));
            } else if (oper.equals("/")) {
                rlt_tmp = Integer.parseInt(numList.get(a)) / Integer.parseInt(numList.get(a + 1));
            } else {
                a++;
                continue;
            }
            //运算符数组remove */运算符
            opersList.remove(a);
            //数值数组 将乘除运算第一个值 改为运算结果 remove第2值
            numList.set(a, String.valueOf(rlt_tmp));
            numList.remove(a + 1);
        }

        Integer rlt = Integer.parseInt(numList.get(0));
        //第二次递历操作符处理+-操作
        for (int j = 0; j < opersList.size(); j++) {
            String oper = opersList.get(j);
            if (oper.equals("+")) {
                rlt += Integer.parseInt(numList.get(j + 1));
            } else if (oper.equals("-")) {
                rlt -= Integer.parseInt(numList.get(j + 1));
            }

        }
        return rlt;
    }
}
