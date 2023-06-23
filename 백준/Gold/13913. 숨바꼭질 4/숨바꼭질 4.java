import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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
		if (N == K)
			sb.append("0").append("\n").append(N);
		else
			bfs(N, K);
		// 출력부
		System.out.println(sb);
	}

	private static void bfs(int n, int k) {
		final int MAXSIZE = 100000 + 1;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(n);

		boolean[] visit = new boolean[MAXSIZE];
		int depth = 0;
		int[] rem = new int[MAXSIZE];
		while (!q.isEmpty()) {
			depth++;

			if (visit[k] == true)
				break;
			int qSize = q.size();
			while (--qSize >= 0) {
				int value = q.poll();

				int next = value + 1;
				if (next < MAXSIZE && visit[next] == false) {
					visit[next] = true;
					q.add(next);
					if (rem[next] == 0)
						rem[next] = value;
				}

				int prev = value - 1;
				if (prev >= 0 && visit[prev] == false) {
					visit[prev] = true;
					q.add(prev);
					if (rem[prev] == 0)
						rem[prev] = value;
				}

				int teleport = value * 2;
				if (teleport < MAXSIZE && visit[teleport] == false) {
					visit[teleport] = true;
					q.add(teleport);
					if (rem[teleport] == 0)
						rem[teleport] = value;
				}
			}
		}
		List<Integer> list = new ArrayList<>();
		list.add(k);
		int temp = k;
		while (rem[temp] != n) {
			list.add(rem[temp]);
			temp = rem[temp];
		}
		list.add(n);
		sb.append(depth - 1).append("\n");
		for (int i = list.size() - 1; i >= 0; --i) {
			sb.append(list.get(i) + " ");
		}
	}
}