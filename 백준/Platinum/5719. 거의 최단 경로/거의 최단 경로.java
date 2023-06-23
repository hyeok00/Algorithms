import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

	static class Pair {
		int first;
		int second;

		Pair(int a, int b) {
			first = a;
			second = b;
		}
	}

	static StringBuilder sb;
	static String[] splitedLine;
	static int N, M, START, END;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			splitedLine = in.readLine().split(" ");
			N = Integer.parseInt(splitedLine[0]);
			M = Integer.parseInt(splitedLine[1]);
			if (N == 0 && M == 0)
				break;
			graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i < N + 1; ++i) {
				graph.add(new ArrayList<>());
			}

			splitedLine = in.readLine().split(" ");
			START = Integer.parseInt(splitedLine[0]);
			END = Integer.parseInt(splitedLine[1]);

			for (int i = 0; i < M; ++i) {
				splitedLine = in.readLine().split(" ");
				int s = Integer.parseInt(splitedLine[0]);
				int e = Integer.parseInt(splitedLine[1]);
				int w = Integer.parseInt(splitedLine[2]);
				graph.get(s).add(new Node(e, w));
			}

			int[] dist = new int[N + 1];
			for (int i = 0; i < N + 1; ++i) {
				dist[i] = Integer.MAX_VALUE;
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(START, 0));
			dist[START] = 0;

			// int[] rem = new int[N + 1];
			List<Integer>[] rem = new List[N];
			for (int i = 0; i < N; ++i) {
				rem[i] = new ArrayList<Integer>();
			}
			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (dist[cur.n] < cur.weight)
					continue;

				int size = graph.get(cur.n).size();
				for (int i = 0; i < size; ++i) {
					Node next = graph.get(cur.n).get(i);
					if (dist[next.n] == cur.weight + next.weight) {
						rem[next.n].add(cur.n);
					}
					if (dist[next.n] > cur.weight + next.weight) {
						rem[next.n].clear();
						rem[next.n].add(cur.n);
						dist[next.n] = cur.weight + next.weight;
						pq.add(new Node(next.n, dist[next.n]));
					}
				}
			}

			boolean[][] check = new boolean[N][N];
			boolean[] check2 = new boolean[N];
			Queue<Pair> q = new ArrayDeque<>();
			for (int i = 0; i < rem[END].size(); ++i) {
				q.add(new Pair(rem[END].get(i), END));
			}
			while (!q.isEmpty()) {
				Pair p = q.poll();
				check[p.first][p.second] = true;
				check2[p.first]=true;
				for (int i = 0; i < rem[p.first].size(); ++i) {
					if(check2[rem[p.first].get(i)] == false || rem[p.first].get(i) ==0)
						q.add(new Pair(rem[p.first].get(i), p.first));
				}
			}

			// 재탐색
			for (int i = 0; i < N + 1; ++i) {
				dist[i] = Integer.MAX_VALUE;
			}
			pq.add(new Node(START, 0));
			dist[START] = 0;

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (dist[cur.n] < cur.weight)
					continue;

				int size = graph.get(cur.n).size();
				for (int i = 0; i < size; ++i) {
					Node next = graph.get(cur.n).get(i);
					if (check[cur.n][next.n] == true)
						continue;

					if (dist[next.n] > cur.weight + next.weight) {
						dist[next.n] = cur.weight + next.weight;
						pq.add(new Node(next.n, dist[next.n]));
					}
				}
			}

			if (dist[END] == Integer.MAX_VALUE)
				sb.append("-1").append("\n");
			else
				sb.append(dist[END]).append("\n");
		}
		System.out.println(sb);
	}
}