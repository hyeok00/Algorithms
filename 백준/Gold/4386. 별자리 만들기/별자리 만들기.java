import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static class Node implements Comparable<Node> {
		int to;
		double weight;

		Node(int a, double b) {
			to = a;
			weight = b;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight > o.weight)
				return 1;
			else if (this.weight < o.weight)
				return -1;
			else
				return 0;
		}
	}

	static class Point {
		double x;
		double y;

		Point(double a, double b) {
			x = a;
			y = b;
		}
	}

	static int N;
	static List<Point> points;
	static List<Node>[] graph;
	static boolean visit[];

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());

		points = new ArrayList<>();
		graph = new List[N + 1];
		visit = new boolean[N + 1];
		for (int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		points.add(new Point(0, 0));
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			double x = Double.parseDouble(splitedLine[0]);
			double y = Double.parseDouble(splitedLine[1]);
			points.add(new Point(x, y));
		}

		setDistance();

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));

		double cost = 0;
		int count = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visit[cur.to] == true)
				continue;
			visit[cur.to] = true;
			count++;
			cost += cur.weight;

			if (count == N)
				break;
			for (int i = 0; i < graph[cur.to].size(); ++i)
				pq.add(graph[cur.to].get(i));

		}
		cost = Math.floor(cost * 100) * 0.01;
		System.out.println(cost);
	}

	private static void setDistance() {
		int size = points.size();
		for (int i = 1; i < size; ++i) {
			for (int j = 1; j < size; ++j) {
				if (i == j)
					graph[i].add(new Node(j, 100000000));
				else
					graph[i].add(new Node(j, getDistance(points.get(i), points.get(j))));
			}
		}
	}

	private static double getDistance(Point p1, Point p2) {
		double dx = Math.abs(p1.x - p2.x);
		double dy = Math.abs(p1.y - p2.y);
		return Math.sqrt(dx * dx + dy * dy);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}