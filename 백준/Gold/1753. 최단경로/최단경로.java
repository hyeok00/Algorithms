import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static int V, E, S;
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
		V = Integer.parseInt(splitedLine[0]);
		E = Integer.parseInt(splitedLine[1]);
		S = Integer.parseInt(in.readLine());

		// 초기화
		graph = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; ++i)
			graph[i] = new ArrayList<Node>();

		resultArr = new int[V + 1];
		Arrays.fill(resultArr, Integer.MAX_VALUE);

		// 로직
		for (int i = 0; i < E; ++i) {
			splitedLine = in.readLine().split(" ");
			int from = Integer.parseInt(splitedLine[0]);
			int to = Integer.parseInt(splitedLine[1]);
			int weight = Integer.parseInt(splitedLine[2]);
			graph[from].add(new Node(to, weight));
		}

		resultArr[S] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(S, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.peek();
			pq.remove();

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

		// 출력부
		for (int i = 1; i < V + 1; ++i) {
			if (resultArr[i] == Integer.MAX_VALUE)
				sb.append("INF").append("\n");
			else
				sb.append(resultArr[i]).append("\n");
		}
		System.out.println(sb);
	}
}
