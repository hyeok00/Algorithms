import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		inputs = in.readLine().split(" ");
		arr = new int[M];
		for (int i = 0; i < M; ++i)
			arr[i] = stoi(inputs[i]);

		if (N <= M) {
			System.out.println(N);
			return;
		}

		long left = 0;
		long right = 60000000000L;
		while (left <= right) {
			long mid = (left + right) / 2;

			long count = M;
			for (int time : arr)
				count += mid / time;

			if (count >= N)
				right = mid - 1;
			else
				left = mid + 1;
		}

		int complete = M;
		for (int time : arr)
			complete += (left - 1) / time;

		int idx = -1;
		while (complete < N) {
			idx++;
			if (left % arr[idx] == 0)
				complete++;
		}
		System.out.println(idx + 1);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}