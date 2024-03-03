import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] arr;
	final static int SIZE = 5;

	static int[] select;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i)
			arr[i] = in.readLine().toCharArray();

		select = new int[7];
		result = 0;
		permutation(0, 0);
		System.out.println(result);
	}

	private static void permutation(int start, int depth) {
		if (depth == 7) {
			// 조건에 부합하는 경우
			if (check())
				result++;
			return;
		}

		for (int i = start; i < 25; ++i) {
			select[depth] = i;
			permutation(i + 1, depth + 1);
		}
	}

	private static boolean check() {
		int[][] checkMap = new int[5][5];
		// 조건 4.
		int count = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < 7; ++i) {
			x = select[i] / 5;
			y = select[i] % 5;
			if (arr[x][y] == 'S')
				count++;
			checkMap[x][y] = 1;
		}

		// 조건4 불충족
		if (count < 4)
			return false;

		// 조건2 검사.
		int adjCount = 1;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(select[6]);
		checkMap[x][y] = 2;
		while (!q.isEmpty()) {
			int cur = q.poll();
			x = cur / 5;
			y = cur % 5;

			for (int d = 0; d < 4; ++d) {
				int nextX = x + dx[d];
				int nextY = y + dy[d];
				if (isInRange(nextX, nextY) && checkMap[nextX][nextY] == 1) {
					adjCount++;
					checkMap[nextX][nextY] = 2;
					q.add(nextX * 5 + nextY);
				}
			}
		}

		// 조건2 불충족.
		if (adjCount != 7)
			return false;

		return true;
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (0 <= nextX && nextX < SIZE && 0 <= nextY && nextY < SIZE)
			return true;
		return false;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}