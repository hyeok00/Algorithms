import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] map;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
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
		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		map = new int[N][M];

		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < N; ++i) {
			String line = in.readLine();
			for (int j = 0; j < M; ++j) {
				char c = line.charAt(j);
				if (c == '.') {
					map[i][j] = 0;
					q.add(new Point(i, j));
				} else
					map[i][j] = c - '0';
			}
		}

		int depth = -1;
		while (!q.isEmpty()) {
			depth++;
			int prevSize = q.size();
			for (int i = 0; i < prevSize; ++i) {
				Point cur = q.poll();
				for (int d = 0; d < 8; ++d) {
					int nextX = cur.x + dx[d];
					int nextY = cur.y + dy[d];
					if (isInRange(nextX, nextY)) {
						if (1 <= map[nextX][nextY] && map[nextX][nextY] <= 8) {
							map[nextX][nextY]--;
							if (map[nextX][nextY] == 0) {
								q.add(new Point(nextX, nextY));
							}
						}
					}
				}
			}
		}
		System.out.println(depth);
	}

	private static int check(int x, int y) {
		int result = 0;
		for (int d = 0; d < 8; ++d) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			if (isInRange(nextX, nextY) && map[nextX][nextY] == 0)
				result++;
		}
		return result;
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