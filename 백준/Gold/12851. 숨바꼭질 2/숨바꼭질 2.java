import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int K = Integer.parseInt(splitedLine[1]);

		// 로직
		bfs(N, K);
		// 출력부
		System.out.println(sb);
	}

	private static void bfs(int n, int k) {
		final int MAXSIZE = 100000 + 1;
		Queue<Integer> q = new LinkedList();
		q.add(n);

		boolean[] visit = new boolean[MAXSIZE];
		int depth = 0;
		int count = 0;
		while (!q.isEmpty()) {
			depth++;
			int qSize = q.size();
			while (--qSize >= 0) {
				int value = q.peek();
				q.remove();
				
				if(value==k) {
					count++;
				}
				
				visit[value] = true;

				int next = value + 1;
				if (next < MAXSIZE && visit[next] == false) {
					q.add(next);
				}

				int prev = value - 1;
				if (prev >= 0 && visit[prev] == false) {
					q.add(prev);
				}

				int teleport = value * 2;
				if (teleport < MAXSIZE && visit[teleport] == false) {
					q.add(teleport);
				}
			}
			if (visit[k] == true)
				break;
		}
		sb.append(depth - 1 + "\n" + count);
	}
}