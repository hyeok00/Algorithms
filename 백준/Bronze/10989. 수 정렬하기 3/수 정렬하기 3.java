import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		count = new int[10001];
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; ++i) {
			int value = Integer.parseInt(in.readLine());
			count[value]++;
		}
		for (int i = 1; i < 10001; ++i) {
			if (count[i] != 0) {
				for (int j = 0; j < count[i]; ++j) {
					sb.append(i).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}