import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String first = in.readLine();
		String second = in.readLine();
		int length1 = first.length();
		int length2 = second.length();

		int[][] dp = new int[length1 + 1][length2 + 1];
		for (int i = 1; i <= length1; ++i) {
			for (int j = 1; j <= length2; ++j) {
				if (first.charAt(i - 1) == second.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		sb.append(dp[length1][length2]);

		int i = length1;
		int j = length2;
		StringBuilder st = new StringBuilder();
		while (true) {
			if (dp[i][j] == 0)
				break;
			if (dp[i - 1][j] == dp[i][j]) {
				i = i - 1;
			} else if (dp[i][j - 1] == dp[i][j]) {
				j = j - 1;
			} else {
				i = i - 1;
				j = j - 1;
				st.append(first.charAt(i));
			}
		}
		System.out.println(sb);
		System.out.println(st.reverse());
	}
}