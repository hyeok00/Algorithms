import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int W, H;
	static int[][] map;

	final static int WALL = 1; // 블록
	final static int BLANK = 0; // 빈 공간
	final static int POSSIBLE = 8; // 고이는 경우
	final static int IMPOSSIBLE = 9; // 고이지 않는 경우

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		H = stoi(splitedLine[0]);
		W = stoi(splitedLine[1]);
		map = new int[H][W + 2];

		splitedLine = in.readLine().split(" ");

		// 좌우로 한 칸씩 추가로 주고, 기둥을 세운다.
		for (int i = 0; i < W; ++i) {
			int k = stoi(splitedLine[i]);
			for (int j = 0; j < k; ++j) {
				map[H - j - 1][i + 1] = 1;
			}
		}

		for (int i = 0; i < H; ++i) {
			for (int j = 1; j <= W; ++j) {
				if (map[i][j] == BLANK)
					simulation(i, j);
			}
		}

		int result = getPossibleCount();
		System.out.println(result);
	}

	private static int getPossibleCount() {
		int count=0;
		for (int i = 0; i < H; ++i)
			for (int j = 1; j <= W; ++j)
				if (map[i][j] == POSSIBLE)
					count++;
		return count;
	}

	// 고이는 물인지 확인한다.
	private static void simulation(final int i, final int j) {
		boolean isOkayToLeft = leftCheck(i, j);
		boolean isOkayToRight = rightCheck(i, j);
		if (isOkayToLeft && isOkayToRight)
			map[i][j] = POSSIBLE;
		else
			map[i][j] = IMPOSSIBLE;
	}

	private static boolean leftCheck(final int i, final int j) {
		int leftIndex = j;
		while (leftIndex >= 0) {
			if (map[i][leftIndex] == IMPOSSIBLE) return false;
			if (map[i][leftIndex] == POSSIBLE) return true;
			if (map[i][leftIndex] == WALL) return true;
			if (map[i][leftIndex] == BLANK)
				leftIndex--;
		}
		return false;
	}

	private static boolean rightCheck(final int i, final int j) {
		int rightIndex = j;
		while (rightIndex <= W) {
			if (map[i][rightIndex] == IMPOSSIBLE) return false;
			if (map[i][rightIndex] == POSSIBLE) return true;
			if (map[i][rightIndex] == WALL) return true;
			if (map[i][rightIndex] == BLANK)
				rightIndex++;
		}
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}