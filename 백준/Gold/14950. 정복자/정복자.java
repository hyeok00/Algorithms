import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int start;
		int end;
		int value;

		Node(int a, int b, int c) {
			start = a;
			end = b;
			value = c;
		}

		public int compareTo(Node n) {
			return value - n.value;
		}
	}

	static int parent[];
	static int N, M, T;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		T = Integer.parseInt(splitedLine[2]);

		init();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			pq.add(new Node(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]),
				Integer.parseInt(splitedLine[2])));
		}

		int value = 0;
		int count = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (find(node.start) != find(node.end)) {
				union(node.start, node.end);
				value += node.value;
				value += count*T;
				count++;
			}
		}

		System.out.println(value);

	}

	public static void init() {
		parent = new int[N+1];
		for (int i = 1; i <= N; ++i) {
			parent[i] = i;
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	private static int find(int n) {
		if (n == parent[n])
			return n;
		else
			return parent[n] = find(parent[n]);
	}
}