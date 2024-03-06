import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
	static int R, C, N;
	static char[][] map;
	static int[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		R = stoi(inputs[0]);
		C = stoi(inputs[1]);
		map = new char[R][C];

		for (int i = 0; i < R; ++i)
			map[i] = in.readLine().toCharArray();

		N = stoi(in.readLine());
		inputs = in.readLine().split(" ");
		for (int i = 0; i < N; ++i) {
			visit = new int[R][C];
			int h = stoi(inputs[i]);
			throwBar(h, i % 2);
			int maxGroupNum = assignGroup();
			fallDown(maxGroupNum);
		}
		print();
	}

	private static int assignGroup() {
		int count = 0;
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == 'x' && visit[i][j] == 0) {
					count++;
					visit[i][j] = count;
					search(i, j);
				}
			}
		}
		return count;
	}

	private static void search(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {startX, startY});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];

			for (int d = 0; d < 4; ++d) {
				int nextX = x + dx[d];
				int nextY = y + dy[d];
				if (isInRange(nextX, nextY) && map[nextX][nextY] == 'x' && visit[nextX][nextY] == 0) {
					visit[nextX][nextY] = visit[x][y];
					q.add(new int[] {nextX, nextY});
				}
			}
		}
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C)
			return true;
		return false;
	}

	private static void fallDown(int maxGroupNum) {
		int expect = (maxGroupNum * (maxGroupNum + 1)) / 2;
		int sum = 0;
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < C; ++i)
			s.add(visit[R - 1][i]);
		sum = s.stream().mapToInt(Integer::intValue).sum();

		// 모든 그룹이 바닥에 붙어있다.
		if (sum == expect)
			return;

		int groupId = expect - sum;
		while (true) {
			for (int i = R - 1; i >= 0; i--) {
				for (int j = C - 1; j >= 0; j--) {
					if (visit[i][j] != groupId)
						continue;

					// 어딘가와 붙었다면 종료.
					if (i + 1 == R || (visit[i + 1][j] != groupId && visit[i + 1][j] > 0))
						return;

				}
			}
			// 해당 그룹을 한 칸씩 모두 내린다.
			for (int i = R - 1; i >= 0; i--) {
				for (int j = C - 1; j >= 0; j--) {
					if (visit[i][j] != groupId)
						continue;

					map[i + 1][j] = map[i][j];
					map[i][j] = '.';
					visit[i + 1][j] = visit[i][j];
					visit[i][j] = 0;

				}
			}
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void throwBar(int h, int type) {
		if (type == 0) {
			// 좌 -> 우
			for (int i = 0; i < C; ++i) {
				if (map[R - h][i] == 'x') {
					map[R - h][i] = '.';
					break;
				}
			}
		} else {
			// 우 -> 좌
			for (int i = C - 1; i >= 0; --i) {
				if (map[R - h][i] == 'x') {
					map[R - h][i] = '.';
					break;
				}
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}