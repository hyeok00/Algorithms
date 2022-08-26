import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static int[][] map, weight;

	static class Pair implements Comparable<Pair> {
		int x, y;

		public Pair(int a, int b) {
			x = a;
			y = b;
		}

		public int compareTo(Pair o) {
			return map[this.x][this.y] - map[o.x][o.y];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int tc = 1;
		while (true) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0) // 종료
				break;

			map = new int[N][N];
			weight = new int[N][N];
			for (int i = 0; i < N; ++i) {
				String[] splitedLine = in.readLine().split(" ");
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(splitedLine[j]);
					weight[i][j] = Integer.MAX_VALUE;
				}
			}

			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };

			PriorityQueue<Pair> pq = new PriorityQueue<>();
			weight[0][0] = map[0][0];
			pq.add(new Pair(0, 0));

			boolean[][] visit = new boolean[N][N];
			while (!pq.isEmpty()) {
				Pair p = pq.poll();

				if (weight[p.x][p.y] < map[p.x][p.y]) {
					continue;
				}

				visit[p.x][p.y] = true;

				for (int i = 0; i < dx.length; ++i) {
					int nextX = p.x + dx[i];
					int nextY = p.y + dy[i];
					if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
						if (weight[nextX][nextY] > weight[p.x][p.y] + map[nextX][nextY]) {
							weight[nextX][nextY] = weight[p.x][p.y] + map[nextX][nextY];
							pq.add(new Pair(nextX, nextY));
						}
					}
				}
			}
			sb.append("Problem " + tc + ": " + weight[N - 1][N - 1] + "\n");
			tc++;
		}
		// 로직

		// 출력부
		System.out.println(sb);
	}
}