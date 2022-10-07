import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int[][] map, dp;
	static boolean[][] isVisit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, result;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);
		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				dp[i][j] = -1;
			}
		}

		dp[0][0] = 1;
		result = search(N - 1, M - 1);
		sb.append(result);
		System.out.println(sb);
	}

	private static int search(int startX, int startY) {
		if (startX == 0 && startY == 0) {
			return dp[0][0];
		}
		if (dp[startX][startY] >= 0) {
			return dp[startX][startY];
		}

		dp[startX][startY] = 0;
		for (int i = 0; i < 4; ++i) {
			int nextX = startX + dx[i];
			int nextY = startY + dy[i];
			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
				if (map[nextX][nextY] > map[startX][startY]) {
					dp[startX][startY] += search(nextX, nextY);
				}
			}
		}

		return dp[startX][startY];
	}

	private static int stoi(String string) {
		return Integer.parseInt(string);
	}
}