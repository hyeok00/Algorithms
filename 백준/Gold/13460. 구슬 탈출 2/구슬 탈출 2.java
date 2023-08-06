import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static int N, M;
	static char[][] map;

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	static Point hole, red, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		map = new char[N][M];
		for (int i = 0; i < N; ++i) {
			String str = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					red = new Point(i, j);
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					blue = new Point(i, j);
					map[i][j] = '.';
				} else if (map[i][j] == 'O') {
					hole = new Point(i, j);
				} else {
				}
			}
		}

		Queue<Point> q = new ArrayDeque<>();
		q.add(red);
		q.add(blue);

		int depth = 1;
		while (!q.isEmpty()) {
			if (depth > 10)
				break;
			int size = q.size() / 2;
			while (size-- > 0) {
				Point curRed = q.poll();
				Point curBlue = q.poll();
				for (int d = 0; d < 4; ++d) {
					Point nextRed = moveBall(curRed, curBlue, d); // red 먼저 움직이기
					Point nextBlue = moveBall(curBlue, nextRed, d); // blue 움직이기

					if (!isIn(nextRed)) {
						nextRed = moveBall(nextRed, nextBlue, d); // blue가 움직여서 red가 더 움직여 질 수 있으므로 한번더
					}

					// 둘 다 이상 없다면 큐에 추가
					if (!isIn(nextRed) && !isIn(nextBlue)) {
						q.add(nextRed);
						q.add(nextBlue);
						continue;
					}
					// 특정 구슬이 들어갔다면 중단.
					boolean flag = false;
					if (isIn(nextBlue)) {
						flag = true;
						// nothing
					}
					if (isIn(nextRed) && flag == false) {
						System.out.println(depth);
						return;
					}
				}
			}
			depth++;
		}
		System.out.println(-1);
	}

	public static boolean isIn(Point p) {
		if (p.x == -1 && p.y == -1)
			return true;
		return false;
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	private static Point moveBall(Point p, Point another, int d) {
		int curX = p.x;
		int curY = p.y;
		int nextX = p.x;
		int nextY = p.y;
		while (true) {
			nextX = nextX + dx[d];
			nextY = nextY + dy[d];
			if (map[nextX][nextY] == '#' || (nextX == another.x && nextY == another.y)) {
				return new Point(curX, curY);
			} else if (map[nextX][nextY] == '.') {
				curX = nextX;
				curY = nextY;
			} else {
				return new Point(-1, -1);
			}
		}
	}
}