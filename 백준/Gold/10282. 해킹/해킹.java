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

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int N, D, C;
	static StringBuilder sb = new StringBuilder();
	static List<Node>[] list;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String[] inputs = in.readLine().split(" ");
			N = stoi(inputs[0]);
			D = stoi(inputs[1]);
			C = stoi(inputs[2]);

			int[] dist = new int[N + 1];
			list = new List[N + 1];
			for (int i = 0; i <= N; ++i)
				list[i] = new ArrayList<>();

			for (int i = 0; i < D; ++i) {
				inputs = in.readLine().split(" ");
				list[stoi(inputs[1])].add(new Node(stoi(inputs[0]), stoi(inputs[2])));
			}

			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[C] = 0;
			dijkstra(C, dist);

			int count = 0;
			int max = -1;
			for (int i = 1; i <= N; ++i) {
				if (dist[i] != Integer.MAX_VALUE) {
					count++;
					max = Math.max(max, dist[i]);
				}
			}
			sb.append(count).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
	}

	private static void dijkstra(int start, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (int i = 0; i < list[cur.to].size(); ++i) {
				Node next = list[cur.to].get(i);
				if (dist[next.to] > dist[cur.to] + next.weight) {
					dist[next.to] = dist[cur.to] + next.weight;
					pq.add(next);
				}
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}