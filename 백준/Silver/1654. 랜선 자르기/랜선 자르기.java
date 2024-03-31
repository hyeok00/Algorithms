import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int K, N;
	static long[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		K = stoi(inputs[0]);
		N = stoi(inputs[1]);

		arr = new long[K];
		for (int i = 0; i < K; ++i)
			arr[i] = stoi(in.readLine());

		long left = 0;
		long right = Integer.MAX_VALUE;

		while (right >= left) {
			long mid = (left + right) / 2;

			long count = 0;
			for (long value : arr)
				count += value / mid;

			if (count >= N)
				left = mid + 1;
			else
				right = mid - 1;
		}
		System.out.println(left - 1);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}