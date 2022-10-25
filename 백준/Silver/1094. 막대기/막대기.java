import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
			int N = Integer.parseInt(in.readLine());
			int result = 1;
			while (true) {
				if (N == 1)
					break;
				if (N % 2 == 1)
					result++;
				N = N / 2;
			}
			sb.append(result);
		System.out.println(sb);
	}
}