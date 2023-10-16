import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		int weight;

		Node(int b, int c) {
			to = b;
			weight = c;
		}

		@Override
		public int compareTo(Node o) {
			return o.weight - this.weight;
		}
	}

	static int N, D;
	static int[] dist;
	static List<Node>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		D = stoi(splitedLine[1]);
		graph = new List[D + 1];
		dist = new int[D + 1];
		for (int i = 0; i <= D; ++i) {
			graph[i] = new ArrayList<>();
			graph[i].add(new Node(i + 1, 1));
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			int from = stoi(splitedLine[0]);
			int to = stoi(splitedLine[1]);
			int weight = stoi(splitedLine[2]);
			if (from >= D || to > D)
				continue;
			graph[from].add(new Node(to, weight));
		}

		System.out.println(dijkstra(0, D));

	}

	private static int dijkstra(int start, int end) {
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int s = cur.to;
			if (dist[s] < cur.weight)
				continue;
			if(cur.to >= D)
				continue;

			for (int i = 0; i < graph[s].size(); ++i) {
				Node next = graph[s].get(i);
				int nextN = next.to;
				if (dist[next.to] > cur.weight + next.weight) {
					dist[next.to] = cur.weight + next.weight;
					pq.add(new Node(next.to, cur.weight + next.weight));
				}
			}
		}

		return dist[end];
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}