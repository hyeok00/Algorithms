import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static StringBuilder sb;
	static int N, M, K, RESULT;
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static String inputStr;
	static String[] splitedLine;
	final static int WALL = 6;
	final static int INSIDE = -1;
	final static int OUTSIDE = 0;
	static ArrayList<Pair> pairArr;

	static class Pair {
		int x;
		int y;
		int type;

		public Pair(int a, int b, int c) {
			x = a;
			y = b;
			type = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]); // 가로
		M = Integer.parseInt(splitedLine[1]); // 세로
		K = 0; // 전체 카메라 대수
		pairArr = new ArrayList<>();
		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				int value = Integer.parseInt(splitedLine[j]);
				map[i][j] = value;
				if (isCCTV(value)) {
					pairArr.add(new Pair(i, j, value));
					++K;
				}
			}
		}

		// 로직
		RESULT = Integer.MAX_VALUE;
		simulation(map, 0);
		sb.append(RESULT);

		// 출력부
		System.out.println(sb);
	}

	public static void simulation(int[][] testMap, int depth) {
		if (depth == K) {
			int curCount = getResult(testMap);
			if (RESULT > curCount)
				RESULT = curCount;
			return;
		}
		int s_x = pairArr.get(depth).x;
		int s_y = pairArr.get(depth).y;
		int cctvType = pairArr.get(depth).type;
		int[][] curTestMap = new int[N][M];
		switch (cctvType) {
		case 1:
			for (int i = 0; i < 4; ++i) {
				for (int p = 0; p < N; ++p) {
					for (int q = 0; q < M; ++q) {
						curTestMap[p][q] = testMap[p][q];
					}
				}
				search(s_x, s_y, curTestMap, i);
				simulation(curTestMap, depth + 1);
			}
			break;
		case 2:
			for (int i = 0; i < 2; ++i) {
				for (int p = 0; p < N; ++p) {
					for (int q = 0; q < M; ++q) {
						curTestMap[p][q] = testMap[p][q];
					}
				}
				search(s_x, s_y, curTestMap, i);
				search(s_x, s_y, curTestMap, i + 2);
				simulation(curTestMap, depth + 1);
			}
			break;
		case 3:
			for (int i = 0; i < 4; ++i) {
				for (int p = 0; p < N; ++p) {
					for (int q = 0; q < M; ++q) {
						curTestMap[p][q] = testMap[p][q];
					}
				}
				search(s_x, s_y, curTestMap, i % 4);
				search(s_x, s_y, curTestMap, (i + 1) % 4);
				simulation(curTestMap, depth + 1);
			}
			break;
		case 4:
			for (int i = 0; i < 4; ++i) {
				for (int p = 0; p < N; ++p) {
					for (int q = 0; q < M; ++q) {
						curTestMap[p][q] = testMap[p][q];
					}
				}
				search(s_x, s_y, curTestMap, i % 4);
				search(s_x, s_y, curTestMap, (i + 1) % 4);
				search(s_x, s_y, curTestMap, (i + 2) % 4);
				simulation(curTestMap, depth + 1);
			}
			break;
		case 5:
			for (int p = 0; p < N; ++p) {
				for (int q = 0; q < M; ++q) {
					curTestMap[p][q] = testMap[p][q];
				}
			}
			searchFive(s_x, s_y, curTestMap);
			simulation(curTestMap, depth + 1);
			break;
		default:
			break;
		}
	}

	public static int getResult(int[][] arr) {
		int count = 0;
		for (int[] row : arr) {
			for (int colData : row) {
				if (colData == OUTSIDE)
					++count;
			}
		}
		return count;
	}

	public static boolean isCCTV(int n) {
		if (1 <= n && n <= 5)
			return true;
		else
			return false;
	}

	public static void search(int s_x, int s_y, int[][] testMap, int dir) {
		int nextX = s_x;
		int nextY = s_y;
		while (true) {
			nextX = nextX + dx[dir];
			nextY = nextY + dy[dir];
			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
				if (testMap[nextX][nextY] == WALL)
					break;
				if (isCCTV(testMap[nextX][nextY]))
					continue;
				testMap[nextX][nextY] = INSIDE;
			} else
				break;
		}
	}

	public static void searchFive(int s_x, int s_y, int[][] testMap) {
		for (int i = 0; i < dx.length; ++i) {
			int nextX = s_x;
			int nextY = s_y;
			while (true) {
				nextX = nextX + dx[i];
				nextY = nextY + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
					if (testMap[nextX][nextY] == WALL)
						break;
					if (isCCTV(testMap[nextX][nextY]))
						continue;
					testMap[nextX][nextY] = INSIDE;
				} else
					break;
			}
		}
	}
}