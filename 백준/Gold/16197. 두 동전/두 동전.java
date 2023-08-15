import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	// static StringBuilder sb;
	static int N, M;
	static char[][] map;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	final static char COIN = 'o';
	final static char BLANK = '.';
	final static char WALL = '#';

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
		// sb = new StringBuilder();
		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		map = new char[N + 2][M + 2];
		for (int i = 0; i <= N + 1; ++i) {
			for (int j = 0; j <= M + 1; ++j) {
				map[i][j] = BLANK;
			}
		}
		Queue<Point> q = new ArrayDeque<>();
		for (int i = 1; i <= N; ++i) {
			String line = in.readLine();
			for (int j = 1; j <= M; ++j) {
				map[i][j] = line.charAt(j - 1);
				if (map[i][j] == COIN)
					q.add(new Point(i, j));
			}
		}

		int depth = 1;
		while (!q.isEmpty()) {
			if (depth > 10)
				break;

			int size = q.size() / 2;
			while (size-- > 0) {
				Point first = q.poll(); // 첫번째 코인
				Point second = q.poll(); // 두번째 코인

				for (int d = 0; d < 4; ++d) {
					Point firstCoinNext = getNextPosition(first, d);
					Point secondCoinNext = getNextPosition(second, d);

					boolean resultFirst = isInBoard(firstCoinNext.x, firstCoinNext.y);
					boolean resultSecond = isInBoard(secondCoinNext.x, secondCoinNext.y);
					if (resultFirst == true && resultSecond == true) {
						// 동전 2개가 모두 보드 안쪽에 위치하는 경우
						q.add(firstCoinNext);
						q.add(secondCoinNext);
					} else if ((resultFirst == true && resultSecond == false)
						|| (resultFirst == false && resultSecond == true)) {
						// 동전 2개 중 1개가 보드 밖으로 나간 경우
						System.out.println(depth);
						return;
					} else {
						// 동전 2개가 모두 떨어진 경우
						// do nothing
					}
				}
			}
			depth++;
		}
		System.out.println(-1);
	}

	private static Point getNextPosition(Point p, int d) {
		int nextX = p.x + dx[d];
		int nextY = p.y + dy[d];
		if (map[nextX][nextY] != WALL && isInRange(nextX, nextY))
			return new Point(nextX, nextY);
		else
			return new Point(p.x, p.y);
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x <= N + 1 && 0 <= y && y <= M + 1)
			return true;
		return false;
	}

	private static boolean isInBoard(int x, int y) {
		if (1 <= x && x <= N && 1 <= y && y <= M)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}