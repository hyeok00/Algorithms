import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		int[] arr = new int[N];
		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}
		Arrays.sort(arr);

		int left = 0;
		int right = arr[N - 1];
		int middle = 0;
		while (true) {
			middle = (left + right) / 2;
			long value = getLength(middle, N, arr) - M;
			if (value == 0 || left > right) {
				break;
			} else if (value < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		System.out.println(middle);
	}

	private static long getLength(int value, int size, int[] data) {
		long sum = 0;
		for (int i = 0; i < size; ++i) {
			if (data[i] > value) {
				sum += (data[i] - value);
			}
		}
		return sum;
	}

}