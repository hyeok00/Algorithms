import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	public int first;
	public int second;

	public Pair(int x, int y) {
		first = x;
		second = y;
	}

	public Pair() {
	};
}

public class Main {
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int SIZE = Integer.parseInt(input.readLine());

		char[][] normalmap = new char[SIZE][SIZE];
		char[][] abnormalmap = new char[SIZE][SIZE];
		char[][] visitedNormal = new char[SIZE][SIZE];
		char[][] visitedAbNormal = new char[SIZE][SIZE];

		for (int i = 0; i < SIZE; ++i) {
			String str = input.readLine();
			for (int j = 0; j < SIZE; ++j) {
				normalmap[i][j] = str.charAt(j);
				abnormalmap[i][j] = str.charAt(j);
				if (abnormalmap[i][j] == 'R') // 적록색약의 경우에는 R,G과 같으므로 하나로 변환.
					abnormalmap[i][j] = 'G';
			}
		} // 입력 완료

		int res1 = 0;
		int res2 = 0;
		// 탐색
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				if (visitedNormal[i][j] == 0) {
					Pair p = new Pair(i, j);
					dfsSearch(normalmap, visitedNormal, SIZE, p);
					++res1;
				}
				if (visitedAbNormal[i][j] == 0) {
					Pair p = new Pair(i, j);
					dfsSearch(abnormalmap, visitedAbNormal, SIZE, p);
					++res2;
				}
			}
		}
		System.out.println(res1 + " " + res2);
	}

	public static void dfsSearch(char[][] map, char[][] visited, int SIZE, Pair p) {
		char curValue = map[p.first][p.second];
		visited[p.first][p.second] = 1; // 방문처리
		for (int i = 0; i < 4; ++i) {
			int nextX = p.first + dx[i];
			int nextY = p.second + dy[i];
			if (nextX >= 0 && nextX < SIZE && nextY >= 0 && nextY < SIZE && (visited[nextX][nextY] == 0)
					&& (curValue == map[nextX][nextY])) {
				// 범위가 내부이고 방문한 적이 없는 경우
				Pair newpos = new Pair(nextX, nextY);
				dfsSearch(map, visited, SIZE, newpos);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}