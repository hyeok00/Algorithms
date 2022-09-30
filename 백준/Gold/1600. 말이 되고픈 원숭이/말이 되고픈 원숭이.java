import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class Pos {
		public int x;
		public int y;
		public int jump;

		public Pos(int x, int y, int jump) {
			this.x = x;
			this.y = y;
			this.jump = jump;
		}
	}

	static StringBuilder sb;
	static int[][] arr, dp;

	static int[] horseX = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] horseY = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] monkeyX = { -1, 1, 0, 0 };
	static int[] monkeyY = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int K, W, H;
		K = Integer.parseInt(in.readLine());
		String[] splitedLine = in.readLine().split(" ");

		W = Integer.parseInt(splitedLine[0]);
		H = Integer.parseInt(splitedLine[1]);

		arr = new int[H][W];

		for (int i = 0; i < H; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < W; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		// 로직
		int[][][] visit = new int[H][W][K + 1];

		Pos first = new Pos(0, 0, 0);
		Queue<Pos> q = new LinkedList<>();
		q.add(first);
		int max = Integer.MAX_VALUE;
		int depth = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			depth++;
			while (size-- > 0) {
				Pos temp = q.poll();
				if (temp.x == H - 1 && temp.y == W - 1) {
					max = depth;
					break;
				}

				for (int i = 0; i < 4; ++i) {
					int nextX = temp.x + monkeyX[i];
					int nextY = temp.y + monkeyY[i];
					if (0 <= nextX && nextX < H && 0 <= nextY && nextY < W && arr[nextX][nextY] != 1
							&& visit[nextX][nextY][temp.jump] == 0) {
						visit[nextX][nextY][temp.jump] = 1;
						q.add(new Pos(nextX, nextY, temp.jump));
					}
				}

				if (temp.jump < K) {
					for (int i = 0; i < 8; ++i) {
						int nextX = temp.x + horseX[i];
						int nextY = temp.y + horseY[i];
						if (0 <= nextX && nextX < H && 0 <= nextY && nextY < W && arr[nextX][nextY] != 1
								&& visit[nextX][nextY][temp.jump + 1] == 0) {
							visit[nextX][nextY][temp.jump + 1] = 1;
							q.add(new Pos(nextX, nextY, temp.jump + 1));
						}
					}
				}

			}
			if (max == depth)
				break;
		}

		// 출력부
		if (max == depth)
			sb.append(max - 1);
		else
			sb.append("-1");

		System.out.println(sb);
	}
}