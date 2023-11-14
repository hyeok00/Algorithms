import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, K;
	static int[][] dp;

	final static int DIVIDER = 1000000003;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		K = stoi(in.readLine());
		dp = new int[N + 1][N + 1];

		for (int i = 0; i <= N; ++i) {
			dp[i][1] = i;
			dp[i][0] = 1;
		}
		for (int i = 4; i <= N; ++i) {
			for (int j = 1; j <= K && j<=i/2; ++j) {
				dp[i][j] = (dp[i - 2][j - 1] % DIVIDER + dp[i - 1][j] % DIVIDER) % DIVIDER;
			}
		}
		System.out.println(dp[N][K]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}