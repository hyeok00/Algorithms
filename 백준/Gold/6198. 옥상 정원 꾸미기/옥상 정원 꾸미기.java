import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		Stack<Integer> s = new Stack<>();
		long res = 0;
		for (int i = 0; i < N; ++i) {
			int value = Integer.parseInt(in.readLine());
			while (!s.isEmpty() && s.peek() <= value)
				s.pop();
			res += s.size();
			s.push(value);
		}
		System.out.println(res);

	}
}