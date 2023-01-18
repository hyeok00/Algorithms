import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];

		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		dp[0] = arr[0];
		int sum = arr[0];

		for (int i = 1; i < N; ++i) {
			dp[i] = dp[i-1]+arr[i]>arr[i]?dp[i-1]+arr[i]:arr[i];
			sum = sum>dp[i]?sum:dp[i];
		}
		System.out.println(sum);
	}
}