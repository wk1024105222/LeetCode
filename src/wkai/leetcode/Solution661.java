package wkai.leetcode;

/**
 * leetcode661. 图片平滑器
 * 测试结果：通过
 * 执行用时：7 ms, 在所有 Java 提交中击败了79.82%的用户
 * 内存消耗：39.5 MB, 在所有 Java 提交中击败了42.30%的用户
 */
public class Solution661 {
    public static void main(String[] args) {
        System.out.println(new Solution661().imageSmoother(new int[][]{{2,3,4},{5,6,7},{8,9,10},{11,12,13},{14,15,16}}));
    }

    public int[][] imageSmoother(int[][] M) {
        int lineNum = M.length;
        int colNum = M[0].length;
        int[][] rlt = new int[lineNum][colNum];
        for(int i=0;i<lineNum;i++){
            for(int j=0;j<colNum;j++){
                int sum =M[i][j],num=1;
                if(i-1>=0){
                    sum+=M[i-1][j];
                    num++;
                }
                if(i+1<lineNum){
                   sum+= M[i+1][j];
                   num++;
                }
                if(j-1>=0){
                    sum+= M[i][j-1];
                    num++;
                }
                if(j+1<colNum){
                    sum+= M[i][j+1];
                    num++;
                }
                if(i-1>=0&&j-1>=0){
                    sum+=M[i-1][j-1];
                    num++;
                }
                if(i-1>=0&&j+1<colNum){
                    sum+=M[i-1][j+1];
                    num++;
                }
                if(j-1>=0&&i+1<lineNum){
                    sum+=M[i+1][j-1];
                    num++;
                }
                if(j+1<colNum&&i+1<lineNum){
                    sum+=M[i+1][j+1];
                    num++;
                }
                rlt[i][j]=sum/num;
            }
        }
        return rlt;
    }
}
