import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		long A = stol(splitedLine[0]);
		long B = stol(splitedLine[1]);
		long C = stol(splitedLine[2]);

		System.out.println(calculate(A, B, C));
	}

	private static long calculate(long a, long b, long c) {
		if (b == 1)
			return a % c;

		long value = calculate(a, b / 2, c);
		value = ((value % c) * (value % c)) % c;
		if (b % 2 == 1) {
			value = (value * a) % c;
		}
		return value;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}

}