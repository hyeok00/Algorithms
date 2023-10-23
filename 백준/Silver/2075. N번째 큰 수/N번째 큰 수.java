import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		int[] arr = new int[N * N];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				int cur = stoi(splitedLine[j]);
				arr[i * N + j] = cur;
			}
		}
		Arrays.sort(arr);
		System.out.println(arr[(N * (N - 1))]);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}