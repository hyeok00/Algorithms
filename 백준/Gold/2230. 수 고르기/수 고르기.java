import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] arr;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		arr = new int[N];
		for (int i = 0; i < N; ++i)
			arr[i] = stoi(in.readLine());

		Arrays.sort(arr);

		int min = Integer.MAX_VALUE;
		int left = 0;
		int right = left + 1;
		while (right < N) {
			int value = arr[right] - arr[left];
			if (value >= M) {
				min = Math.min(min, value);
				if(left + 1 < right) {
					left++;
				} else {
					right++;
				}
			} else {
				right++;
			}
		}
		System.out.println(min);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}