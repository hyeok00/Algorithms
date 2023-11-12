import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		dp = new int[31];
		dp[0] = 0;
		dp[2] = 3;
		dp[4] = 11;

		for (int i = 6; i <= N; i+=2)
			dp[i] = dp[i - 2] * 4 - dp[i - 4];
		System.out.println(dp[N]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}