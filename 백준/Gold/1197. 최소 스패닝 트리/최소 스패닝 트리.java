import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;
	static int[] arr;

	private static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int start, int end, int weight) {
			this.from = start;
			this.to = end;
			this.weight = weight;
		}

		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine;
			splitedLine = in.readLine().split(" ");
			int V = Integer.parseInt(splitedLine[0]);
			int E = Integer.parseInt(splitedLine[1]);

			Edge[] el = new Edge[E];

			arr = new int[V + 1];
			for (int i = 0; i < V + 1; ++i) {
				makeSet(i);
			}

			for (int i = 0; i < E; ++i) {
				splitedLine = in.readLine().split(" ");
				int first = Integer.parseInt(splitedLine[0]);
				int second = Integer.parseInt(splitedLine[1]);
				int weight = Integer.parseInt(splitedLine[2]);

				el[i] = new Edge(first, second, weight);
			}
			Arrays.sort(el);

			int result = 0; // MST 비용
			int count = 0; // 연결 간선 수
			for (Edge edge : el) {
				// 싸이클이 발생하지 않았으면
				if (unionSet(edge.from, edge.to)) {
					result += edge.weight;
					if (++count == V - 1) { // 연결 간선수가 (정점 수 - 1) 이면 최소 신장 트리 완성
						break;
					}
				}
			}
			sb.append(result);
		
		// 출력부
		System.out.println(sb);
	}

	public static void makeSet(int x) {
		arr[x] = x;
	}

	public static int findSet(int x) {
		if (arr[x] == x)
			return x;
		else
			return arr[x] = findSet(arr[x]);
	}

	public static boolean unionSet(int x, int y) {
		if (findSet(x) == findSet(y)) {
			return false;
		}
		arr[findSet(x)] = findSet(y);
		return true;
	}
}