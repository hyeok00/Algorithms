import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static char[][] map = new char[50][50];
	static int[][] isVisit = new int[50][50];

	static class Pair {
		int x;
		int y;

		public Pair() {

		}

		public Pair(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		int R = Integer.parseInt(splitedLine[0]);
		int C = Integer.parseInt(splitedLine[1]);

		Pair start = new Pair();
		Pair goal = new Pair();
		ArrayList<Pair> waterList = new ArrayList<>();
		for (int i = 0; i < R; ++i) {
			String inputLine = in.readLine();
			for (int j = 0; j < C; ++j) {
				map[i][j] = inputLine.charAt(j);
				if (map[i][j] == 'S') {
					start.x = i;
					start.y = j;
				}
				if (map[i][j] == 'D') {
					goal.x = i;
					goal.y = j;
				}
				if (map[i][j] == '*') {
					waterList.add(new Pair(i, j));
				}
			}
		}

		// 로직
		bfs(start, waterList, R, C);

		// 출력부
		if (isVisit[goal.x][goal.y] == 0)
			sb.append("KAKTUS");
		else
			sb.append(isVisit[goal.x][goal.y]-1);
		System.out.println(sb);
	}

	private static void bfs(Pair start, ArrayList<Pair> wl, int row, int col) {
		Queue<Pair> q = new LinkedList<>();
		for (Pair it : wl) {
			q.add(it);
			isVisit[it.x][it.y] = -1;
		}
		q.add(start);
		isVisit[start.x][start.y] = 1;

		int[] dx = { -1, 0, 1, 0 }; // 위쪽부터 시계방향
		int[] dy = { 0, 1, 0, -1 };// 위쪽부터 시계방향
		while (!q.isEmpty()) {
			Pair cur = q.peek();
			q.remove();

			for (int i = 0; i < dx.length; ++i) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];

				if (0 <= nextX && nextX < row && 0 <= nextY && nextY < col) {
					// 범위 내부만 체크
					if (isVisit[cur.x][cur.y] == -1) {
						// 물인 경우
						if (isVisit[nextX][nextY] == 0 && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'S')) {
							// 방문한 적이 없고, 빈칸이라면 물이 채워진다.
							isVisit[nextX][nextY] = isVisit[cur.x][cur.y];
							q.add(new Pair(nextX, nextY));
						}
					} else { // 고슴도치의 경우
						if (isVisit[nextX][nextY] == 0 && (map[nextX][nextY] == '.' || map[nextX][nextY] == 'D')) {
							// 방문한 적이 없고, 빈칸이라면 진행이 가능하다.
							isVisit[nextX][nextY] = isVisit[cur.x][cur.y] + 1;
							q.add(new Pair(nextX, nextY));
						}
					}
				}
			}
		}
	}
}
