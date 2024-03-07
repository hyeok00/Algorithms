import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		arr = new int[100001];
		dp = new int[100001];
		String[] inputs = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i+1] = stoi(inputs[i]);
		}

		int end = 0;
		dp[0] = arr[0];

		for (int i = 1; i < N + 1; ++i) {
			if (dp[end] < arr[i]) {
				dp[end + 1] = arr[i];
				end += 1;
			} else {
				int idx = search(0, end, arr[i]);
				dp[idx] = arr[i];
			}
		}
		System.out.println(N - end);
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