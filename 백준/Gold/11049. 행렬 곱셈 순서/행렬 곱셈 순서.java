import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Matrix {
		int r;
		int c;

		Matrix(int a, int b) {
			r = a;
			c = b;
		}
	}

	static int N;
	static Matrix[] arr;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		arr = new Matrix[N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			arr[i + 1] = new Matrix(stoi(inputs[0]), stoi(inputs[1]));
		}

		int result = search(1, N);
		System.out.println(result);
	}

	private static int search(int left, int right) {
		if (left == right)
			return 0;

		if (dp[left][right] != 0)
			return dp[left][right];

		dp[left][right] = Integer.MAX_VALUE;
		for (int mid = left; mid < right; ++mid) {
			int leftCount = search(left, mid);
			int rightCount = search(mid + 1, right);
			int sum = leftCount + rightCount + arr[left].r * arr[mid].c * arr[right].c;
			dp[left][right] = Math.min(dp[left][right], sum);
		}
		return dp[left][right];
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}