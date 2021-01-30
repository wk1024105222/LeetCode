package wkai.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode227. 基本计算器 II V2.0版本
 * 采用伪栈思想 定义3个数值变量num1～3 2个运算符变量 oper1～2
 * 对于int （+-/*） int （+-/*） int 的表达式
 * 若2个都是加减法或都是乘除法 则完成前2个数的计算 num1=运算结果 num2=num3 num3等于下一个值oper1=oper2 oper2=下一个运算符
 * 若1个/* 1个+- 则完成乘除法 再取下一个值以及运算符 遍历
 * 遍历string 一次即可
 * 测试结果：通过
 * 执行用时：19 ms, 在所有 Java 提交中击败了35.09%的用户
 * 内存消耗：39.5 MB, 在所有 Java 提交中击败了44.62%的用户
 */
public class Solution227V2 {
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
        System.out.println(new Solution227V2().calculate(str));
    }

    public int calculate(String s) {
        //去空格
        String str = s.replace(" ", "");

        Integer num_1 ;
        Integer num_2;
        Integer num_3;

        char oper_1;
        char oper_2;
        char oper_3;
        int index = 0;
        int[] tmp ;

        tmp=this.nextNum(str,index);
        num_1 = tmp[0];
        index = tmp[1];
        if(index == -1){
            return num_1;
        }
        oper_1 = str.charAt(index-1);

        tmp=this.nextNum(str,index);
        num_2 = tmp[0];
        index = tmp[1];
        if(index == -1){
            switch (oper_1) {
                case '+':return num_1+num_2;
                case '-':return num_1-num_2;
                case '*':return num_1*num_2;
                case '/':return num_1/num_2;
            }
        }
        oper_2 = str.charAt(index-1);

        while(true){

            tmp=this.nextNum(str,index);
            num_3 = tmp[0];
            index = tmp[1];


            if((oper_1=='+' || oper_1=='-') && (oper_2=='+' || oper_2=='-')){
                num_1 = oper_1=='+'?num_1+num_2:num_1-num_2;
                oper_1 = oper_2;
                num_2=num_3;

            } else if((oper_1=='*' || oper_1=='/') && (oper_2=='*' || oper_2=='/')){
                num_1 = oper_1=='*'?num_1*num_2:num_1/num_2;
                oper_1 = oper_2;
                num_2=num_3;
            } else {
                if(oper_1=='*' || oper_1=='/'){
                    num_1 = oper_1=='*'?num_1*num_2:num_1/num_2;
                    oper_1 = oper_2;
                    num_2=num_3;
                } else {
                    num_2 = oper_2=='*'?num_2*num_3:num_2/num_3;
                }

            }
            if(index == -1){
                switch (oper_1) {
                    case '+':return num_1+num_2;
                    case '-':return num_1-num_2;
                    case '*':return num_1*num_2;
                    case '/':return num_1/num_2;
                }
            }
            oper_3 = str.charAt(index-1);
            oper_2=oper_3;
        }
    }

    /**
     * 获取自index起 第一个数字 以及下一个数字的index 如果是最后一个数字 则返回-1
     * @param str
     * @param index
     * @return
     */
    private int[] nextNum(String str, int index) {
        if(index>=str.length()){
            return null;
        }else{
            StringBuilder sb = new StringBuilder();
            int i=index;
            for(;i<str.length();i++){
                if(str.charAt(i)<='9' && str.charAt(i)>='0'){
                    sb.append(str.charAt(i));
                }else {
                    break;
                }
            }
            if(i == str.length()){
                return new int[]{Integer.parseInt(sb.toString()),-1};
            }
            return new int[]{Integer.parseInt(sb.toString()),i+1};
        }
    }
}
