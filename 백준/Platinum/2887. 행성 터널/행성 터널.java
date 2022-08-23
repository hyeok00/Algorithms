import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static StringBuilder sb;
	static int[] arr;

	static class Plannet {
		int n;
		int x;
		int y;
		int z;

		public Plannet(int a, int b, int c, int n) {
			this.n = n;
			x = a;
			y = b;
			z = c;
		}
	}

	static class Edge implements Comparable<Edge> {
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
		int N = Integer.parseInt(in.readLine());

		Plannet[] pl = new Plannet[N];
		arr = new int[N + 1];
		for (int i = 0; i < N + 1; ++i) {
			makeSet(i);
		}
		String[] splitedLine;
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			int x = Integer.parseInt(splitedLine[0]);
			int y = Integer.parseInt(splitedLine[1]);
			int z = Integer.parseInt(splitedLine[2]);
			pl[i] = new Plannet(x, y, z, i);
		}

		ArrayList<Edge> el = new ArrayList<>();

		Arrays.sort(pl, new Comparator<Plannet>() {
			@Override
			public int compare(Plannet o1, Plannet o2) {
				return o1.x - o2.x;
			}
		});
		for (int i = 1; i < N; ++i) {
			el.add(new Edge(pl[i].n, pl[i - 1].n, Math.abs(pl[i].x - pl[i - 1].x)));
		}
		
		Arrays.sort(pl, new Comparator<Plannet>() {
			@Override
			public int compare(Plannet o1, Plannet o2) {
				return o1.y - o2.y;
			}
		});
		for (int i = 1; i < N; ++i) {
			el.add(new Edge(pl[i].n, pl[i - 1].n, Math.abs(pl[i].y - pl[i - 1].y)));
		}
		
		Arrays.sort(pl, new Comparator<Plannet>() {
			@Override
			public int compare(Plannet o1, Plannet o2) {
				return o1.z - o2.z;
			}
		});
		for (int i = 1; i < N; ++i) {
			el.add(new Edge(pl[i].n, pl[i - 1].n, Math.abs(pl[i].z - pl[i - 1].z)));
		}

		Collections.sort(el);
		long result = 0; // MST 비용
		int count = 0; // 연결 간선 수
		for (Edge edge : el) {
			if (unionSet(edge.from, edge.to)) {
				result += edge.weight;
				count++;
				if (count == N - 1) { // 연결 간선수가 (정점 수 - 1) 이면 최소 신장 트리 완성
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
		else {
			arr[x] = findSet(arr[x]);
			return arr[x];
		}
	}

	public static boolean unionSet(int x, int y) {
		if (findSet(x) == findSet(y)) {
			return false;
		}
		arr[findSet(x)] = findSet(y);
		return true;
	}
}
