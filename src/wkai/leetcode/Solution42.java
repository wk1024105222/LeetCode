package wkai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode42. 接雨水
 */
public class Solution42 {
    public static void main(String[] args) {
        int[] test1 = new int[]{8,8,1,5,6,2,5,3,3,9};
        System.out.println(new Solution42().trap_v2(test1));
    }

    /**
    * 执行用时： 65 ms , 在所有 Java 提交中击败了 15% 的用户
    * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 0% 的用户
    * 先将柱子排序 然后取相邻2个立柱 取小的 计算在原坐标轴上蓄水容积
    * @param heights
    * @return
    */
    public int trap_v2(int[] heights) {
        List<Node> nodes = new ArrayList<Node>(heights.length);
        for(int i=0;i<heights.length;i++){
            nodes.add(new Node(i,heights[i]));
        }
        Collections.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n2.hight-n1.hight;
            }});
        int sum = 0;
        for(int j=0;j<nodes.size()-1;j++){
            Node high = nodes.get(j);
            Node low = nodes.get(j+1);
            int begin = Math.min(high.index,low.index);
            int end = Math.max(high.index,low.index);
            for(int m=begin+1;m<end;m++){
                sum += low.hight > heights[m] ? low.hight - heights[m] : 0;
                heights[m]=low.hight;

            }
        }
        System.out.println(sum);
        return sum;
    }

    class Node{
        public int index ;
        public int hight;

        public Node(int index, int hight) {
            this.index = index;
            this.hight = hight;
        }
    }

    /**
     * 执行用时： 132 ms
     * 内存消耗： 40.5 MB
     *
     * @param height
     * @return
     */
    public int trap_v1(int[] height) {
        int[] deps = height;
        List<int[]> ranges = new ArrayList<int[]>();
        //遍历数组以每个节点做base向在、向右找到分别找到递培最大点
        for (int i = 1; i < deps.length - 1; ) {
            int left = i - 1;
            int right = i + 1;
            boolean left_stop = false;
            boolean right_stop = false;
            //向左移动找到递增的极限 走到左侧边界或开始下降 则停止
            while (left >= 0 && !left_stop) {
                if (left == 0) {
                    if (deps[left] < deps[left + 1]) {
                        left_stop = true;
                        left++;
                    }
                    break;
                }
                if (deps[left] < deps[left + 1]) {
                    left_stop = true;
                    left++;
                } else {
                    if (left >= 1) {
                        left--;
                    }
                }
            }
            //向右移动找到递增的极限 走到右侧边界或开始下降 则停止
            while (right < deps.length && !right_stop) {
                if (right == deps.length - 1) {
                    if (deps[right] < deps[right - 1]) {
                        right_stop = true;
                        right--;
                    }
                    break;
                }
                if (deps[right] < deps[right - 1]) {
                    right_stop = true;
                    right--;
                } else {
                    if (right + 1 < deps.length) {
                        right++;
                    }
                }
            }
            //base最低 left right高于base构成水洼
            if (left < right && left < i && right > i) {
                System.out.printf("tar:%d left:%d right:%d\n", i, left, right);
                ranges.add(new int[]{left, right});
                i = right + 1;
            } else {
                i++;
            }
        }
        int sum = 0;

        boolean isMerge;
        do {

            isMerge = false;
            if (ranges.size() == 0) {
                sum = 0;
                return sum;
            } else if (ranges.size() == 1) {
                sum = this.count(ranges.get(0)[0], ranges.get(0)[1], deps);
                System.out.printf("merge: left:%d_%d right:%d_%d\n",  ranges.get(0)[0],deps[ranges.get(0)[0]],
                        ranges.get(0)[1],deps[ranges.get(0)[1]]);
                return sum;
            } else {
                for (int i = 0; i < ranges.size() - 1; i++) {
                    int[] before = ranges.get(i);
                    int[] after = ranges.get(i + 1);
                    if (before[1] >= after[0] && deps[before[0]] >= deps[before[1]] && deps[after[1]] >= deps[before[1]]) {
                        ranges.set(i, new int[]{before[0], after[1]});
                        ranges.remove(i + 1);
                        isMerge = true;
                    }
                }
            }
        } while (isMerge);

        for (int m = 0; m < ranges.size(); m++) {
            sum+=this.count(ranges.get(m)[0], ranges.get(m)[1], deps);
            System.out.printf("merge: left:%d_%d right:%d_%d\n",  ranges.get(m)[0],deps[ranges.get(m)[0]],
                    ranges.get(m)[1],deps[ranges.get(m)[1]]);
        }
        System.out.println("aunl:" + sum);
        return sum;
    }

    private int count(int left, int right, int[] arry) {
        int max = Math.min(arry[left], arry[right]);
        int sum = 0;
        for (int i = left; i <=right; i++) {
            sum += max > arry[i] ? max - arry[i] : 0;
        }
        return sum;
    }
}
