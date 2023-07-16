import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static char[][] map;
	static int visit[][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static class Pair {
		int x;
		int y;
		char type;

		Pair(int a, int b, char c) {
			x = a;
			y = b;
			type = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String[] splitedLine = in.readLine().split(" ");
			M = Integer.parseInt(splitedLine[0]);
			N = Integer.parseInt(splitedLine[1]);

			map = new char[N][M];
			visit = new int[N][M];

			for (int i = 0; i < N; ++i) {
				map[i] = in.readLine().toCharArray();
			}
			sb.append(bfs()).append("\n");
		}
		System.out.println(sb);
	}

	private static String bfs() {
		Pair start = getStart();
		List<Pair> fires = getFires();
		Queue<Pair> q = new ArrayDeque<>();
		q.add(start);
		fires.stream().forEach((e) -> {
			visit[e.x][e.y] = -1;
			q.add(e);
		});
		visit[start.x][start.y] = 1;
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			for (int d = 0; d < 4; ++d) {
				int nextX = cur.x + dx[d];
				int nextY = cur.y + dy[d];
				if (!isInRange(nextX, nextY) && cur.type == '@' && visit[cur.x][cur.y] != -1) {
					return Integer.toString(visit[cur.x][cur.y]);
				}

				if (isInRange(nextX, nextY)) {
					if (cur.type == '@') { // 상근
						if (visit[nextX][nextY] == 0 && map[nextX][nextY] == '.') {
							visit[nextX][nextY] = visit[cur.x][cur.y] + 1;
							q.add(new Pair(nextX, nextY, cur.type));
						}
					} else { // 불
						if (visit[nextX][nextY] != -1 && map[nextX][nextY] != '#') {
							visit[nextX][nextY] = -1;
							q.add(new Pair(nextX, nextY, cur.type));
						}
					}
				}
			}
		}
		return "IMPOSSIBLE";
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M)
			return true;
		return false;
	}

	private static Pair getStart() {
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == '@')
					return new Pair(i, j, '@');
			}
		return null;
	}

	private static List<Pair> getFires() {
		List<Pair> result = new ArrayList<>();
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == '*')
					result.add(new Pair(i, j, '*'));
			}
		return result;
	}

}