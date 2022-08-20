import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[][] map;
	static String inputStr;
	static String[] splitedLine;
	final static int WHITE = 0;
	final static int BLACK = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; ++i) {
			inputStr = in.readLine();
			for (int j = 0; j < N; ++j) {
				map[i][j] = inputStr.charAt(j) - '0';
			}
		}

		// 로직
		if (isOneColor(map, N, WHITE)) {
			sb.append(0);
		} else if (isOneColor(map, N, BLACK)) {
			sb.append(1);
		} else {
			zFunction(N, map);
		}

		// 출력부
		System.out.println(sb);
	}

	private static void zFunction(int n, int[][] curMap) {
		if (n == 2) {
			sb.append("(");
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					sb.append(curMap[i][j]);
				}
			}
			sb.append(")");
			return;
		}
		int halfValue = n / 2;

		sb.append("(");
		// 1사분면
		int[][] testMap = new int[halfValue][halfValue];
		for (int i = 0; i < halfValue; ++i) {
			for (int j = 0; j < halfValue; ++j) {
				testMap[i][j] = curMap[i][j];
			}
		}
		if (isOneColor(testMap, halfValue, WHITE)) {
			sb.append(0);
		} else if (isOneColor(testMap, halfValue, BLACK)) {
			sb.append(1);
		} else {
			zFunction(halfValue, testMap);
		}

		// 2사분면
		testMap = new int[halfValue][halfValue];
		for (int i = 0; i < halfValue; ++i) {
			for (int j = 0; j < halfValue; ++j) {
				testMap[i][j] = curMap[i][j + halfValue];
			}
		}
		if (isOneColor(testMap, halfValue, WHITE)) {
			sb.append(0);
		} else if (isOneColor(testMap, halfValue, BLACK)) {
			sb.append(1);
		} else {
			zFunction(halfValue, testMap);
		}

		// 4사분면
		testMap = new int[halfValue][halfValue];
		for (int i = 0; i < halfValue; ++i) {
			for (int j = 0; j < halfValue; ++j) {
				testMap[i][j] = curMap[i + halfValue][j];
			}
		}
		if (isOneColor(testMap, halfValue, WHITE)) {
			sb.append(0);
		} else if (isOneColor(testMap, halfValue, BLACK)) {
			sb.append(1);
		} else {
			zFunction(halfValue, testMap);
		}
		
		// 3사분면
		testMap = new int[halfValue][halfValue];
		for (int i = 0; i < halfValue; ++i) {
			for (int j = 0; j < halfValue; ++j) {
				testMap[i][j] = curMap[i + halfValue][j + halfValue];
			}
		}
		if (isOneColor(testMap, halfValue, WHITE)) {
			sb.append(0);
		} else if (isOneColor(testMap, halfValue, BLACK)) {
			sb.append(1);
		} else {
			zFunction(halfValue, testMap);
		}

		sb.append(")");
	}

	static boolean isOneColor(int[][] map, int size, int color) {
		int count = 0;
		for (int[] row : map)
			for (int colData : row)
				if (colData == color)
					count++;
		if (size * size == count)
			return true;
		else
			return false;
	}
}
