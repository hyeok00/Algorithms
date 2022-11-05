import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int arr[][] = new int[N][3];
		int dp[][] = new int[N][3];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < 3; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (i == j)
					dp[0][j] = arr[0][j];
				else
					dp[0][j] = 1000 * 1000 + 1;
			}
			for (int j = 1; j < N; ++j) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
				dp[j][2] = Math.min(dp[j - 1][1], dp[j - 1][0]) + arr[j][2];
			}
			for (int j = 0; j < 3; ++j) {
				if (i == j)
					continue;
				else
					result = Math.min(result, dp[N-1][j]);
			}
		}
		sb.append(result);
		System.out.println(sb);
	}
}