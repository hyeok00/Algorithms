import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			int N = stoi(in.readLine());
			int[][] arr = new int[N + 1][2];
			for (int i = 0; i < 2; ++i) {
				String[] splitedLine = in.readLine().split(" ");
				for (int j = 0; j < N; ++j)
					arr[j + 1][i] = stoi(splitedLine[j]);
			}

			int[][] dp = new int[N + 1][2];
			dp[1][0] = arr[1][0];
			dp[1][1] = arr[1][1];
			for (int i = 2; i <= N; ++i) {
				dp[i][0] = Math.max(dp[i - 1][1], Math.max(dp[i - 2][0],dp[i - 2][1])) + arr[i][0];
				dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 2][0],dp[i - 2][1])) + arr[i][1];
			}
			sb.append(Math.max(dp[N][0], dp[N][1])).append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}