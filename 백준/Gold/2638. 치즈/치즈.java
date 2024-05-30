import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<int[]> cheese = new ArrayDeque<>();
	static Queue<int[]> air = new ArrayDeque<>();

	final static int AIR = 9;
	final static int CHEESE = 1;
	final static int NO_AIR = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);

		arr = new int[N][M];
		for (int i = 0; i < N; ++i) {
			inputs = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				if (i == 0 || j == 0 || i == N - 1 | j == M - 1) {
					arr[i][j] = AIR;
					air.add(new int[] {i, j});
					continue;
				}

				if (CHEESE == stoi(inputs[j])) {
					cheese.add(new int[] {i, j});
					arr[i][j] = CHEESE;
				}
			}
		}

		int time = 0;
		while (!cheese.isEmpty()) {
			airCirculation();
			meltCheese();
			time++;
		}

		System.out.println(time);
	}

	private static void airCirculation() {
		while (!air.isEmpty()) {
			int[] cur = air.poll();

			for (int d = 0; d < 4; ++d) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (isInRange(nx, ny) && arr[nx][ny] == NO_AIR) {
					arr[nx][ny] = AIR;
					air.add(new int[] {nx, ny});
				}
			}
		}
	}

	private static void meltCheese() {
		int size = cheese.size();
		List<int[]> list = new ArrayList<>();
		while (size-- > 0) {
			int[] cur = cheese.poll();

			int count = 0;
			for (int d = 0; d < 4; ++d) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (isInRange(nx, ny) && arr[nx][ny] == AIR)
					count++;
			}
			if (count >= 2)
				list.add(cur);
			else
				cheese.add(cur);
		}
		for (int[] pos : list) {
			arr[pos[0]][pos[1]] = AIR;
			air.add(pos);
		}
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}