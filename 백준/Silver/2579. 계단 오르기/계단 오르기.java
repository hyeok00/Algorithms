import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(in.readLine());
		int[] arr = new int[N + 1];

		for (int i = 1; i <= N; ++i)
			arr[i] = stoi(in.readLine());

		int[] dp = new int[N + 1];

		if (N >= 1)
			dp[1] = arr[1];
		if (N >= 2)
			dp[2] = arr[1] + arr[2];

		for (int i = 3; i <= N; ++i) {
			int oneStep = dp[i - 3] + arr[i - 1] + arr[i];
			dp[i] = Math.max(dp[i], oneStep);

			int twoStep = dp[i - 2] + arr[i];
			dp[i] = Math.max(dp[i], twoStep);
		}
		System.out.println(dp[N]);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}