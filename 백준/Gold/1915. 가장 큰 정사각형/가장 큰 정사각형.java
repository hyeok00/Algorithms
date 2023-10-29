import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		int[][] map = new int[N + 1][M + 1];
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; ++i) {
			String str = in.readLine();
			for (int j = 1; j <= M; ++j) {
				map[i][j] = str.charAt(j - 1) - '0';
			}
		}

		int max = 0;
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				if (map[i][j] == 1) {
					if (dp[i - 1][j - 1] > 0 && dp[i - 1][j] > 0 && dp[i][j - 1] > 0) {
						dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
					} else {
						dp[i][j] = 1;
					}
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		System.out.println(max*max);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}