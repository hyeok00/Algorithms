import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M;

	static List<Node>[] graph;

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

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		graph = new List[N + 1];
		for (int i = 0; i < N + 1; ++i)
			graph[i] = new ArrayList<>();

		long total = 0;
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int a = stoi(splitedLine[0]);
			int b = stoi(splitedLine[1]);
			int c = stoi(splitedLine[2]);
			total += c;
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		boolean[] visit = new boolean[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		int count = 0;
		long weight = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visit[cur.to] == true)
				continue;
			count++;
			weight += cur.weight;
			visit[cur.to] = true;
			for (int i = 0; i < graph[cur.to].size(); ++i) {
				Node next = graph[cur.to].get(i);
				if (!visit[next.to]) {
					pq.add(next);
				}
			}

			if (count == N)
				break;
		}
		if (count != N)
			System.out.println(-1);
		else
			System.out.println(total - weight);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}