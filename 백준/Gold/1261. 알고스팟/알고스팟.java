import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] map, dist;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Pair {
		int x;
		int y;

		Pair(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");
		M = Integer.parseInt(splitedLine[0]);
		N = Integer.parseInt(splitedLine[1]);

		map = new int[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String s = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = s.charAt(j) - '0';
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		Queue<Pair> q = new ArrayDeque<>();
		dist[0][0] = 0;
		q.add(new Pair(0, 0));
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int i = 0; i < 4; ++i) {
				int nextX = p.x + dx[i];
				int nextY = p.y + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
					if (map[nextX][nextY] == 0) {
						if (dist[nextX][nextY] > dist[p.x][p.y]) {
							dist[nextX][nextY] = dist[p.x][p.y];
							q.add(new Pair(nextX, nextY));
						}
					} else {
						if (dist[nextX][nextY] > dist[p.x][p.y] + 1) {
							dist[nextX][nextY] = dist[p.x][p.y] + 1;
							q.add(new Pair(nextX, nextY));
						}
					}
				}
			}
		}
		System.out.println(dist[N - 1][M - 1]);
	}

}