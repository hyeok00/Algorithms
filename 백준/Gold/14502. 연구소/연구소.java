import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int N, M, result;
	static int[] combArr, val;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	final static int NUM_WALL = 3;
	final static int VIRUS = 2;
	final static int BLANK = 0;
	final static int WALL = 1;

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}
		int size = N * M;
		val = new int[size];
		for (int i = 0; i < size; ++i) {
			val[i] = i + 1;
		}
		combArr = new int[NUM_WALL];
		result = Integer.MIN_VALUE;
		comb(0, 0);
		System.out.println(result);
	}

	public static void comb(int start, int depth) {
		if (depth == NUM_WALL) {
			int[][] testMap = new int[N][M];
			copyArr(map, testMap);
			for (int it : combArr) {
				int x = it / M;
				int y = it % M;
				if (testMap[x][y] != BLANK) {
					return;
				}
				testMap[x][y] = WALL;
			}
			search(testMap);
			return;
		}
		for (int i = start; i < N * M; ++i) {
			combArr[depth] = i;
			comb(i + 1, depth + 1);
		}

	}

	private static void search(int[][] testMap) {
		Queue<Pair> q = new LinkedList<>();

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (testMap[i][j] == VIRUS)
					q.add(new Pair(i, j));

			}
		}

		while (!q.isEmpty()) {
			Pair temp = q.poll();
			for (int i = 0; i < dx.length; ++i) {
				int nextX = temp.first + dx[i];
				int nextY = temp.second + dy[i];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
					if (testMap[nextX][nextY] == BLANK) {
						testMap[nextX][nextY] = VIRUS;
						q.add(new Pair(nextX, nextY));
					}

				}
			}
		}

		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (testMap[i][j] == BLANK)
					++count;
			}
		}

		if (count > result)
			result = count;
	}

	public static void copyArr(int[][] src, int[][] des) {
		int len = src.length;
		int innerLen = src[0].length;

		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < innerLen; ++j) {
				des[i][j] = src[i][j];
			}
		}
	}
}
