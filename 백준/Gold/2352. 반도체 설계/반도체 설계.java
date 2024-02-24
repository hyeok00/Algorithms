import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		String[] inputs = in.readLine().split(" ");
		arr = new int[N];
		dp = new int[N];
		for (int i = 0; i < N; ++i)
			arr[i] = stoi(inputs[i]);

		int end = 0;
		dp[0] = arr[0];

		for (int i = 1; i < N; ++i) {
			if (dp[end] < arr[i]) {
				dp[end + 1] = arr[i];
				end++;
			} else {
				int idx = search(0, end, arr[i]);
				dp[idx] = arr[i];
			}
		}
		System.out.println(end + 1);
	}

	static int search(int left, int right, int target) {
		while (left < right) {
			int mid = (left + right) / 2;

			if (dp[mid] < target)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}