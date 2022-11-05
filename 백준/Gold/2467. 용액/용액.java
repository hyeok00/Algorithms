import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		String[] splitedLine = in.readLine().split(" ");

		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		int left = 0;
		int right = N - 1;
		int first = 0;
		int second = 0;
		int sum = Integer.MAX_VALUE;
		while (!(left == right)) {
			int value = arr[left] + arr[right];
			if (Math.abs(value) < Math.abs(sum)) {
				sum = value;
				first = arr[left];
				second = arr[right];
			}
			if (value < 0) {
				left++;
			} else {
				right--;
			}
		}
		sb.append(first).append(" ").append(second);
		System.out.println(sb);
	}
}