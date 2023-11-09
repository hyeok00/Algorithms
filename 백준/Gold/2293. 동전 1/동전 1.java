import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N, K;
	static int[] coin, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		K = stoi(splitedLine[1]);

		coin = new int[N];
		dp = new int[K + 1];
		for (int i = 0; i < N; ++i)
			coin[i] = stoi(in.readLine());

		dp[0] = 1; // 0원을 만들기 위해서는 아무동전도 안쓰는 방법 1개가 존재함.
		for (int i = 0; i < N; ++i) { 
			for (int j = coin[i]; j <= K; ++j) {
				dp[j] += dp[j - coin[i]];
			}
		}
		System.out.println(dp[K]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}