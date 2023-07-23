import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(in.readLine());

			int[] arr = new int[N];
			String[] splitedLine = in.readLine().split(" ");
			for (int i = 0; i < N; ++i) {
				arr[i] = Integer.parseInt(splitedLine[i]);
			}
			int target = Integer.parseInt(in.readLine());

			int[] dp = new int[200001];

			dp[0] = 1;
			for (int i = 0; i < N; ++i) {
				int cur = arr[i];
				for (int j = cur; j <= target; ++j) {
					dp[j] += dp[j - cur];
				}
			}
			sb.append(dp[target]).append("\n");
		}
		System.out.println(sb);
	}
}