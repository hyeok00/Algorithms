import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());

		int[][] dp = new int[101][10];
		for (int i = 1; i < 10; ++i)
			dp[1][i] = 1;

		for (int i = 2; i <= N; ++i) {
			dp[i][0] = dp[i-1][1];
			dp[i][9] = dp[i-1][8];
			for (int j = 1; j <= 8; ++j)
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
		}
		int sum = 0;
		for (int i = 0; i <= 9; ++i){
			sum += dp[N][i];
			sum = sum % 1000000000;
		}
		System.out.println(sum);
}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}