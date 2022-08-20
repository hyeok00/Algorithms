import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		int SIZE = 19;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] dx = { -1, 0, 1, 1 };
		int[] dy = { 1, 1, 1, 0 };
		int[][] map = new int[SIZE][SIZE];
		// 입력
		for (int i = 0; i < SIZE; ++i) {
			String[] splitedInputLine = in.readLine().split(" ");
			for (int j = 0; j < SIZE; ++j) {
				map[i][j] = Integer.parseInt(splitedInputLine[j]);
			}
		}

		// 처리
		int winner = 0;
		int ansX = 0;
		int ansY = 0;

		myLoop: for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				if (map[i][j] != 0) {
					int curUser = map[i][j]; // 흑백 유저 확인

					for (int k = 0; k < 4; ++k) { // 4방향 탐색
						int cnt = 0;
						int curX = i;
						int curY = j;

						// System.out.println(k + " " + (curX + 1) + " " + (curY + 1));
						// 육목확인
						int prevX = curX - dx[k];
						int prevY = curY - dy[k];
						if ((prevX >= 0) && (prevX < 19) && (prevY >= 0) && (prevY < 19)
								&& (map[prevX][prevY] == curUser)) {
							continue;
						}
						for (int d = 0; d < 4; ++d) { // 5번만 확인(오목)
							curX = curX + dx[k];
							curY = curY + dy[k];

							if ((curX >= 0) && (curX < 19) && (curY >= 0) && (curY < 19)
									&& (map[curX][curY] == curUser)) {
								// 범위 내부에 반드시 있어야 함.
								cnt++;
								if (cnt >= 4) {
									// 만약 육목인지 확인
									int nextX = curX + dx[k];
									int nextY = curY + dy[k];
									if ((nextX >= 0) && (nextX < 19) && (nextY >= 0) && (nextY < 19)
											&& (map[nextX][nextY] == curUser)) {
										break;
									}

									ansX = i + 1;
									ansY = j + 1;
									winner = curUser;
									break myLoop;
								}
							} else {
								break;
							}
						}
					}
				}
			}
		}
		System.out.println(winner);
		if (winner != 0) {
			System.out.println(ansX + " " + ansY);
		}
	}

}
