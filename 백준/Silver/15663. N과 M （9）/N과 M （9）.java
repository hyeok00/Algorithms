import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;
	static int[] value;
	static int[] arr;
	static boolean[] used;
	static int A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		A = Integer.parseInt(splitedLine[0]);
		B = Integer.parseInt(splitedLine[1]);

		value = new int[A];
		arr = new int[B];
		used = new boolean[A];

		splitedLine = in.readLine().split(" ");
		for (int i = 0; i < A; ++i) {
			value[i] = Integer.parseInt(splitedLine[i]);
		}

		// 로직
		Arrays.sort(value);
		comb(A, B, 0);
		System.out.println(sb);
	}

	public static void comb(int n, int r, int depth) {
		if (r == depth) {
			for (int i = 0; i < depth; ++i) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");

			return;
		}
		int prev = 0;
		for (int i = 0; i < n; i++) {
			if (used[i] == false && prev != value[i]) {
				used[i] = true;
				arr[depth] = value[i];
				prev = value[i];
			} else {
				continue;
			}
			
			comb(n, r, depth + 1);
			used[i] = false;
		}
	}
}
