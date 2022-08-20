import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 1; i <= n; ++i) {
			q.add(i);
		}
		sb.append("<");

		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int value = q.peek();
			q.poll();
			if (cnt == k) {
				cnt = 0;
				if(q.size()==0)
					sb.append(value);
				else {
					sb.append(value + ", ");
				}
			} else {
				q.add(value);
			}
		}
		sb.append(">");

		System.out.println(sb);
	}
}