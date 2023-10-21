import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Main {

	static int N, budget;
	static int[] arr;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		arr = new int[N];
		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			arr[i] = stoi(splitedLine[i]);
		Arrays.sort(arr);
		budget = stoi(in.readLine());

		System.out.println(getMaximumBudget());
	}

	private static int getMaximumBudget() {
		int result = 0;

		int left = 0;
		int right = arr[N - 1];

		while (left <= right) {
			int middle = (left + right) / 2;

			int sum = 0;
			for (int i = 0; i < N; ++i) {
				sum += Math.min(arr[i], middle);
			}

			if (sum <= budget) {
				result = middle;
				left = middle + 1;
			} else
				right = middle - 1;

		}
		return result;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}