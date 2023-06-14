import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M, max;
	static int[][] map, dp;
	static int[] dx = {0, 0, -1}; // 좌우상
	static int[] dy = {-1, 1, 0};

	static String[] splitedLine;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				dp[i][j] = Integer.MIN_VALUE;
			}
		}

		max = Integer.MIN_VALUE;
		// 첫줄
		dp[0][0] = map[0][0];
		for (int i = 1; i < M; ++i) {
			dp[0][i] = dp[0][i - 1] + map[0][i];
		}
		// 중간줄
		for (int i = 1; i < N - 1; ++i) {
			int[] leftSearch = new int[M];
			int[] rightSearch = new int[M];
			// 좌에서 우
			leftSearch[0] = dp[i - 1][0] + map[i][0];
			for (int j = 1; j < M; ++j) {
				leftSearch[j] = Math.max(leftSearch[j - 1], dp[i - 1][j]) + map[i][j];
			}
			rightSearch[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
			for (int j = M - 2; j >= 0; --j) {
				rightSearch[j] = Math.max(rightSearch[j + 1], dp[i - 1][j]) + map[i][j];
			}
			for (int j = 0; j < M; ++j) {
				dp[i][j] = Math.max(leftSearch[j], rightSearch[j]);
			}
			// 우에서 좌
		}
		// 마지막줄
		if (N > 1) {
			dp[N - 1][0] = dp[N - 2][0] + map[N - 1][0];
			for (int i = 1; i < M; ++i) {
				dp[N - 1][i] = Math.max(dp[N - 1][i - 1], dp[N - 2][i]) + map[N - 1][i];
			}
		}
		
		System.out.println(dp[N-1][M-1]);
	}

}