import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int T, LEFT, RIGHT, result;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String[] splitedLine = in.readLine().split(" ");
			LEFT = Integer.parseInt(splitedLine[0]);
			RIGHT = Integer.parseInt(splitedLine[1]);
			result = 0;
			dp = new int[RIGHT + 1][RIGHT + 1];

			sb.append(comb(RIGHT, LEFT) + "\n");
		}

		// 로직

		// 출력부
		System.out.println(sb);
	}

	private static int comb(int n, int r) {
		if (n == r || r == 0) {
			dp[n][r]=1;
			return 1;
		}

		if (dp[n][r] == 0) {
			return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
		}

		return dp[n][r];
		// TODO Auto-generated method stub
	}

}
