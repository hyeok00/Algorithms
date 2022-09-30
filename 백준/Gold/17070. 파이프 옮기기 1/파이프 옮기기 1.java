import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	static int N, result;
	static int[][] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		// 로직
		search(0, 1, 'R');
		// 출력부

		sb.append(result);
		System.out.println(sb);
	}

	private static void search(int x, int y, char c) {
		if (x == N - 1 && y == N - 1) {
			result++;
			return;
		}
		int nextX = x + 1;
		int nextY = y + 1;
		switch (c) {
		case 'R': // 우측
			if (nextY < N && arr[x][nextY] != 1) {
				search(x, nextY, 'R');
			}
			break;
		case 'B': // 아래
			if (nextX < N && arr[nextX][y] != 1) {
				search(nextX, y, 'B');
			}
			break;

		case 'D': // 대각
			if (nextY < N && arr[x][nextY] != 1) {
				search(x, nextY, 'R');
			}
			if (nextX < N && arr[nextX][y] != 1) {
				search(nextX, y, 'B');
			}
			break;
		}
		if (nextX < N && nextY < N && arr[nextX][nextY] != 1 && arr[nextX][y] != 1 && arr[x][nextY] != 1) {
			search(nextX, nextY, 'D');
		}
	}
}