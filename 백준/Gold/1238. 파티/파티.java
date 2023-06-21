import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static int N, M, X;
	static ArrayList<Node>[] graph;
	static int[] resultArr;

	static class Node implements Comparable<Node> {
		int n;
		int weight;

		public Node(int a, int b) {
			n = a;
			weight = b;
		}

		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		X = Integer.parseInt(splitedLine[2]);

		// 초기화
		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; ++i)
			graph[i] = new ArrayList<Node>();

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int from = Integer.parseInt(splitedLine[0]);
			int to = Integer.parseInt(splitedLine[1]);
			int weight = Integer.parseInt(splitedLine[2]);
			graph[from].add(new Node(to, weight));
		}

		// 로직
		int max = 0;
		int[] go = new int[N + 1];
		for (int t = 1; t <= N; ++t) {
			if (t == X)
				continue;
			resultArr = new int[N + 1];
			Arrays.fill(resultArr, Integer.MAX_VALUE);
			resultArr[t] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(t, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				// 주변 정점 탐색
				for (int i = 0; i < graph[cur.n].size(); ++i) {
					Node next = graph[cur.n].get(i);

					// 최소비용 갱신
					if (resultArr[next.n] > cur.weight + next.weight) {
						resultArr[next.n] = cur.weight + next.weight;
						pq.add(new Node(next.n, resultArr[next.n]));
					}
				}
			}
			go[t] = resultArr[X];
		}

		for (int t = 1; t <= N; ++t) {
			if (t == X)
				continue;
			resultArr = new int[N + 1];
			Arrays.fill(resultArr, Integer.MAX_VALUE);
			resultArr[X] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(X, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				// 주변 정점 탐색
				for (int i = 0; i < graph[cur.n].size(); ++i) {
					Node next = graph[cur.n].get(i);

					// 최소비용 갱신
					if (resultArr[next.n] > cur.weight + next.weight) {
						resultArr[next.n] = cur.weight + next.weight;
						pq.add(new Node(next.n, resultArr[next.n]));
					}
				}
			}
			max = max > resultArr[t] + go[t] ? max : resultArr[t] + go[t];
		}

		System.out.println(max);
	}
}