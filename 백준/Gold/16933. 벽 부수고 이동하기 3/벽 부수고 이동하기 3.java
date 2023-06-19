import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int N, M, K;
	static String[] splitedLine;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Pos {
		int x;
		int y;
		int count;

		public Pos(int first, int second, int count) {
			this.x = first;
			this.y = second;
			this.count = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		K = Integer.parseInt(splitedLine[2]);

		int[][] map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			String inputLine = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = inputLine.charAt(j) - '0';
			}
		}

		boolean isVisit[][][] = new boolean[K + 1][N][M];
		Queue<Pos> q = new ArrayDeque<>();
		isVisit[0][0][0] = true;

		q.add(new Pos(0, 0, 0));

		int depth = 0;
		boolean time = false;
		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			time = !time;
			while (size-- > 0) {
				Pos p = q.peek();
				if (p.x == N - 1 && p.y == M - 1) {
					System.out.println(depth);
					return;
				}

				if (time) {
					for (int i = 0; i < 4; ++i) {
						int nextX = p.x + dx[i];
						int nextY = p.y + dy[i];
						if (isInRange(nextX, nextY)) {
							if (map[nextX][nextY] == 0 && isVisit[p.count][nextX][nextY] == false) {
								isVisit[p.count][nextX][nextY] = true;
								q.add(new Pos(nextX, nextY, p.count));
							} else {
								if (p.count < K && isVisit[p.count + 1][nextX][nextY] == false) {
									isVisit[p.count + 1][nextX][nextY] = true;
									q.add(new Pos(nextX, nextY, p.count + 1));
								}
							}
						}
					}
					q.poll();
				} else {
					boolean check = false;
					for (int i = 0; i < 4; ++i) {
						int nextX = p.x + dx[i];
						int nextY = p.y + dy[i];
						if (isInRange(nextX, nextY)) {
							if (map[nextX][nextY] == 0 && isVisit[p.count][nextX][nextY] == false) {
								isVisit[p.count][nextX][nextY] = true;
								q.add(new Pos(nextX, nextY, p.count));
							} else {
								if (p.count < K && isVisit[p.count + 1][nextX][nextY] == false) {
									check = true;
								}
							}
						}
					}
					q.poll();
					if (check)
						q.add(p);
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M)
			return true;
		else
			return false;
	}
}