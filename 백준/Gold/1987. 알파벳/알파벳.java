import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static StringBuilder sb;
	// 상하좌우 순
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int row;
	static int col;
	static char[][] map;
	static boolean[] visit;
	static int maxDepth;

	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] inputString = input.readLine().split(" ");
		row = Integer.parseInt(inputString[0]);
		col = Integer.parseInt(inputString[1]);

		sb = new StringBuilder();

		map = new char[row][col];
		visit = new boolean['Z' - 'A' + 1];

		for (int i = 0; i < row; ++i) {
			String line = input.readLine();
			for (int j = 0; j < col; ++j) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완료
		maxDepth = 0;
		visit[map[0][0] - 'A'] = true;
		dfsSearch(0, 0, 1);

		sb.append(maxDepth);
		System.out.println(sb);
	}

	public int dfsSearch(int startX, int startY, int depth) {
		if (depth >= maxDepth)
			maxDepth = depth;

		int posX = startX;
		int posY = startY;
		for (int i = 0; i < dx.length; ++i) {
			int nextX = posX + dx[i];
			int nextY = posY + dy[i];
			if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col) {
				char nextValue = map[nextX][nextY];
				if (visit[nextValue - 'A'] == false) {
					visit[nextValue - 'A'] = true;
					dfsSearch(nextX, nextY, depth + 1);
					visit[nextValue - 'A'] = false;
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}