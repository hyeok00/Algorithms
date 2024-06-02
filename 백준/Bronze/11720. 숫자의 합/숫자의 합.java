import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		int N = stoi(in.readLine());

		int sum = 0;
		String s = in.readLine();
		for (int i = 0; i < N; ++i) {
			sum += s.charAt(i) - '0';
		}
		System.out.println(sum);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}