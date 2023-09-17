import java.io.BufferedReader;
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

	static int N, M;
	static int[][] arr, result;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		arr = new int[N][M];
		result = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < M; ++j)
				result[i][j] = -1;

		Queue<Point> q = new ArrayDeque<>();
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				arr[i][j] = stoi(splitedLine[j]);
				if (arr[i][j] == 2) {
					q.add(new Point(i, j));
					result[i][j] = 0;
					visit[i][j] = true;
				} else if (arr[i][j] == 0)
					result[i][j] = 0;
			}
		}

		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; ++d) {
				int nextX = cur.x + dx[d];
				int nextY = cur.y + dy[d];
				if (isInRange(nextX, nextY) && !isVisit(nextX, nextY) && arr[nextX][nextY] != 0) {
					visit[nextX][nextY] = true;
					result[nextX][nextY] = result[cur.x][cur.y] + 1;
					q.add(new Point(nextX, nextY));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean isVisit(int nextX, int nextY) {
		return visit[nextX][nextY];
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}