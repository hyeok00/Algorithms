import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String commands = in.readLine();
			in.readLine();
			StringTokenizer st = new StringTokenizer(in.readLine(), "[,]");
			Deque<Integer> q = new ArrayDeque<>();
			while (st.hasMoreTokens())
				q.add(stoi(st.nextToken()));

			boolean isReverse = false;
			boolean isError = false;
			for (int i = 0; i < commands.length(); ++i) {
				if (commands.charAt(i) == 'R')
					isReverse = !isReverse;
				else {
					if (q.isEmpty()) {
						isError = true;
						break;
					}
					if (isReverse) {
						q.pollLast();
					} else {
						q.pollFirst();
					}
				}
			}
			if (isError)
				sb.append("error\n");
			else {
				sb.append("[");
				while (!q.isEmpty()) {
					if (isReverse)
						sb.append(q.pollLast());
					else
						sb.append(q.pollFirst());
					if (q.size() > 0)
						sb.append(",");
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}