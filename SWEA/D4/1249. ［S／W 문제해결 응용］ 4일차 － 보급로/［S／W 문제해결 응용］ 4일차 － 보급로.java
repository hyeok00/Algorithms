import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
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
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(in.readLine());
			if (N == 0) // 종료
				break;

			map = new int[N][N];
			weight = new int[N][N];
			for (int i = 0; i < N; ++i) {
				String inLine = in.readLine();
				for (int j = 0; j < N; ++j) {
					map[i][j] = inLine.charAt(j)-'0';
					weight[i][j] = Integer.MAX_VALUE;
				}
			}

            
		// 로직
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
			sb.append("#" + tc +" " + weight[N - 1][N - 1] + "\n");
		}

		// 출력부
		System.out.println(sb);
	}
}