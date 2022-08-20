import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int R, C, RESULT;
	static int[] dx = { -1, 0, 1 }; // 우상, 우, 우하
	static int[] dy = { 1, 1, 1 };
	static char[][] map;
	static String inputStr;
	static String[] splitedLine;
	final static char BLANK = '.';
	final static char WALL = 'x';

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		splitedLine = in.readLine().split(" ");
		R = Integer.parseInt(splitedLine[0]);
		C = Integer.parseInt(splitedLine[1]);
		map = new char[R][C];

		for (int i = 0; i < R; ++i) {
			inputStr = in.readLine();
			for (int j = 0; j < C; ++j) {
				map[i][j] = inputStr.charAt(j);
			}
		}

		// 로직
		for (int i = 0; i < R; ++i)
			search(i, 0);
		sb.append(RESULT);

		// 출력부
		System.out.println(sb);
	}

	public static boolean search(int x, int y) {
		if (y == C - 1) {
			RESULT++;
			return true;
		}
		for (int i = 0; i < dx.length; ++i) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && map[nextX][nextY] == BLANK) {
				map[nextX][nextY] = WALL;
				if (search(nextX, nextY))
					return true;
			}
		}
		return false;
	}
}