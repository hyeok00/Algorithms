import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static List<Node>[] list;
	static boolean[] visit;

	static class Node implements Comparable<Node> {
		int to;
		int weight;

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

		Node(int b, int c) {
			to = b;
			weight = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		visit = new boolean[N];
		list = new List[N];
		for (int i = 0; i < N; ++i)
			list[i] = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				int value = stoi(splitedLine[j]);
				if (value != 0)
					list[i].add(new Node(j, value));
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		long result = 0;
		int count = 0;
		pq.add(new Node(0, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visit[cur.to])
				continue;

			visit[cur.to] = true;
			result += cur.weight;
			for (Node node : list[cur.to])
				pq.add(node);
			count++;
			if (count == N)
				break;
		}
		System.out.println(result);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}