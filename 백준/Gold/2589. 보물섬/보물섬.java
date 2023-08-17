import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static int N, M;
	static char[][] map;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	final static char SEA = 'W';
	final static char LAND = 'L';

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		map = new char[N][M];
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < N; ++i) {
			map[i] = in.readLine().toCharArray();
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == LAND && isCandidate(i, j)) {
					q.add(new Point(i, j));
				}
			}
		}

		int result = 0;
		while (!q.isEmpty()) {
			Point p = q.poll();
			result = Math.max(result, bfs(p));
		}
		System.out.println(result);
	}

	private static int bfs(Point p) {
		boolean[][] visit = new boolean[N][M];
		visit[p.x][p.y] = true;
		Queue<Point> q = new ArrayDeque<>();
		q.add(p);

		int depth = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				for (int d = 0; d < 4; ++d) {
					int nextX = cur.x + dx[d];
					int nextY = cur.y + dy[d];
					if (isInRange(nextX, nextY) &&
						map[nextX][nextY] == LAND &&
						visit[nextX][nextY] == false) {
						visit[nextX][nextY] = true;
						q.add(new Point(nextX, nextY));
					}
				}
			}
			depth++;
		}
		return depth-1;
	}

	private static boolean isCandidate(int x, int y) {
		int count = 0;
		for (int d = 0; d < 4; ++d) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			if (isInRange(nextX, nextY) && map[nextX][nextY] == LAND)
				count++;
		}
		return count <= 2 ? true : false;
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}