import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int I;
	static int[][] map, visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(in.readLine());

		for (int tc = 0; tc < T; ++tc) {
			I = stoi(in.readLine());
			String[] inputs = in.readLine().split(" ");
			int sx = stoi(inputs[0]);
			int sy = stoi(inputs[1]);
			inputs = in.readLine().split(" ");
			int ex = stoi(inputs[0]);
			int ey = stoi(inputs[1]);
			int count = getMoveCount(sx, sy, ex, ey);
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

	private static int getMoveCount(int sx, int sy, int ex, int ey) {
		map = new int[I][I];
		visit = new int[I][I];

		Queue<int[]> q = new ArrayDeque<>();
		visit[sx][sy] = 1;
		q.add(new int[] {sx, sy});
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			if (cx == ex && cy == ey)
				break;
			for (int d = 0; d < 8; ++d) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				if (!isInRange(nx, ny))
					continue;
				if (visit[nx][ny] > 0)
					continue;
				visit[nx][ny] = visit[cx][cy] + 1;
				q.add(new int[] {nx, ny});
			}
		}

		return visit[ex][ey] - 1;
	}

	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < I && 0 <= y && y < I)
			return true;
		return false;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}