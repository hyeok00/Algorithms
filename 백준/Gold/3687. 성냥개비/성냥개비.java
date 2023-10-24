import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N, T;
	static long[] min;
	static String[] max;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		T = stoi(in.readLine());
		min = new long[101];
		max = new String[101];

		StringBuilder sb = new StringBuilder();

		calculateMin();
		calculateMax();
		for (int i = 0; i < T; ++i) {
			N = stoi(in.readLine());
			sb.append(min[N]).append(" ").append(max[N]).append("\n");
		}
		System.out.println(sb);
	}

	private static void calculateMin() {
		Arrays.fill(min, Long.MAX_VALUE);
		min[2] = 1;
		min[3] = 7;
		min[4] = 4;
		min[5] = 2;
		min[6] = 6;
		min[7] = 8;
		min[8] = 10;
		long[] count = {1, 7, 4, 2, 0, 8};
		for (int i = 9; i <= 100; ++i) {
			for (int j = 2; j <= 7; ++j) {
				min[i] = Math.min((min[i-j] * 10) + count[j-2], min[i]);
			}
		}
	}

	private static void calculateMax() {
		max[2] = "1";
		max[3] = "7";
		for (int i = 4; i <= 100; ++i) {
			if (isOdd(i)) {
				max[i] = "7" + max[i - 3];
			} else {
				max[i] = max[i - 2] + "1";
			}
		}

	}

	private static boolean isOdd(int i) {
		if (i % 2 == 1)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}