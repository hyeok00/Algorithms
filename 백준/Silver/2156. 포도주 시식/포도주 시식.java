import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = stoi(in.readLine());

		int[] arr = new int[10001];
		int[] dp = new int[10001];

		for (int i = 1; i <= N; ++i)
			arr[i] = stoi(in.readLine());

		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];

		for (int i = 3; i <= N; ++i) {
			dp[i] = Math.max(Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i], dp[i - 1]);
		}
		System.out.println(dp[N]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}