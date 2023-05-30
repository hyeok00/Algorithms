import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		int weight;

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		Node(int a, int b, int c) {
			start = a;
			end = b;
			weight = c;
		}
	}

	static int N, M;
	static int[] arr;

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (a > b)
				arr[a] = b;
			else
				arr[b] = a;
		}
	}

	public static int find(int n) {
		if (arr[n] == n)
			return n;
		else
			return arr[n] = find(arr[n]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		arr = new int[N + 1];
		for (int i = 0; i <= N; ++i) {
			arr[i] = i;
		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int s = Integer.parseInt(splitedLine[0]);
			int e = Integer.parseInt(splitedLine[1]);
			int w = Integer.parseInt(splitedLine[2]);
			pq.add(new Node(s, e, w));
		}

		int result = 0;
		int last = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (find(node.start) != find(node.end)) {
				union(node.start, node.end);
				result += node.weight;
				last = node.weight;
			}
		}
		System.out.println(result - last);
	}
}