import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static class myTree implements Comparable<myTree> {
		int x;
		int y;
		int age;

		myTree(int a, int b, int c) {
			x = a;
			y = b;
			age = c;
		}

		@Override
		public int compareTo(myTree o) {
			return this.age - o.age;
		}
	}

	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int dy[] = {0, 1, 1, 1, 0, -1, -1, -1};

	static int N, M, K;
	static String[] splitedLine;
	static int[][] weight, base;
	static PriorityQueue<myTree>[] pq = new PriorityQueue[2];
	static Queue<myTree> dead, spread;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		K = Integer.parseInt(splitedLine[2]);

		weight = new int[N][N];
		base = new int[N][N];
		pq[0] = new PriorityQueue<>();
		pq[1] = new PriorityQueue<>();
		dead = new ArrayDeque<>();
		spread = new ArrayDeque<>();

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				weight[i][j] = Integer.parseInt(splitedLine[j]);
				base[i][j] = 5;
			}
		}

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int x = Integer.parseInt(splitedLine[0]) - 1;
			int y = Integer.parseInt(splitedLine[1]) - 1;
			int age = Integer.parseInt(splitedLine[2]);
			pq[0].add(new myTree(x, y, age));
		}

		for (int t = 0; t < K; ++t) {
			// 봄
			doSpring(t);

			// 여름
			doSummer();

			// 가을
			doFall(t);

			// 겨울
			doWinter(t);
		}
		System.out.println(pq[K % 2].size());
	}

	private static void doSpring(int t) {
		int idx = t % 2;
		while (!pq[idx].isEmpty()) {
			myTree cur = pq[idx].poll();
			if (base[cur.x][cur.y] >= cur.age) {
				base[cur.x][cur.y] -= cur.age;
				cur.age += 1;
				pq[(idx + 1) % 2].add(cur);
				if (cur.age % 5 == 0)
					spread.add(cur);
			} else {
				dead.add(cur);
			}
		}
	}

	private static void doSummer() {
		while (!dead.isEmpty()) {
			myTree cur = dead.poll();
			base[cur.x][cur.y] += (cur.age / 2);
		}
	}

	private static void doFall(int t) {
		while (!spread.isEmpty()) {
			myTree cur = spread.poll();
			for (int i = 0; i < 8; ++i) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
					pq[(t + 1) % 2].add(new myTree(nextX, nextY, 1));
				}
			}
		}
	}

	private static void doWinter(int t) {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				base[i][j] += weight[i][j];
			}
		}
	}
}