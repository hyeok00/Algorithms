import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
	int SIZE = 104;
	int[][] board = new int[SIZE][SIZE];
	int[][] visit = new int[SIZE][SIZE];
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		for (int[] data : rectangle)
			draw(data);

		Queue<int[]> q = new ArrayDeque<>();
		visit[characterX * 2][characterY * 2] = 1;
		q.add(new int[] {characterX * 2, characterY * 2});
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; ++d) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (board[nx][ny] == 1 && !isInRectangle(nx, ny, rectangle) && visit[nx][ny] == 0) {
					q.add(new int[] {nx, ny});
					visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
				}
			}
		}
		return (visit[itemX * 2][itemY * 2] - 1) / 2;
	}

	// 전체 사각형을 순회하며 내부 지점인지 확인한다.
	public boolean isInRectangle(int x, int y, int[][] rectangle) {
		for (int[] data : rectangle)
			if (data[0] * 2 < x && x < data[2] * 2 && data[1] * 2 < y && y < data[3] * 2)
				return true;
		return false;
	}

	private void draw(int[] data) {
		int fixedX1 = data[0] * 2;
		int fixedY1 = data[1] * 2;
		int fixedX2 = data[2] * 2;
		int fixedY2 = data[3] * 2;

		for (int i = fixedX1; i <= fixedX2; ++i) {
			for (int j = fixedY1; j <= fixedY2; ++j) {
				if (i == fixedX1 || i == fixedX2 || j == fixedY1 || j == fixedY2)
					board[i][j] = 1;
				else
					board[i][j] = 0;
			}
		}
	}
}