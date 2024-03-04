import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int SIZE = 10;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Map<Character, Character> map;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// init
		char[][] arr = new char[SIZE][SIZE];
		char[][] simulationArr = new char[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i)
			arr[i] = in.readLine().toCharArray();
		map = new HashMap<>();
		map.put('#', 'O');
		map.put('O', '#');

		int result = Integer.MAX_VALUE;
		// 모든 경우의 수
		for (int state = 0; state < (1 << SIZE); ++state) {
			int count = 0;
			deepCopy(arr, simulationArr); // arr의 값을 simulationArr로 복사.

			// 첫 줄은 경우의 수를 따져서 처리한다.
			for (int j = 0; j < SIZE; ++j) {
				// 특정 위치를 누르는 선택
				int bitNum = 1 << j;
				if ((state & bitNum) == bitNum) {
					count++;
					pressButton(0, j, simulationArr);
				}
			}

			// 나머지 줄은 윗 줄의 결과에 종속된다.
			for (int i = 1; i < SIZE; ++i) {
				for (int j = 0; j < SIZE; ++j) {
					// 불이 켜져있다면, 지금 꺼야한다.
					if (simulationArr[i - 1][j] == 'O') {
						count++;
						pressButton(i, j, simulationArr);
					}
				}
			}

			if (isValidate(simulationArr[SIZE-1]))
				result = Math.min(result, count);
		}

		if (result == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static boolean isValidate(char[] chars) {
		for (char c : chars)
			if (c == 'O')
				return false;
		return true;
	}

	private static void deepCopy(char[][] arr, char[][] simulationArr) {
		for (int i = 0; i < SIZE; ++i)
			for (int j = 0; j < SIZE; ++j)
				simulationArr[i][j] = arr[i][j];
	}

	private static void pressButton(int x, int y, char[][] simulationArr) {
		// 현재 위치 변경
		simulationArr[x][y] = map.get(simulationArr[x][y]);

		// 인접 4방향 변경
		for (int d = 0; d < 4; ++d) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			if (isInRange(nextX, nextY))
				simulationArr[nextX][nextY] = map.get(simulationArr[nextX][nextY]);
		}
	}

	public static boolean isInRange(int x, int y) {
		if (0 <= x && x < SIZE && 0 <= y && y < SIZE)
			return true;
		return false;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}