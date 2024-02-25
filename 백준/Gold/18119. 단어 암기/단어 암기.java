import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		int[] arr = new int[N];
		for (int i = 0; i < N; ++i) {
			String s = in.readLine();
			for (int j = 0; j < s.length(); ++j)
				arr[i] = arr[i] | (1 << (s.charAt(j) - 'a'));
		}

		int base = 0;
		base = 1 << 'z' - 'a' + 2;
		base -= 1;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i) {
			inputs = in.readLine().split(" ");
			if (inputs[0].equals("1")) {
				base = base & ~(1 << (inputs[1].charAt(0) - 'a'));
			} else {
				base = base | (1 << (inputs[1].charAt(0) - 'a'));
			}

			final int finalBase = base;
			int count = (int)Arrays.stream(arr)
				.filter(a -> (a & finalBase) == a)
				.count();
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}