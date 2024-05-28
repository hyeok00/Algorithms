import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int weight;

		Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static List<Node>[] graph;
	static int N, M;
	static int[] dist, prev;
	static int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		graph = new List[N + 1];
		for (int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < M; ++i) {
			inputs = in.readLine().split(" ");
			int a = stoi(inputs[0]);
			int b = stoi(inputs[1]);
			int c = stoi(inputs[2]);
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		dist = new int[N + 1];
		prev = new int[N + 1];
		dijkstra(1, N, new int[] {0, 0});

		List<int[]> pathList = new ArrayList<>();
		for (int i = N; prev[i] != 0; i = prev[i])
			pathList.add(new int[] {prev[i], i});

		int max = 0;
		int orgTime = dist[N];
		for (int[] path : pathList) {
			dijkstra(1, N, path);
			int fixTime = dist[N];
			if (fixTime == INF) {
				max = -1;
				break;
			}
			max = Math.max(max, fixTime - orgTime);
		}
		System.out.println(max);
	}

	private static void dijkstra(int start, int end, int[] disable) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.to == end)
				break;
			for (Node next : graph[cur.to]) {
				if (cur.to == disable[0] && next.to == disable[1])
					continue;
				if (dist[next.to] > dist[cur.to] + next.weight) {
					dist[next.to] = dist[cur.to] + next.weight;
					prev[next.to] = cur.to;
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}