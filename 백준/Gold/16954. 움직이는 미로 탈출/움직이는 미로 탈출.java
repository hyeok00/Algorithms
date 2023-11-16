import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	final static char BLANK = '.';
	final static char WALL = '#';
	final static int SIZE = 8;

	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		map = new char[SIZE][SIZE];

		// 입력
		for (int i = 0; i < SIZE; ++i)
			map[i] = in.readLine().toCharArray();

		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(SIZE - 1, 0));

		while (!q.isEmpty()) {
			int size = q.size();
			boolean visit[][] = new boolean[SIZE][SIZE];

			while (size-- > 0) {
				Point p = q.poll();
				if(p.x == 0 && p.y == SIZE-1){
					System.out.println(1);
					return;
				}
				if (map[p.x][p.y] == BLANK) {
					for (int d = 0; d < 9; ++d) {
						int nextX = p.x + dx[d];
						int nextY = p.y + dy[d];
						if (isInRange(nextX, nextY) && visit[nextX][nextY] == false && map[nextX][nextY] == BLANK) {
							visit[nextX][nextY] = true;
							q.add(new Point(nextX, nextY));
						}
					}
				}
			}
			// 캐릭터의 이동이 모두 완료됨.
			// 벽을 내려보자.
			moveWalls();
		}
			System.out.println(0);
	}

	private static void moveWalls() {
		for (int i = SIZE - 2; i >= 0; --i) {
			for (int j = 0; j < SIZE; ++j) {
				map[i + 1][j] = map[i][j];
			}
		}
		for (int i = 0; i < SIZE; ++i) {
			map[0][i] = BLANK;
		}
	}
	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < SIZE && 0 <= y && y < SIZE)
			return true;
		return false;
	}
}