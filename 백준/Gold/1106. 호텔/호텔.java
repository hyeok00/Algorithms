import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, C;
	static String[] splitedLine;
	static int[] dp;
	final static int MAX = 101;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		splitedLine = in.readLine().split(" ");
		C = Integer.parseInt(splitedLine[0]);
		N = Integer.parseInt(splitedLine[1]);

		dp = new int[C + MAX];
		for (int i = 0; i < C+MAX; ++i)
			dp[i] = Integer.MAX_VALUE;
		dp[0] = 0;

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			int cost = Integer.parseInt(splitedLine[0]);
			int people = Integer.parseInt(splitedLine[1]);
			for (int j = people; j < C + MAX; j++) {
				int temp = dp[j-people];
				if(temp != Integer.MAX_VALUE)
					dp[j] = Math.min(dp[j], cost + temp);
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = C; i < C + MAX; ++i) {
			result = Math.min(result, dp[i]);
		}
		System.out.println(result);
	}
}