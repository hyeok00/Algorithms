import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int R, C;
	static char[][] map;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}

		public void init(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		R = stoi(splitedLine[0]);
		C = stoi(splitedLine[1]);

		map = new char[R][C];

		// input
		for (int i = 0; i < R; ++i)
			map[i] = in.readLine().toCharArray();

		// logic
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == '.')
					if (check(i, j)) {
						return;
					}
			}
		}

	}

	private static boolean check(int nextX, int nextY) {
		Point tp = new Point(nextX + dx[TOP], nextY + dy[TOP]);
		Point bp = new Point(nextX + dx[BOTTOM], nextY + dy[BOTTOM]);
		Point lp = new Point(nextX + dx[LEFT], nextY + dy[LEFT]);
		Point rp = new Point(nextX + dx[RIGHT], nextY + dy[RIGHT]);

		boolean[] chk = new boolean[4];
		if (isInRange(tp.x, tp.y) && canComeBottom(map[tp.x][tp.y])) {
			chk[0] = true;
		}
		if (isInRange(bp.x, bp.y) && canComeTop(map[bp.x][bp.y])) {
			chk[1] = true;
		}
		if (isInRange(lp.x, lp.y) && canComeRight(map[lp.x][lp.y])) {
			chk[2] = true;
		}
		if (isInRange(rp.x, rp.y) && canComeLeft(map[rp.x][rp.y])) {
			chk[3] = true;
		}

		if (chk[0] == true && chk[1] == true && chk[2] == true && chk[3] == true)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " +");
		else if (chk[0] == true && chk[1] == true && chk[2] == false && chk[3] == false)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " |");
		else if (chk[0] == true && chk[1] == false && chk[2] == true && chk[3] == false)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " 3");
		else if (chk[0] == true && chk[1] == false && chk[2] == false && chk[3] == true)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " 2");
		else if (chk[0] == false && chk[1] == true && chk[2] == true && chk[3] == false)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " 4");
		else if (chk[0] == false && chk[1] == true && chk[2] == false && chk[3] == true)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " 1");
		else if (chk[0] == false && chk[1] == false && chk[2] == true && chk[3] == true)
			System.out.println((nextX + 1) + " " + (nextY + 1) + " -");
		else {
			// nothing
			return false;
		}
		return true;
	}

	final static int TOP = 0;
	final static int BOTTOM = 1;
	final static int LEFT = 2;
	final static int RIGHT = 3;

	private static boolean isConnect(char current, char next, int d) {
		boolean result = false;
		switch (d) {
			case TOP:
				result = canGoTop(current) && canComeBottom(next);
				break;
			case BOTTOM:
				result = canGoBottom(current) && canComeBottom(next);
				break;
			case LEFT:
				result = canGoLeft(current) && canComeRight(next);
				break;
			case RIGHT:
				result = canGoRight(current) && canComeLeft(next);
				break;

		}
		return result;
	}

	private static boolean canGoTop(char c) {
		if (c == '|' || c == '+' || c == '2' || c == '3')
			return true;
		return false;
	}

	private static boolean canGoBottom(char c) {
		if (c == '|' || c == '+' || c == '1' || c == '4')
			return true;
		return false;
	}

	private static boolean canGoLeft(char c) {
		if (c == '-' || c == '+' || c == '3' || c == '4')
			return true;
		return false;
	}

	private static boolean canGoRight(char c) {
		if (c == '-' || c == '+' || c == '1' || c == '2')
			return true;
		return false;
	}

	private static boolean canComeTop(char c) {
		if (c == '|' || c == '+' || c == '2' || c == '3')
			return true;
		return false;
	}

	private static boolean canComeBottom(char c) {
		if (c == '|' || c == '+' || c == '1' || c == '4')
			return true;
		return false;
	}

	private static boolean canComeLeft(char c) {
		if (c == '-' || c == '+' || c == '3' || c == '4')
			return true;
		return false;
	}

	private static boolean canComeRight(char c) {
		if (c == '-' || c == '+' || c == '1' || c == '2')
			return true;
		return false;
	}

	private static boolean isInRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}