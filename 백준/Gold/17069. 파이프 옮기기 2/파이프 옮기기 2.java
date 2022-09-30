import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	static int N, result;
	static int[][] arr;
	static long[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		dp = new long[N][N][3];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		dp[0][1][0] = 1;
		// 로직

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == 0 && j == 0)
					continue;
				if (arr[i][j] == 1)
					continue;
				if (j + 1 < N && arr[i][j + 1] == 0) {
					dp[i][j + 1][0] = dp[i][j][0] + dp[i][j][2];
				}
				if (i + 1 < N && arr[i + 1][j] == 0) {
					dp[i + 1][j][1] = dp[i][j][1] + dp[i][j][2];
				}
				if (j + 1 < N && i + 1 < N && arr[i + 1][j + 1] == 0 && arr[i][j + 1] == 0 && arr[i + 1][j] == 0) {
					dp[i + 1][j + 1][2] = dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
				}

			}
		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}