import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static String[] splitedLine;

	final static char BLANK = '.';
	final static char WALL = '#';
	final static char START = '0';
	final static char END = '1';

	static class Data {
		int x;
		int y;
		int value;

		public Data(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		Queue<Data> q = new LinkedList<>();

		char[][] map = new char[N][M];
		for (int i = 0; i < N; ++i) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == '0')
					q.add(new Data(i, j, 0));
			}
		}

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[][][] visit = new int[N][M][1 << 6];

		int count = -1;
		visit[q.peek().x][q.peek().y][0] = 1;
		while (!q.isEmpty()) {
			Data temp = q.poll();

			if (map[temp.x][temp.y] == END) {
				count = visit[temp.x][temp.y][temp.value];
				break;
			}

			for (int i = 0; i < 4; ++i) {
				int nextX = temp.x + dx[i];
				int nextY = temp.y + dy[i];
				int nextKey = temp.value;
				// 범위 내부이면서, 벽이 아니여야 한다
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M && map[nextX][nextY] != WALL) {
					// 방문한적이 없거나, 방문 당시 Key 현황이 달라야 한다.
					char c = map[nextX][nextY];
					if (visit[nextX][nextY][temp.value] == 0) {
						if ('a' <= c && c <= 'f') {
							// 열쇠
							if (!hasKey(temp.value, c)) {
								nextKey = setKey(temp.value, c);
							}
							q.add(new Data(nextX, nextY, nextKey));
							visit[nextX][nextY][nextKey] = visit[temp.x][temp.y][temp.value] + 1;
						} else if ('A' <= c && c <= 'F') {
							// 문
							// 키가 있는지 확인
							if (hasKey(temp.value, c)) {
								visit[nextX][nextY][nextKey] = visit[temp.x][temp.y][temp.value] + 1;
								q.add(new Data(nextX, nextY, nextKey));
							}
						} else {
							// 빈 칸
							visit[nextX][nextY][nextKey] = visit[temp.x][temp.y][temp.value] + 1;
							q.add(new Data(nextX, nextY, nextKey));
						}
					}
				}
			}
		}
		if (-1 != count) {
			System.out.println(count-1);
		} else {
			System.out.println(-1);
		}
	}

	static int setKey(int value, char c) {
		return value | getWeight(c);
	}

	static int getWeight(char c) {
		return 1 << (c - 'a');
	}

	static boolean hasKey(int value, char c) {
		if ((value & getWeight(c)) == getWeight(c)) {
			return true;
		}
		return false;
	}
}