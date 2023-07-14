import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N, K;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		sb = new StringBuilder();

		N = Integer.parseInt(splitedLine[0]);
		K = Integer.parseInt(splitedLine[1]);
		String str = in.readLine();
		Stack<Integer> s = new Stack<>();

		int count = 0;
		for (int i = 0; i < N; ++i) {
			int value = str.charAt(i) - '0';

			while (count < K && !s.isEmpty() && s.peek() < value) {
				s.pop();
				count++;
			}
			s.add(value);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N - K; i++) {
			sb.append(s.get(i));
		}
		System.out.print(sb);
	}

}