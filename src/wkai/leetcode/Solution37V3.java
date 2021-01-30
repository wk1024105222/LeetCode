package wkai.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * leetcode37 解数独 V3.0版本
 * 采用回溯算法 按顺序 给每个节点从mayBeList找一个合适的值
 * 如果某个节点找不到合适的值，无路可走时 则退一步 上个节点换个值
 * 还不行再退一步 直到更换第一个点的值 重新走
 * 测试结果：数独APP上 困难级别的可以解开、专家级可以解开 提交测试通过 成绩很差
 * 执行用时：54 ms, 在所有 Java 提交中击败了5.00%的用户
 * 内存消耗：39 MB, 在所有 Java 提交中击败了5.11%的用户
 */
class Solution37V3 {
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
		new Solution37V3().solveSudoku(arr_leetcode_3);
	}

	//每个节点可能的值列表
	Map<Integer, LinkedList<Character>> mayBeList = new HashMap<Integer, LinkedList<Character>>();
	//回溯算法中的路径 存储找到合适值的空节点 如果某个节点mayBeList都没有合适的值 则逐个出栈回溯
	Stack<Integer> track = new Stack<Integer>();
	//空节点列表 每次取链表第一个 有合适的值 则入栈然后处理下一个 否则再放回链表第一个 出栈获取上一个找到合适值的空节点 从剩余mayBeList换个值尝试
	LinkedList<Integer> kongPoint = new LinkedList<Integer>();
	int step = 0;

	public void solveSudoku(char[][] arr) {
		//给所有的空节点 初始化mayBeList 都是1-9的数组
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (arr[i][j] == '.') {
					kongPoint.add(i * 9 + j);
					LinkedList<Character> l = new LinkedList<Character>();
					for (char t = '1'; t <= '9'; t++) {
						l.add(t);
					}
					mayBeList.put(i * 9 + j, l);
				}
			}
		}

//		System.out.println("kongpoint" + mayBeList.size());
		//遍历 kongPoint 程序主驱动
		while (!kongPoint.isEmpty()) {
			// 获取排在队头的点
			int a = kongPoint.removeFirst();
			//检查节点mayBeList中是否有合适的值
			if (handle(a, arr)) {
				//有则入栈 继续处理下一个空节点
				step++;
				track.push(a);
			} else {
//				System.out.printf("死路(%d,%d,%c) stocksize:%d\n", a / 9, a % 9, a, track.size());
				//没有 则塞回kongPoint 复原mayBeList
				kongPoint.addFirst(a);
				for (char t = '1'; t <= '9'; t++) {
					mayBeList.get(a).add(t);
				}
				//出栈 拿到上一个找到合适值的空节点
				a = track.pop();
				// 塞回kongPoint
				kongPoint.addFirst(a);
//				System.out.printf("后退一步(%d,%d,%c)stocksize:d\n", a / 9, a % 9, a, track.size());
				//取消原来找到的合适值
				arr[a / 9][a % 9] = '.';
			}
		}
		print(arr);
	}

	/**
	 * 针对一个位置的点 遍历mayBeList 检查是否存在符合数独要求的值
	 * @param num 当前处理点的位置
	 * @param arr
	 * @return true mayBeList有合适的值 false mayBeList没有合适的值
	 */
	private boolean handle(int num, char[][] arr) {
		int i = num / 9;
		int j = num % 9;
		while (!mayBeList.get(num).isEmpty()) {
			char a = mayBeList.get(num).removeFirst();
			if (isOkNow(i, j, a, arr)) {
				arr[i][j] = a;
//				System.out.printf("前进一步(%d,%d,%c) stocksize:%d\n", i, j, a, track.size());
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查一个节点的值是否符合数组要求 与横9 竖9 九宫格里数据不重复
	 * @param i
	 * @param j
	 * @param a
	 * @param arr
	 * @return
	 */
	private boolean isOkNow(int i, int j, char a, char[][] arr) {
		int line = i;
		for (int c = 0; c < 9; c++) {
			if (c != j && arr[line][c] == a) {
				return false;
			}
		}
		int col = j;
		for (int l = 0; l < 9; l++) {
			if (l != i && arr[l][col] == a) {
				return false;
			}
		}
		for (int p = i / 3 * 3; p <= i / 3 * 3 + 2; p++) {
			for (int q = j / 3 * 3; q <= j / 3 * 3 + 2; q++) {
				if (!(p == i && q == j) && arr[p][q] == a) {
					return false;
				}

			}
		}
		return true;
	}

	void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.printf("%c\t", arr[i][j]);
			}
			System.out.printf("\n");
		}
	}
}
