import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	static StringBuilder sb;
	static int N, NUM_CONNECTED_CORE, MIN_DISTANCE;
	static int[] combArr;
	static int[][] map;
	static ArrayList<Pair> cl;
	static boolean flag;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

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
		//System.setIn(new FileInputStream("sample_input2.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		// 입력부
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(in.readLine());

			map = new int[N][N];
			cl = new ArrayList<>();

			for (int i = 0; i < N; ++i) {
				String[] splitedLine = in.readLine().split(" ");
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(splitedLine[j]);
					if (map[i][j] == 1 && 0 < i && i < N - 1 && 0 < j && j < N - 1) {
						cl.add(new Pair(i, j));
					}
				}
			}

			// 하나씩 진행
			for (int i = cl.size(); i > 0; --i) {
				MIN_DISTANCE = Integer.MAX_VALUE;
				flag = false;
				combArr = new int[i];
				comb(0, 0, i);
				if (flag) // 완성된 경우
					break;
			}
			sb.append("#" + tc + " " + MIN_DISTANCE + "\n");
		}

		// 출력부
		System.out.println(sb);
	}

	static void comb(int start, int depth, int r) {
		if (r == depth) {
			//System.out.println(Arrays.toString(combArr));
			simulate(map, 0, r, 0);
			return;
		}
		for (int i = start; i < cl.size(); ++i) {
			combArr[depth] = i;
			comb(i + 1, depth + 1, r);
		}
	}

	static void simulate(int[][] inmap, int start, int end, int dist) {
		if (start == end) { // 모든 조합이 완성된 경우
			// 연결이 완성된 경우
//			System.out.println("거리: "+ dist);
//			for (int i = 0; i < N; ++i) {
//				for (int j = 0; j < N; ++j) {
//					System.out.print(inmap[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			flag = true;
			if (MIN_DISTANCE > dist)
				MIN_DISTANCE = dist;
			return;
		}
		// 위쪽부터 시계방향 확인
		int curCore = combArr[start];
		int curX = cl.get(curCore).x;
		int curY = cl.get(curCore).y;
		int nextX = curX;
		int nextY = curY;

		int count = 0;
		// 위쪽으로 쭉 뻗을 수 있는지 확인
		if (checkTop(inmap, cl.get(curCore))) {
			int[][] testMap = new int[N][N];
			deepCopy(inmap, testMap);
			count = 0;
			testMap[curX][curY]=curCore+2;
			while (nextX > 0) {
				nextX = nextX + dx[0];
				testMap[nextX][nextY] = curCore+2;
				count++;
			}
			// 위쪽 시뮬 결과
			simulate(testMap, start + 1, end, dist + count);
		}

		// 우
		nextX = curX;
		nextY = curY;
		if (checkRight(inmap, cl.get(curCore))) {
			int[][] testMap = new int[N][N];
			deepCopy(inmap, testMap);
			count = 0;
			testMap[curX][curY]=curCore+2;
			while (nextY < N - 1) {
				nextY = nextY + dy[1];
				testMap[nextX][nextY] = curCore+2;
				count++;
			}
			simulate(testMap, start + 1, end, dist + count);
		}

		// 하
		nextX = curX;
		nextY = curY;
		if (checkBottom(inmap, cl.get(curCore))) {
			int[][] testMap = new int[N][N];
			deepCopy(inmap, testMap);
			count = 0;
			testMap[curX][curY]=curCore+2;
			while (nextX < N - 1) {
				nextX = nextX + dx[2];
				testMap[nextX][nextY] = curCore+2;
				count++;
			}
			simulate(testMap, start + 1, end, dist + count);
		}

		// 좌
		nextX = curX;
		nextY = curY;
		if (checkLeft(inmap, cl.get(curCore))) {
			int[][] testMap = new int[N][N];
			deepCopy(inmap, testMap);
			count = 0;
			testMap[curX][curY]=curCore+2;
			while (nextY > 0) {
				nextY = nextY + dy[3];
				testMap[nextX][nextY] = curCore+2;
				count++;
			}
			simulate(testMap, start + 1, end, dist + count);
		}

	}

	static void deepCopy(int[][] src, int[][] des) {
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				des[i][j] = src[i][j];
	}

	static boolean checkTop(int[][] testMap, Pair p) {
		int curX = p.x;
		int curY = p.y;
		int nextX = curX;
		int nextY = curY;
		while (nextX > 0) {
			nextX = nextX + dx[0];
			if (testMap[nextX][nextY] >= 1)
				return false;
		}
		return true;
	}

	static boolean checkBottom(int[][] testMap, Pair p) {
		int curX = p.x;
		int curY = p.y;
		int nextX = curX;
		int nextY = curY;
		while (nextX < N - 1) {
			nextX = nextX + dx[2];
			if (testMap[nextX][nextY] >= 1)
				return false;
		}
		return true;
	}

	static boolean checkLeft(int[][] testMap, Pair p) {
		int curX = p.x;
		int curY = p.y;
		int nextX = curX;
		int nextY = curY;
		while (nextY > 0) {
			nextY = nextY + dy[3];
			if (testMap[nextX][nextY] >= 1)
				return false;
		}
		return true;
	}

	static boolean checkRight(int[][] testMap, Pair p) {
		int curX = p.x;
		int curY = p.y;
		int nextX = curX;
		int nextY = curY;
		while (nextY < N - 1) {
			nextY = nextY + dy[1];
			if (testMap[nextX][nextY] >= 1)
				return false;
		}
		return true;
	}
}