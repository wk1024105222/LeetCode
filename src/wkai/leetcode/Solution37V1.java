package wkai.leetcode;

import java.util.*;

/**
 * leetcode37 解数独 V1.0版本
 * 处理方案与人脑玩法一致
 * 1、先确认直接可以确定的点
 * 2、检查其关联点(横9个点 竖9个点 所在九宫格的点)
 * 3、逐个九宫格 摒除法再处理一轮
 * 测试结果：数独APP上 困难级别的可以解开、专家级解不开 提交测试6个测试通过2个
 */
public class Solution37V1 {
    public static void main(String[] args) {
        // 数独APP上 困难级别
        char[][] arr_app_hard = {
                {'.','2','.','4','.','9','1','.','.'},
                {'.','.','6','.','5','.','.','8','9'},
                {'.','7','.','.','8','3','.','2','4'},
                {'7','1','.','5','.','.','.','.','.'},
                {'.','.','.','.','9','.','2','.','.'},
                {'.','.','.','.','4','.','.','.','7'},
                {'.','6','.','.','.','.','.','.','.'},
                {'.','.','7','3','.','.','8','.','1'},
                {'3','4','.','.','.','5','.','6','.'}};
        char[][] arr_app_zhuanjia = {
                {'.', '4', '6', '9', '.', '3', '.', '.', '.'},
                {'.', '.', '3', '.', '5', '.', '.', '6', '.'},
                {'9', '.', '.', '.', '.', '2', '.', '.', '3'},
                {'.', '.', '5', '.', '.', '6', '.', '.', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '1', '.'},
                {'.', '1', '.', '7', '8', '.', '2', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '5', '.'},
                {'.', '8', '1', '3', '.', '.', '.', '.', '7'},
                {'.', '.', '.', '8', '.', '.', '1', '.', '4'}};

        char[][] arr_leetcode_3 = {
                {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        new Solution37V1().solveSudoku(arr_app_zhuanjia);
    }

    //nodeArray与arr数组大小一致 且一一对应
    //nodeArray[i][j]=Node 是arr[i][j]封装 保存了坐标、value、可能的值、关联点
    public Node[][] nodeArray = new Node[9][9];

    //存储已完成检查节点如果新增确认点无需再次检查
    public Node[][] doneArray = new Node[9][9];

    public void solveSudoku(char[][] arr) {
        Stack<Node> needCheck = new Stack<Node>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (doneArray[i][j] != null) {
                    continue;
                }
                Node curr;
                //只处理空的节点
                if (arr[i][j] == '.') {
                    //初始化nodeArray[i][j]
                    if ((curr = nodeArray[i][j]) == null) {
                        curr = new Node(i, j, arr[i][j]);
                        nodeArray[i][j] = curr;
                    }
                    //初始化当前节点 可能值列表以及关联点列表
                    curr.initMaybeListAndrelatedList(arr);
                    if (curr.maybeList.size() == 1) {
                        /*
                        curr的可能值只有一个 接下来
                        1、更新该节点的值
                        2、遍历关联节点列表curr1～n 初始化curr1～n的maybeList，并从maybeList移除curr的值 然后入栈
                        3、逐个Node出栈 若出栈节点maybeList.size==1 则有新的节点可以确定 重复第2步 继续将关联节点入栈 直到栈为空
                        到此由curr被确定引发的一系列处理 平息，1～3步骤处理的所有节点 如果仍有多种可能 则记录在doneArray
                        除非doneArray中节点的关联节点出现新的确认点，否则无需再次检查
                         */
                        checkOkNode(curr, arr, needCheck);
                    } else {
                        doneArray[i][j] = curr;
                        continue;
                    }
                }
            }
        }
        //遍历9个 九宫格 使用摒除法进一步处理
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                //将一个九宫格内 所有空节点的maybeList 累计起来 若某个值只出现一次 则新的确认点出现
                Map<Character, String[]> charMap = new HashMap<Character, String[]>();
                for (int p = 0; p < 3; p++) {
                    for (int q = 0; q < 3; q++) {
                        int line = m * 3 + p;
                        int col = n * 3 + q;
                        Node node = nodeArray[line][col];
                        //累计maybeList Map<可能值，[数量，行坐标-列坐标]>
                        for (Character c : node.maybeList) {
                            if (charMap.containsKey(c)) {
                                String[] aa = charMap.get(c);
                                int count = Integer.parseInt(aa[0]) + 1;
                                String locs = aa[1] + line + "-" + col;
                                String[] bb = {count + "", locs};
                                charMap.put(c, bb);
                            } else {
                                String[] bb = {"1", line + "-" + col};
                                charMap.put(c, bb);
                            }
                        }
                    }
                }
                Iterator eter = charMap.entrySet().iterator();
                //遍历MAP 检查只出现一次的值
                while (eter.hasNext()) {
                    Map.Entry e = (Map.Entry) eter.next();
                    Character key = (Character) e.getKey();
                    String[] value = (String[]) e.getValue();
                    int count = Integer.parseInt(value[0]);
                    String loc = value[1];
                    int tmp_1 = loc.charAt(0) - '0';
                    int tmp_c = loc.charAt(2) - '0';
                    Node tmp_n = nodeArray[tmp_1][tmp_c];
                    if (count == 1) {
                        tmp_n.maybeList.clear();
                        tmp_n.maybeList.add(key);
                        // 处理确认点
                        checkOkNode(tmp_n, arr, needCheck);
                    }
                }
            }
        }
        print(arr);
    }

    void checkOkNode(Node curr,char[][]arr,Stack<Node> needCheck){
        //将关联节点入栈
        pushRelatedListToStock(curr,arr,needCheck);
        while(!needCheck.isEmpty()) {
            //出栈
            Node stockUpNode = needCheck.pop();
            //检查是否为确定点
            if (stockUpNode.maybeList.size() == 1) {
                //继续将关联节点入栈
                pushRelatedListToStock(stockUpNode, arr, needCheck);
            } else {
                //对于仍存在多种可能的节点 记录在doneArray中
                doneArray[stockUpNode.lineNo][stockUpNode.colNo] = stockUpNode;
            }
        }
    }

    void pushRelatedListToStock(Node curr,char[][]arr,Stack<Node> needCheck) {
        int i = curr.lineNo;
        int j = curr.colNo;
        arr[i][j] = curr.maybeList.get(0);
        curr.value = curr.maybeList.get(0);
//        curr.maybeList.clear();
        for (Node tmp : curr.relatedList) {
            if (arr[tmp.lineNo][tmp.colNo] != '.') {
                continue;
            }
            tmp.initMaybeListAndrelatedList(arr);
//            tmp.maybeList.remove(curr.maybeList);
            needCheck.push(tmp);
            doneArray[tmp.lineNo][tmp.colNo] = null;
        }
    }

    void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%c\t", arr[i][j]);
            }
            System.out.printf("\n");
        }
    }

    class Node {
        public char value;
        public List<Character> maybeList = new ArrayList<Character>();
        public int lineNo;
        public int colNo;
        public List<Node> relatedList = new ArrayList<Node>();

        public Node(int lineNo, int colNo, char value) {
            this.value = value;
            this.lineNo = lineNo;
            this.colNo = colNo;
        }

        public void initMaybeListAndrelatedList(char[][] arr) {
            relatedList.clear();
            if (value == '.') {
                List<Character> impossibleList = new ArrayList<Character>();
                //遍历行、列关联点
                Node tmp;
                String nodeKey;
                for (int a = 0; a < 9; a++) {
                    char lv = arr[this.lineNo][a];
                    if (lv != '.') {
                        impossibleList.add(lv);
                    }
                    if (a != this.colNo) {
                        if (nodeArray[this.lineNo][a] != null) {
                            tmp = nodeArray[this.lineNo][a];
                        } else {
                            tmp = new Node(this.lineNo, a, lv);
                            nodeArray[this.lineNo][a] = tmp;
                        }

                        relatedList.add(tmp);
                    }
                    char cv = arr[a][this.colNo];
                    if (cv != '.') {
                        impossibleList.add(cv);
                    }
                    if (a != this.lineNo) {
                        if (nodeArray[a][this.colNo] != null) {
                            tmp = nodeArray[a][this.colNo];
                        } else {
                            tmp = new Node(a, this.colNo, cv);
                            nodeArray[a][this.colNo] = tmp;
                        }
                        relatedList.add(tmp);
                    }
                }
                int line_b = this.lineNo / 3 * 3;
                int line_e = line_b + 2;
                int col_b = this.colNo / 3 * 3;
                int col_e = col_b + 2;
                for (int m = line_b; m <= line_e; m++) {
                    for (int n = col_b; n <= col_e; n++) {
                        if (arr[m][n] != '.') {
                            impossibleList.add(arr[m][n]);
                        }
                        if (m != this.lineNo && n != this.colNo) {
                            if (nodeArray[m][n] != null) {
                                tmp = nodeArray[m][n];
                            } else {
                                tmp = new Node(m, n, arr[m][n]);
                                nodeArray[m][n] = tmp;
                            }
                            relatedList.add(tmp);
                        }
                    }
                }

                maybeList.clear();
                for (char a = '1'; a <= '9'; a++) {
                    maybeList.add(a);
                }
                maybeList.removeAll(impossibleList);
            }
        }
    }
}
