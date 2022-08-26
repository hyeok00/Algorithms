import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int R, C, T;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		R = Integer.parseInt(splitedLine[0]);
		C = Integer.parseInt(splitedLine[1]);
		T = Integer.parseInt(splitedLine[2]);

		int airConditionRowPos = 0;
		int[][] map = new int[R][C];
		for (int i = 0; i < R; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < C; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				if (map[i][j] == -1)
					airConditionRowPos = i;
			}
		}

		// 로직
		int[][] testMap = new int[R][C];
		for (int time = 0; time < T; ++time) {
			testMap = new int[R][C];

			// 미세먼지 확산
			step1(map, testMap);

			// 공기청정기 가동
			step2(testMap, airConditionRowPos);

			// 배열 복사
			deepCopy(testMap, map);
		}

		// 출력부
		int result = 2; // 공기청정기 2칸 값 고려
		for (int[] row : map)
			for (int data : row)
				result += data;

		sb.append(result);
		System.out.println(sb);
	}

	private static void deepCopy(int[][] src, int[][] des) {
		for (int i = 0; i < src.length; ++i)
			for (int j = 0; j < src[i].length; ++j)
				des[i][j] = src[i][j];
	}

	private static void step2(int[][] testMap, int airPos) {
		int top = airPos - 1;
		int bottom = airPos;

		// 반시계 순환
		// 아래로 쉬프팅
		for (int i = top - 1; i >= 1; --i) {
			testMap[i][0] = testMap[i - 1][0];
		}

		// 왼쪽으로
		for (int i = 0; i < C - 1; ++i) {
			testMap[0][i] = testMap[0][i + 1];
		}
		// 위로
		for (int i = 0; i < top; ++i) {
			testMap[i][C - 1] = testMap[i + 1][C - 1];
		}

		// 오른쪽으로
		for (int i = C - 1; i > 1; --i) {
			testMap[top][i] = testMap[top][i - 1];
		}
		testMap[top][1] = 0;

		// 시계 순환
		// 위로 쉬프팅
		for (int i = bottom+1; i < R - 1; ++i) {
			testMap[i][0] = testMap[i + 1][0];
		}

		// 왼쪽으로
		for (int i = 0; i < C - 1; ++i) {
			testMap[R-1][i] = testMap[R-1][i + 1];
		}
		// 아래로
		for (int i = R - 1; i >= bottom; --i) {
			testMap[i][C - 1] = testMap[i - 1][C - 1];
		}

		// 오른쪽으로
		for (int i = C - 1; i > 1; --i) {
			testMap[bottom][i] = testMap[bottom][i - 1];
		}
		testMap[bottom][1] = 0;
	}

	private static void step1(int[][] map, int[][] testMap) {
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] > 0) { // 미세먼지가 있는 구역이다.
					int count = 0;
					for (int p = 0; p < dx.length; ++p) {
						int nextX = i + dx[p];
						int nextY = j + dy[p];
						if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && map[nextX][nextY] != -1) {
							// 미세먼지가 퍼질 수 있는 공간 수 확인
							count++;
						}
					}

					int near = map[i][j] / 5;
					int current = map[i][j] - near * count;

					testMap[i][j] = testMap[i][j] + current; // 현재 자리 갱신
					for (int p = 0; p < dx.length; ++p) { // 주변 위치 갱신
						int nextX = i + dx[p];
						int nextY = j + dy[p];
						if (0 <= nextX && nextX < R && 0 <= nextY && nextY < C && map[nextX][nextY] != -1) {
							testMap[nextX][nextY] = testMap[nextX][nextY] + near;
						}
					}
				} else if (map[i][j] == -1) { // 공기 청정기가 자리하고 있는 위치
					testMap[i][j] = -1;
				} else { // 빈 공간
					// nothing to do
				}
			}
		}
	}
}