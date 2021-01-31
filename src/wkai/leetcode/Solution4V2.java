package wkai.leetcode;

import java.util.Arrays;

/**
 * leetcode4. 寻找两个正序数组的中位数 V2.0版本
 * 数组长度合为2n 获取n、n+1 求平均 为单数 则直接取n
 * 通过最大堆 计算第n大的值
 * 测试结果：通过
 * 执行用时：7 ms, 在所有 Java 提交中击败了12.32%的用户
 * 内存消耗：39.7 MB, 在所有 Java 提交中击败了60.93%的用户
 */
public class Solution4V2 {
    public static void main(String[] args) {
        int[] num1 = new int[]{9,4,90,56,87,93,2,6,9,12};
        int[] num2 = new int[]{5,90,512,21,34,35,64,57,82,100};
//        int[] num1 = new int[]{0,0,0,0,0};
//        int[] num2 = new int[]{-1,0,0,0,0,0,1};

        System.out.println(new Solution4V2().findMedianSortedArrays(num1,num2));
    }

    public double findMedianSortedArrays(int[] num1, int[] num2) {
        int[] arr = new int[num1.length+num2.length+1];
        arr[0]=Integer.MAX_VALUE;
        if(num1.length+num2.length == 0){
            return 0.0;
        }
        int index=0;
        for(int a=0;a<num1.length;a++){
            arr[++index]=num1[a];

            for(int i=index;arr[i]>arr[i/2];i/=2) {
                int t=arr[i];
                arr[i]=arr[i/2];  //父节点下移到子节点的位置
                arr[i/2]=t;
            }
        }
        for(int a=0;a<num2.length;a++){
            arr[++index]=num2[a];
            for(int i=index;arr[i]>arr[i/2];i/=2) {
                int t=arr[i];
                arr[i]=arr[i/2];  //父节点下移到子节点的位置
                arr[i/2]=t;
            }
        }
        System.out.println(Arrays.toString(arr).replace(',','\t'));

        int tar = (num1.length + num2.length) / 2 +1;
        int[] orderDesc = new int[tar];

        int size = arr.length-1;
        for(int i=1;i<=tar;i++){
//            System.out.printf("%d\n",arr[1]);
            orderDesc[i-1]=arr[1];
            arr[1]=arr[size];
            size--;
            int m =1;
            while(true){
                Integer left = m*2>size?null:m*2;
                Integer right = m*2+1>size?null:m*2+1;
                int max;
                if(right!=null){
                    max=arr[left]>arr[right]? left:right;
                } else if(left!=null){
                    max = left;
                } else  {
                    break;
                }
                if(arr[m]<arr[max]){
                    int tmp = arr[m];
                    arr[m]=arr[max];
                    arr[max]=tmp;

                    m=max;
                } else {
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(orderDesc).replace(',','\t'));
        if ((num1.length + num2.length) % 2 == 1) {
            return orderDesc[tar-1];
        } else{
            return (orderDesc[tar-1]+orderDesc[tar-2])/2.0;
        }
    }
}
