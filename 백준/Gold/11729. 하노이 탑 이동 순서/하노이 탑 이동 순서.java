import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, count;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = stoi(in.readLine());
		simulation(N, 1, 3, 2);
		System.out.println(count);
		System.out.println(sb);

	}

	private static void simulation(int n, int from, int to, int other) {
		if (n == 1) {
			count++;
			writeMove(from, to);
			return;
		}
		simulation(n - 1, from, other, to);
		simulation(1, from, to, other);
		simulation(n - 1, other, to, from);
	}

	private static void writeMove(int from, int to) {
		sb.append(from)
			.append(" ")
			.append(to)
			.append("\n");
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}