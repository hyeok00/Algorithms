import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static final int SIZE = 11;
	static int[][] map;
	static int max;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		final int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			map = new int[SIZE][SIZE];
			selected = new boolean[SIZE];
			for (int i = 0; i < SIZE; ++i) {
				String[] splitedLine = in.readLine().split(" ");
				for (int j = 0; j < SIZE; ++j) {
					map[i][j] = Integer.parseInt(splitedLine[j]);
				}
			}

			max = 0;
			getMax(0, 0);
			sb.append(max).append("\n");
		}

		System.out.println(sb);
	}

	private static void getMax(int depth, int curSum) {
		if (depth >= SIZE) {
			max = Math.max(curSum, max);
			return;
		}

		for (int i = 0; i < SIZE; ++i) {
			if (map[depth][i] != 0 && selected[i] == false) {
				selected[i] = true;
				getMax(depth + 1, curSum + map[depth][i]);
				selected[i] = false;
			}
		}
	}
}