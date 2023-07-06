import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int n;
		int weight;

		Node(int a, int b) {
			n = a;
			weight = b;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int N, E;
	static int spot1, spot2;
	static List<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");

		N = Integer.parseInt(splitedLine[0]);
		E = Integer.parseInt(splitedLine[1]);
		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; ++i)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < E; ++i) {
			splitedLine = in.readLine().split(" ");
			int first = Integer.parseInt(splitedLine[0]);
			int second = Integer.parseInt(splitedLine[1]);
			int weight = Integer.parseInt(splitedLine[2]);
			graph[first].add(new Node(second, weight));
			graph[second].add(new Node(first, weight));
		}
		splitedLine = in.readLine().split(" ");
		spot1 = Integer.parseInt(splitedLine[0]);
		spot2 = Integer.parseInt(splitedLine[1]);

		int firstPath = getDistance(1, spot1, spot2, N);
		int secondPath = getDistance(1, spot2, spot1, N);
		if (firstPath == -1 && secondPath == -1)
			System.out.println("-1");
		else if (firstPath == -1 && secondPath != -1)
			System.out.println(secondPath);
		else if (firstPath != -1 && secondPath == -1)
			System.out.println(firstPath);
		else
			System.out.println(firstPath > secondPath ? secondPath : firstPath);
	}

	private static int getDistance(int start, int spot1, int spot2, int end) {
		int sum = 0;

		int value = dijkstra(start, spot1);
		if (value == -1)
			return -1;
		sum += value;

		value = dijkstra(spot1, spot2);
		if (value == -1)
			return -1;
		sum += value;

		value = dijkstra(spot2, end);
		if (value == -1)
			return -1;
		sum += value;

		return sum;
	}

	private static int dijkstra(int start, int end) {
		int dist[] = new int[N + 1];
		Arrays.fill(dist, 8765432);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (dist[node.n] < node.weight)
				continue;

			for (int i = 0; i < graph[node.n].size(); ++i) {
				Node next = graph[node.n].get(i);
				if (dist[next.n] > next.weight + node.weight) {
					dist[next.n] = next.weight + node.weight;
					pq.add(new Node(next.n, dist[next.n]));
				}
			}
		}
		if(dist[end] == 8765432)
			return -1;
		return dist[end];
	}
}