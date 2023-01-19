import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, K, result;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		K = Integer.parseInt(splitedLine[1]);

		arr = new int[N];
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		result = 0;
		search(0, arr[0], 1, 1);
		search(0, 0, 1, 0);
		System.out.println(result);
	}

	public static void search(int idx, int sum, int depth, int selectCount) {
		if (depth == N) {
			if (sum == K && selectCount != 0)
				result++;
		} else {
			search(idx + 1, arr[idx + 1] + sum, depth + 1, selectCount + 1);
			search(idx + 1, sum, depth + 1, selectCount);
		}
	}
}