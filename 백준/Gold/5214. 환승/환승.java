import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static int N, K, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		K = Integer.parseInt(splitedLine[1]);
		M = Integer.parseInt(splitedLine[2]);

		List<Integer>[] node = new List[N + M + 1];
		for (int i = 0; i < N + M + 1; ++i)
			node[i] = new ArrayList<>();

		for (int i = 1; i <= M; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < K; ++j) {
				int value = Integer.parseInt(splitedLine[j]);
				node[value].add(i + N);
				node[i + N].add(value);
			}
		}

		int[] visit = new int[N + M + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		visit[1] = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == N) {
				System.out.println(visit[cur]);
				return;
			}
			for (int i = 0; i < node[cur].size(); ++i) {
				int next = node[cur].get(i);
				if (visit[next] == 0) {
					if (next > N)
						visit[next] = visit[cur];
					else
						visit[next] = visit[cur] + 1;
					q.add(next);
				}
			}
		}
		System.out.println(-1);
	}
}