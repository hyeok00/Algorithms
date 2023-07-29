import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String S = in.readLine();
		String T = in.readLine();
		int result = 0;

		Queue<String> q = new ArrayDeque<>();
		q.add(T);
		while (!q.isEmpty()) {
			String str = q.poll();

			if (str.length() < S.length())
				break;

			if (str.equals(S)) {
				result = 1;
				break;
			}

			int lastIdx = str.length() - 1;
			String subStr = str.substring(0, lastIdx);
			if (str.charAt(lastIdx) == 'A') {
				q.add(subStr);
			}
			if (str.charAt(0) == 'B') {
				q.add(new StringBuilder(str).reverse().toString().substring(0, lastIdx));
			}
		}

		System.out.println(result);
	}
}