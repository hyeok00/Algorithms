import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		splitedLine = in.readLine().split(" ");
		for (int j = 0; j < N; ++j) {
			arr[j] = Integer.parseInt(splitedLine[j]);
		}

		int[] dp = new int[N];
		int max = 1;

		for (int i = 0; i < N; ++i) {
			dp[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (arr[j] < arr[i]) {
					dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1;
				}
			}
			max = max > dp[i] ? max : dp[i];
		}
		sb.append(max);

		System.out.println(sb);
	}
}
