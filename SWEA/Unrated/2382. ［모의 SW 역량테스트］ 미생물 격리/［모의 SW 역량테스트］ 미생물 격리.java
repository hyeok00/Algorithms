import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static class Info {
		int num;
		int dir;

		Info(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}

	static int N, M, K, numRemain;
	static int[][] arr, newArr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static StringBuilder sb = new StringBuilder();
	static Info[] infos;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(in.readLine());

		for (int tc = 0; tc < T; ++tc) {
			String[] inputs = in.readLine().split(" ");
			N = stoi(inputs[0]);
			M = stoi(inputs[1]);
			K = stoi(inputs[2]);
			arr = new int[N][N];
			infos = new Info[K];
			for (int i = 0; i < K; ++i) {
				inputs = in.readLine().split(" ");
				int x = stoi(inputs[0]);
				int y = stoi(inputs[1]);
				int num = stoi(inputs[2]);
				int dir = stoi(inputs[3]) - 1;

				infos[i] = new Info(num, dir);
				arr[x][y] = i + 1;
			}

			int result = getTotalSum();
			sb.append("#").append(tc + 1).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static int getTotalSum() {
		numRemain = K;

		for (int tc = 0; tc < M; ++tc) {
			if (numRemain == 0)
				break;
			newArr = new int[N][N];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					newArr[i][j] = simulation(i, j);
					// if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
					// 	int idx = newArr[i][j];
					// 	if (idx == 0)
					// 		continue;
					// 	infos[idx - 1].num = infos[idx - 1].num / 2;
					// 	infos[idx - 1].dir = (infos[idx - 1].dir + 2) % 4;
					// }
				}
			}
			for (int i = 0; i < N; ++i) {
				if (newArr[i][0] != 0) {
					int idx = newArr[i][0] - 1;
					infos[idx].num = infos[idx].num / 2;
					infos[idx].dir = getReverseDir(infos[idx].dir);
					if (infos[idx].num == 0)
						newArr[i][0] = 0;
				}
				if (newArr[i][N - 1] != 0) {
					int idx = newArr[i][N - 1] - 1;
					infos[idx].num = infos[idx].num / 2;
					infos[idx].dir = getReverseDir(infos[idx].dir);
					if (infos[idx].num == 0)
						newArr[i][N - 1] = 0;
				}
				if (newArr[0][i] != 0) {
					int idx = newArr[0][i] - 1;
					infos[idx].num = infos[idx].num / 2;
					infos[idx].dir = getReverseDir(infos[idx].dir);
					if (infos[idx].num == 0)
						newArr[0][i] = 0;
				}
				if (newArr[N - 1][i] != 0) {
					int idx = newArr[N - 1][i] - 1;
					infos[idx].num = infos[idx].num / 2;
					infos[idx].dir = getReverseDir(infos[idx].dir);
					if (infos[idx].num == 0)
						newArr[N - 1][i] = 0;
				}
			}
			arr = newArr;
		}

		int sum = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int idx = arr[i][j];
				if (idx == 0)
					continue;
				sum += infos[idx - 1].num;
			}
		}
		return sum;
	}

	private static int getReverseDir(int dir) {
		if (dir == 0)
			return 1;
		if (dir == 1)
			return 0;
		if (dir == 2)
			return 3;
		if (dir == 3)
			return 2;
		return -1;
	}

	private static int simulation(int x, int y) {
		int maxIdx = -1;
		int maxNum = -1;
		int sum = 0;
		int count = 0;
		for (int d = 0; d < 4; ++d) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (!isInRange(nx, ny))
				continue;
			int next = arr[nx][ny] - 1;

			// 옆이 빈자리였다 생략.
			if (next == -1)
				continue;

			// 옆 자리의 미생물이 현재 위치로 이동하는 것이 아니라면 생략
			int newDir = getReverseDir(infos[next].dir);
			if (newDir != d)
				continue;

			if (maxNum < infos[next].num) {
				maxNum = infos[next].num;
				maxIdx = next;
			}
			sum += infos[next].num;
			count++;
		}
		if (maxIdx == -1)
			return 0;
		infos[maxIdx].num = sum;
		numRemain -= (count - 1);

		return maxIdx + 1;
	}

	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}