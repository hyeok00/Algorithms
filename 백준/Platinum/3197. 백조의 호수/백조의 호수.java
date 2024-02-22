import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int R, C;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

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

		String[] inputs = in.readLine().split(" ");
		R = stoi(inputs[0]);
		C = stoi(inputs[1]);

		map = new char[R][C];
		visit = new boolean[R][C];

		Queue<Point>[] swanQueue = new Queue[2];
		Queue<Point>[] waterQueue = new Queue[2];
		for (int i = 0; i < 2; ++i) {
			swanQueue[i] = new ArrayDeque<>();
			waterQueue[i] = new ArrayDeque<>();
		}

		Point start = new Point(0, 0);
		for (int i = 0; i < R; ++i) {
			String input = in.readLine();
			for (int j = 0; j < C; ++j) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'L') {
					start.x = i;
					start.y = j;
					waterQueue[0].add(new Point(i, j));
				}
				if (map[i][j] == '.') {
					waterQueue[0].add(new Point(i, j));
				}
			}
		}
		swanQueue[0].add(start);
		visit[start.x][start.y] = true;

		int day = 0;
		boolean flag = false;
		while (!flag) {
			int type = day % 2;

			// 백조가 움직여본다.
			while (!swanQueue[type].isEmpty()) {
				Point cur = swanQueue[type].poll();

				for (int d = 0; d < 4; ++d) {
					int nextX = cur.x + dx[d];
					int nextY = cur.y + dy[d];
					if (!isInRange(nextX, nextY))
						continue;

					if (visit[nextX][nextY])
						continue;

					visit[nextX][nextY] = true;
					if (map[nextX][nextY] == 'X')
						swanQueue[(type + 1) % 2].add(new Point(nextX, nextY));
					else if (map[nextX][nextY] == '.') {
						swanQueue[type].add(new Point(nextX, nextY));
					} else if (map[nextX][nextY] == 'L') {
						flag = true;
						break;
					}
				}
			}

			if (flag)
				break;

			// 얼음이 녹는다.
			while (!waterQueue[type].isEmpty()) {
				Point cur = waterQueue[type].poll();
				for (int d = 0; d < 4; ++d) {
					int nextX = cur.x + dx[d];
					int nextY = cur.y + dy[d];
					if (!isInRange(nextX, nextY))
						continue;
					if (map[nextX][nextY] == 'X') {
						map[nextX][nextY] = '.';
						waterQueue[(type + 1) % 2].add(new Point(nextX, nextY));
					}
				}
			}
			day++;
		}
		System.out.println(day);
	}

	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C)
			return true;
		return false;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}