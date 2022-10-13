import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	static class Pos {
		int first;
		int second;
		int count;

		public Pos(int first, int second, int count) {
			this.first = first;
			this.second = second;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		int[][] map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String inputLine = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = inputLine.charAt(j) - '0';
			}
		}

		int isVisit[][][] = new int[N][M][2];
		Queue<Pos> q = new ArrayDeque<>();
		isVisit[0][0][0] = 1;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		q.add(new Pos(0, 0, 0));
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			if (cur.first == N - 1 && cur.second == M - 1) {
				break;
			}
			for (int i = 0; i < 4; ++i) {
				int nextX = cur.first + dx[i];
				int nextY = cur.second + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
					if (map[nextX][nextY] == 0 && isVisit[nextX][nextY][cur.count] == 0) {
						isVisit[nextX][nextY][cur.count] = isVisit[cur.first][cur.second][cur.count] + 1;
						q.add(new Pos(nextX, nextY, cur.count));
					}
					if (map[nextX][nextY] == 1 && cur.count == 0) {
						isVisit[nextX][nextY][1] = isVisit[cur.first][cur.second][0] + 1;
						q.add(new Pos(nextX, nextY, cur.count + 1));
					}
				}
			}
		}
		int checkValue1 = isVisit[N - 1][M - 1][0];
		int checkValue2 = isVisit[N - 1][M - 1][1];
		if (checkValue1 == 0 || checkValue2 == 0) {
			if (checkValue1 == 0 && checkValue2 == 0)
				sb.append("-1");
			else if (checkValue1 > 0)
				sb.append(checkValue1);
			else if (checkValue2 > 0)
				sb.append(checkValue2);
		} else {
			sb.append(Math.min(checkValue1, checkValue2));
		}
		System.out.println(sb);
	}
}