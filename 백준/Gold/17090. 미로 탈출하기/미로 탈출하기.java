import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static StringBuilder sb;
	static char[][] map;
	static int[][] dp;
	final static int UNKNOWN = 0;
	final static int SUCCESS = 1;
	final static int FAIL = 2;
	final static int USING = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 입력부
		String[] spiltedLine = in.readLine().split(" ");
		N = Integer.parseInt(spiltedLine[0]);
		M = Integer.parseInt(spiltedLine[1]);

		map = new char[N][M];
		for (int i = 0; i < N; ++i) {
			String line = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = line.charAt(j);
			}
		}

		// 로직
		dp = new int[N][M]; // a,b 에서 탐색했을 때 가능한가?

		int res = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (dp[i][j] == UNKNOWN) {
					dfsSearch(i, j);
				}

				if (dp[i][j] == SUCCESS) {
					++res;
				} else { // dp[i][j] == FAIL
					// 실패케이스 이므로 내부 탐색할 필요가 없다.
				}
			}
		}
		// 출력
		sb.append(res);
		System.out.println(res);
	}

	private static void dfsSearch(int startX, int startY) {
		dp[startX][startY] = USING;
		int nextX = startX;
		int nextY = startY;
		switch (map[startX][startY]) {
		case 'U':
			nextX -= 1;
			break;
		case 'R':
			nextY += 1;
			break;
		case 'D':
			nextX += 1;
			break;
		case 'L':
			nextY -= 1;
			break;
		default:
			break;
		}

		if ((0 <= nextX) && (nextX < N) && (0 <= nextY) && (nextY < M)) { // 탈출하지 못한 경우
			if (dp[nextX][nextY] == SUCCESS) {
				dp[startX][startY] = SUCCESS;
			} else if (dp[nextX][nextY] == USING) {
				dp[startX][startY] = FAIL;
			} else if (dp[nextX][nextY] == FAIL) {
				dp[startX][startY] = dp[nextX][nextY];
			} else if (dp[nextX][nextY] == UNKNOWN) {
				dfsSearch(nextX, nextY);
				dp[startX][startY] = dp[nextX][nextY];
			}
		} else {
			// 탈출한 경우
			dp[startX][startY] = SUCCESS;
		}
	}
}
