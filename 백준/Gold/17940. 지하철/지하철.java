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
		int count;

		Node(int a, int b, int c) {
			to = a;
			weight = b;
			count = c;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count == o.count)
				return this.weight - o.weight;
			return this.count - o.count;
		}
	}

	static int N, M;
	static List<Node>[] graph;
	static int[] type, dist, transfer;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		type = new int[N];
		for (int i = 0; i < N; ++i)
			type[i] = stoi(in.readLine());

		graph = new List[N];
		for (int i = 0; i < N; ++i) {
			graph[i] = new ArrayList<>();
			inputs = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				int value = stoi(inputs[j]);
				if (value != 0)
					graph[i].add(new Node(j, value, 0));
			}
		}

		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;

		transfer = new int[N];

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));

		int minTransferCount = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.to == M) {
				if (cur.count <= minTransferCount) {
					dist[cur.to] = Math.min(dist[cur.to], cur.weight);
					minTransferCount = Math.min(minTransferCount, cur.count);
				}
				break;
			}

			for (Node next : graph[cur.to]) {
				if (dist[next.to] > dist[cur.to] + next.weight) {
					dist[next.to] = dist[cur.to] + next.weight;
					int nextCount = cur.count + (type[cur.to] == type[next.to] ? 0 : 1);
					pq.add(new Node(next.to, dist[next.to], nextCount));
					transfer[next.to] = nextCount;
				}
			}
		}

		System.out.println(transfer[M] + " " + dist[M]);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}