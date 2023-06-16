import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int kingX;
	static int kingY;
	static int stoneX;
	static int stoneY;

	static boolean check(int x, int y) {
		if (1 <= x && x <= 8 && 1 <= y && y <= 8)
			return true;
		return false;
	}

	static void move(int di, int dj) {

		int kni = kingX + di;
		int knj = kingY + dj;

		if (check(kni, knj)) {
			if (kni == stoneX && knj == stoneY) {

				if (check(stoneX + di, stoneY + dj)) {
					stoneX += di;
					stoneY += dj;
					kingX = kni;
					kingY = knj;
				}
			} else {
				kingX = kni;
				kingY = knj;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		String king = st.nextToken();
		kingX = king.charAt(1) - '0';
		kingY = king.charAt(0) - 'A' + 1;

		String stone = st.nextToken();
		stoneX = stone.charAt(1) - '0';
		stoneY = stone.charAt(0) - 'A' + 1;
		int num = Integer.parseInt(st.nextToken());

		int dx = 0;
		int dy = 0;
		for (int i = 0; i < num; i++) {
			String move = in.readLine();

			if (move.equals("T")) {
				dx = 1;
				dy = 0;
			} else if (move.equals("RT")) {
				dx = 1;
				dy = 1;
			} else if (move.equals("R")) {
				dx = 0;
				dy = 1;
			} else if (move.equals("RB")) {
				dx = -1;
				dy = 1;
			} else if (move.equals("B")) {
				dx = -1;
				dy = 0;
			} else if (move.equals("LB")) {
				dx = -1;
				dy = -1;
			} else if (move.equals("L")) {
				dx = 0;
				dy = -1;
			} else if (move.equals("LT")) {
				dx = 1;
				dy = -1;
			}

			move(dx, dy);
		}
		char fix_kingY = (char)('A' + kingY - 1);
		char fix_stoneY = (char)('A' + stoneY - 1);

		System.out.println(fix_kingY + "" + kingX + "\n" + fix_stoneY + stoneX);
	}
}