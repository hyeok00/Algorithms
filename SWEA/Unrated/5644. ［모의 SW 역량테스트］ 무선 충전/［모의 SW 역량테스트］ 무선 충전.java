import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;
	static int T, M, A, RESULT;
	static Player p1, p2;
	final static int[] dx = { 0, 0, 1, 0, -1 };
	final static int[] dy = { 0, -1, 0, 1, 0 };
	static String[] splitedLine;
	static BC[] bc;

	static class Player {
		int posX;
		int posY;
		int[] move;

		public Player(int a, int b, int c) {
			posX = a;
			posY = b;
			move = new int[c];
		}
	}

	static class BC {
		int posX;
		int posY;
		int distance;
		int power;

		public BC(int a, int b, int c, int d) {
			posX = a;
			posY = b;
			distance = c;
			power = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			RESULT = 0;
			splitedLine = in.readLine().split(" "); // M, A
			M = Integer.parseInt(splitedLine[0]);
			A = Integer.parseInt(splitedLine[1]);

			p1 = new Player(1, 1, M); // 시작점은 1,1 고정
			p2 = new Player(10, 10, M); // 시작점은 10,10 고정

			splitedLine = in.readLine().split(" "); // P1 Move
			for (int i = 0; i < M; ++i) {
				p1.move[i] = Integer.parseInt(splitedLine[i]);
			}
			splitedLine = in.readLine().split(" "); // P2 Move
			for (int i = 0; i < M; ++i) {
				p2.move[i] = Integer.parseInt(splitedLine[i]);
			}

			bc = new BC[A];
			for (int i = 0; i < A; ++i) {
				splitedLine = in.readLine().split(" ");
				bc[i] = new BC(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]),
						Integer.parseInt(splitedLine[2]), Integer.parseInt(splitedLine[3]));
			}
			// 로직
			check();
			// 각 움직임에 대한 체크
			for (int i = 0; i < M; ++i) {
				movePos(p1, i);
				movePos(p2, i);

				check();
			}

			sb.append("#" + tc + " " + RESULT + "\n");
		}
		// 출력부
		System.out.println(sb);
	}

	public static boolean isChargerble(Player p, BC b) {
		return getDistance(p.posX, p.posY, b.posX, b.posY) <= b.distance;
	}

	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void movePos(Player p, int i) {
		p.posX = p.posX + dx[p.move[i]];
		p.posY = p.posY + dy[p.move[i]];
	}

	public static void check() {
		int max = 0;
		for (int i = 0; i < A; ++i) { // p1
			for (int j = 0; j < A; ++j) { // p2
				int innerSum = 0;
				if (i == j) { // 같은 BC를 공유할 경우, 반으로 나눠야 한다.
					if (isChargerble(p1, bc[i]) && isChargerble(p2, bc[j])) {
						innerSum += bc[i].power / 2;
						innerSum += bc[j].power / 2;
					} else if (isChargerble(p1, bc[i])) {
						innerSum += bc[i].power;
					} else if (isChargerble(p2, bc[j])) {
						innerSum += bc[j].power;
					}
				} else {
					if (isChargerble(p1, bc[i])) {
						innerSum += bc[i].power;
					}
					if (isChargerble(p2, bc[j])) {
						innerSum += bc[j].power;
					}
				}
				if (innerSum >= max)
					max = innerSum;
			}
		}

		RESULT += max;
	}
}
