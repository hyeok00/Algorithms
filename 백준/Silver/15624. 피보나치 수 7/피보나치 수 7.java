import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	final static double NUM = 1000000007;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		double N = Double.parseDouble(in.readLine());
		double[] dp = new double[1000001];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= N; ++i) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % NUM;
		}
		int result = (int) dp[(int) N];
		sb.append(result);
		System.out.println(sb);
	}
}