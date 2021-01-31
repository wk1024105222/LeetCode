package wkai.leetcode;

import java.util.ArrayList;

/**
 * leetcode4. 寻找两个正序数组的中位数 V1.0版本
 * 数组长度合为2n 获取n、n+1 求平均 为单数 则直接取n
 * 通过快排 计算第n大的值
 * 测试结果：通过
 * 执行用时：508 ms, 在所有 Java 提交中击败了5.03%的用户
 * 内存消耗：40 MB, 在所有 Java 提交中击败了5.76%的用户
 */
public class Solution4V1 {
    public static void main(String[] args) {
//        int[] num1 = new int[]{9,4,90,56,87,93,2,6,9,12};
//        int[] num2 = new int[]{5,90,512,21,34,35,64,57,82,18,100};
        int[] num1 = new int[]{};
        int[] num2 = new int[]{};

        System.out.println(new Solution4V1().findMedianSortedArrays(num1,num2));
    }

    public double findMedianSortedArrays(int[] num1, int[] num2) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int a=0;a<num1.length;a++){
            list.add(num1[a]);
        }
        for(int a=0;a<num2.length;a++){
            list.add(num2[a]);
        }
        if(list.size()==0){
            return 0;
        }

        int tar1 = list.size()/2;
        int tar2 = list.size()/2+1;
        int rlt1 ;
        int rlt2 = getNoNFromArray(list,tar2);

        if (list.size() % 2 == 1) {
            return rlt2;
        } else{
            rlt1 =  getNoNFromArray(list,tar1);
            return (rlt1+rlt2)/2.0;
        }

    }

    private int getNoNFromArray(ArrayList<Integer> list, int k) {
        if(list.size()==1 ){
            return list.get(0);
        }   else {
            ArrayList<Integer> sub_small = new ArrayList<>();
            ArrayList<Integer> sub_big = new ArrayList<>();

            int index =0;
            int base;
            do{
                if(index==list.size()){
                    return list.get(0);
                }
                base = list.get(index);
                sub_small.clear();
                sub_big.clear();
                for(int a : list){
                    if( a < base ){
                        sub_small.add(a);
                    }else {
                        sub_big.add(a);
                    }
                }
                index++;
            } while(sub_small.size()==0 || sub_big.size()==0);

            if(sub_small.size()>=k){
                return getNoNFromArray(sub_small,k);
            }else {
                return getNoNFromArray(sub_big,k-sub_small.size());
            }
        }
    }

}
