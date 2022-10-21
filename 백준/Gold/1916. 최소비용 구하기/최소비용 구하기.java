import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int n;
		int weight;

		public Node(int n, int weight) {
			this.n = n;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static StringBuilder sb;
	static String[] splitedLine;
	static int N, M, START, END;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		graph = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N + 1; ++i) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int s = Integer.parseInt(splitedLine[0]);
			int e = Integer.parseInt(splitedLine[1]);
			int w = Integer.parseInt(splitedLine[2]);
			graph.get(s).add(new Node(e, w));
		}

		splitedLine = in.readLine().split(" ");
		START = Integer.parseInt(splitedLine[0]);
		END = Integer.parseInt(splitedLine[1]);

		boolean[] isVisited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		for (int i = 0; i < N + 1; ++i) {
			dist[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(START, 0));
		dist[START] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dist[cur.n] < cur.weight)
				continue;

			int size = graph.get(cur.n).size();
			for (int i = 0; i < size; ++i) {
				Node next = graph.get(cur.n).get(i);

				if (dist[next.n] > cur.weight + next.weight) {
					dist[next.n] = cur.weight + next.weight;
					pq.add(new Node(next.n, dist[next.n]));
				}
			}
		}

		System.out.println(dist[END]);
	}
}