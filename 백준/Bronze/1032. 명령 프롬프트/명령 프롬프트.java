import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static String[] data;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());
		data = new String[N];
		for (int i = 0; i < N; ++i) {
			data[i] = in.readLine();
		}

		StringBuilder sb = new StringBuilder();
		int size = data[0].length();
		for (int i = 0; i < size; ++i) {
			sb.append(getChar(i));
		}
		System.out.println(sb);
	}

	private static char getChar(int k) {
		char base = data[0].charAt(k);
		for (int i = 0; i < N; ++i) {
			if (data[i].charAt(k) != base) {
				return '?';
			}
		}
		return base;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}