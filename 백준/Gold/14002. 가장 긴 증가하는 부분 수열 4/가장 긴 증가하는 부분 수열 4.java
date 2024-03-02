import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static int[] arr, dp, indexArr;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		arr = new int[N];
		dp = new int[N];
		indexArr = new int[N];
		String[] inputs = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			arr[i] = stoi(inputs[i]);

		int end = 0;
		dp[0] = arr[0];
		indexArr[0] = 1;
		for (int i = 1; i < N; ++i) {
			if (dp[end] < arr[i]) {
				dp[end + 1] = arr[i];
				end += 1;
				indexArr[i] = end + 1;
			} else {
				int idx = search(0, end, arr[i]);
				dp[idx] = arr[i];
				indexArr[i] = idx + 1;
			}
		}
		int size = end + 1;

		StringBuilder sb = new StringBuilder();
		sb.append(size).append("\n");
		List<Integer> list = new ArrayList<>();
		for (int i = N - 1; i >= 0; --i) {
			if (indexArr[i] == size) {
				list.add(arr[i]);
				--size;
			}
		}

		for (int i = list.size() - 1; i >= 0; --i)
			sb.append(list.get(i)).append(" ");
		System.out.println(sb);
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