package wkai.leetcode;

import java.util.*;

/**
 * leetcode37 解数独 V2.0版本
 * V1.0摒除 只处理了九宫格部分
 * V2.0摒除增加了 横9 竖9
 * 测试结果：数独APP上 困难级别的可以解开、专家级比V1.0多解出3个点 提交测试与V1.0一致 6过2
 */
public class Solution37V2 {
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
		new Solution37V2().solveSudoku(arr_leetcode_3);
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
				// 如果该节点处于完成检查列表 在有新节点被确认前无需再次检查
				if (doneArray[i][j] != null) {
					continue;
				}
				Node curr;
				// 仅仅处理空节点
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

		//横9 竖9 九宫格 做3次摒除法
		for (int f_no = 0; f_no < 9; f_no++) {
			// 第i行
			List<Node> nodeList = new ArrayList<Node>();
			for (int s_no = 0; s_no < 9; s_no++) {
				nodeList.add(nodeArray[f_no][s_no]);
			}
			bingqiHandle(nodeList, arr, needCheck);
			nodeList.clear();
			// 第i列
			for (int s_no = 0; s_no < 9; s_no++) {
				nodeList.add(nodeArray[s_no][f_no]);
			}
			bingqiHandle(nodeList, arr, needCheck);
			nodeList.clear();
			// 第i个九宫格
			int m = f_no / 3;
			int n = f_no % 3;
			for (int p = 0; p < 3; p++) {
				for (int q = 0; q < 3; q++) {
					int line = m * 3 + p;
					int col = n * 3 + q;
					nodeList.add(nodeArray[line][col]);
				}
			}
			bingqiHandle(nodeList, arr, needCheck);
		}
		print(arr);
	}

	/**
	 * 使用摒除法 查找横9 或竖9 或九宫格 范围可以确认的点
	 *
	 * @param list
	 */
	public void bingqiHandle(List<Node> list, char[][]arr, Stack<Node> needCheck) {
		Map<Character, CountModel> charMap = new HashMap<Character, CountModel>();
		for (Node node : list) {
			int line = node.lineNo;
			int col = node.colNo;
			for (Character c : node.maybeList) {
				CountModel model;
				if (charMap.containsKey(c)) {
					model = charMap.get(c);
					model.count++;
					model.locs.add(new int[] {line, col});

				} else {
					model = new CountModel();
					model.count = 1;
					model.locs.add(new int[] {line, col});
				}
				charMap.put(c, model);
			}
		}
		Iterator eter = charMap.entrySet().iterator();
		while (eter.hasNext()) {
			Map.Entry e = (Map.Entry) eter.next();
			Character key = (Character) e.getKey();
			CountModel model = (CountModel) e.getValue();

			if (model.count == 1) {
				Node tmp_n = nodeArray[model.locs.get(0)[0]][model.locs.get(0)[1]];
				tmp_n.maybeList.clear();
				tmp_n.maybeList.add(key);
				checkOkNode(tmp_n, arr, needCheck);
			}
		}
	}

	class CountModel {
		public int count;
		public List<int[]> locs = new ArrayList<int[]>();
	}

	void checkOkNode(Node curr, char[][]arr, Stack<Node> needCheck){
		//将关联节点入栈
		pushRelatedListToStock(curr,arr,needCheck);
		while(!needCheck.isEmpty()) {
			//出栈
			Node stockUpNode = needCheck.pop();
			//检查是否为确定点
			if (stockUpNode.maybeList.size() == 1) {
				pushRelatedListToStock(stockUpNode, arr, needCheck);
			} else {
				//对于仍存在多种可能的节点 记录在doneArray中
				doneArray[stockUpNode.lineNo][stockUpNode.colNo] = stockUpNode;
			}
		}
	}

	void pushRelatedListToStock(Node curr, char[][]arr, Stack<Node> needCheck) {
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


