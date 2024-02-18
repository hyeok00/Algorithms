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

		Node(int a, int b) {
			to = a;
			weight = b;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int N, M;
	static List<Node>[] graph;
	static int[] dist, prev;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		graph = new List[N + 1];
		for (int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < M; ++i) {
			inputs = in.readLine().split(" ");
			graph[stoi(inputs[0])].add(new Node(stoi(inputs[1]), stoi(inputs[2])));
			graph[stoi(inputs[1])].add(new Node(stoi(inputs[0]), stoi(inputs[2])));
		}

		dist = new int[N + 1];
		prev = new int[N + 1];
		prev[0] = -1;
		dijkstra(1, N, true);

		int max = dist[N];
		int end = prev[N];
		int start = N;
		while (start != -1) {
			dijkstra(1, N, false, new int[] {end, start});
			max = Math.max(max, dist[N]);
			end = start;
			start = prev[start];
		}
		System.out.println(max);
	}

	private static void dijkstra(int start, int end, boolean write, int[] pos) {
		Arrays.fill(dist, 123456789);
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.to == end)
				break;
			for (Node next : graph[cur.to]) {
				if ((cur.to == pos[0] && next.to == pos[1]) || (cur.to == pos[1] && next.to == pos[0]))
					continue;
				if (dist[next.to] > next.weight + dist[cur.to]) {
					dist[next.to] = next.weight + dist[cur.to];
					pq.add(new Node(next.to, dist[next.to]));
					if (write)
						prev[next.to] = cur.to;
				}
			}
		}
	}

	private static void dijkstra(int start, int end, boolean write) {
		dijkstra(start, end, write, new int[] {-1, -1});
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}