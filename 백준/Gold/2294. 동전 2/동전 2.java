import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static int N, K;
	static List<Integer> coins = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		K = stoi(inputs[1]);

		int[] dp = new int[100001];
		Arrays.fill(dp, 100001);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			int value = stoi(in.readLine());
			coins.add(value);
			dp[value] = 1;
			min = min < value ? min : value;
		}

		for (int i = min; i <= K; ++i) {
			for (int j = 0; j < N; ++j) {
				if (i - coins.get(j) < 0)
					continue;
				dp[i] = Math.min(dp[i], dp[i - coins.get(j)] + 1);
			}
		}
		System.out.println(dp[K] == 100001 ? -1 : dp[K]);

	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}