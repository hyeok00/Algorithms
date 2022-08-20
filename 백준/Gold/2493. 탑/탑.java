import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	static StringBuilder sb;

	public static class Pair {
		int idx;
		int val;

		Pair(int a, int b) {
			idx = a;
			val = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int N = Integer.parseInt(in.readLine());
		String[] splitedLine = in.readLine().split(" ");

		// 로직
		Stack<Pair> s = new Stack<>();
		s.push(new Pair(0, -1));
		for (int i = 0; i < N; ++i) {
			int val = Integer.parseInt(splitedLine[i]);
			Pair p = s.peek();
			if (p.val < val) {
				while (true) {
					if (s.empty()) {
						sb.append("0 ");
						break;
					}
					p = s.peek();
					if (p.val > val) {
						sb.append(s.peek().idx + " ");
						break;
					}
					s.pop();
				}

			} else {
				sb.append(s.peek().idx + " ");
			}
			s.push(new Pair(i + 1, val));
		}

		// 출력
		System.out.println(sb);
	}
}