import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		String[] splitedLine = in.readLine().split(" ");

		double[] arr = new double[N];
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(splitedLine[i]);
		}

		int fix, left, right;
		int first = 0;
		int second = 0;
		int third = 0;
		Arrays.sort(arr);
		double sum = Double.MAX_VALUE;
		for (int i = 0; i < N - 2; ++i) {
			fix = i;
			left = fix + 1;
			right = N - 1;
			while (!(left == right)) {
				double value = arr[left] + arr[right] + arr[fix];
				if (Math.abs(value) < Math.abs(sum)) {
					sum = value;
					first = (int) arr[fix];
					second = (int) arr[left];
					third = (int) arr[right];
				}
				if (value < 0) {
					left++;
				} else {
					right--;
				}
			}

		}
		sb.append(first).append(" ").append(second).append(" ").append(third);
		System.out.println(sb);
	}
}