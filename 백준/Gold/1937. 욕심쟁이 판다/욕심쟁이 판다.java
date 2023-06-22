import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[][] dp, arr, visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		arr = new int[N][N];
		visit = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
				dp[i][j] = -1;
			}
		}

		int max = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				visit[i][j] = 1;
				int value = dfs(i, j)+1;
				visit[i][j] = 0;
				max = max > value ? max : value;
			}
		}
		System.out.println(max);
	}

	private static int dfs(int x, int y) {
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		boolean flag = false;
		for (int i = 0; i < 4; ++i) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (isInRange(nextX, nextY) && arr[x][y] > arr[nextX][nextY]) {
				visit[nextX][nextY] = visit[x][y] + 1;
				dp[x][y] = Math.max(dp[x][y], dfs(nextX, nextY)+1);
				visit[nextX][nextY] = 0;
				flag = true;
			}
		}

		if (flag) {
			return dp[x][y];
		}

		return 0;
	}

	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		else
			return false;
	}
}

/*
2 0 3 2
0 3 2 0
3 4 0 2
2 0 2 0
 */