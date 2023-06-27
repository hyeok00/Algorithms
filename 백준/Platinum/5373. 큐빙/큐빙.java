import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[] initSide = {'w', 'y', 'r', 'o', 'g', 'b'};
	static char[][] copyArr;
	static char[] copyRow;

	static class Cube {
		char[][][] arr;

		Cube() {
			arr = new char[6][3][3];
			for (int k = 0; k < 6; ++k) {
				for (int i = 0; i < 3; ++i) {
					for (int j = 0; j < 3; ++j) {
						arr[k][i][j] = initSide[k];
					}
				}
			}
		}

		public void print() {
			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					sb.append(arr[0][i][j]);
				}
				sb.append("\n");
			}
		}

		public void turnMain(int side, char dir) {
			for (int i = 0; i < 3; ++i)
				for (int j = 0; j < 3; ++j)
					copyArr[i][j] = arr[side][i][j];

			if (dir == '+') {
				arr[side][0][0] = copyArr[2][0];
				arr[side][1][0] = copyArr[2][1];
				arr[side][2][0] = copyArr[2][2];
				arr[side][0][1] = copyArr[1][0];
				arr[side][2][1] = copyArr[1][2];
				arr[side][0][2] = copyArr[0][0];
				arr[side][1][2] = copyArr[0][1];
				arr[side][2][2] = copyArr[0][2];
			} else {
				arr[side][0][0] = copyArr[0][2];
				arr[side][1][0] = copyArr[0][1];
				arr[side][2][0] = copyArr[0][0];
				arr[side][0][1] = copyArr[1][2];
				arr[side][2][1] = copyArr[1][0];
				arr[side][0][2] = copyArr[2][2];
				arr[side][1][2] = copyArr[2][1];
				arr[side][2][2] = copyArr[2][0];
			}
		}

		public void turnUp(char dir) {
			turnMain(0, dir);
			copyRow[0] = arr[2][0][0];
			copyRow[1] = arr[2][0][1];
			copyRow[2] = arr[2][0][2];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[2][0][i] = arr[5][0][i];
				for (int i = 0; i < 3; i++)
					arr[5][0][i] = arr[3][2][2 - i];
				for (int i = 0; i < 3; i++)
					arr[3][2][2 - i] = arr[4][0][i];
				for (int i = 0; i < 3; i++)
					arr[4][0][i] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[2][0][i] = arr[4][0][i];
				for (int i = 0; i < 3; i++)
					arr[4][0][i] = arr[3][2][2 - i];
				for (int i = 0; i < 3; i++)
					arr[3][2][2 - i] = arr[5][0][i];
				for (int i = 0; i < 3; i++)
					arr[5][0][i] = copyRow[i];
			}
		}

		public void turnDown(char dir) {
			turnMain(1, dir);
			copyRow[0] = arr[2][2][0];
			copyRow[1] = arr[2][2][1];
			copyRow[2] = arr[2][2][2];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[2][2][i] = arr[4][2][i];
				for (int i = 0; i < 3; i++)
					arr[4][2][i] = arr[3][0][2 - i];
				for (int i = 0; i < 3; i++)
					arr[3][0][2 - i] = arr[5][2][i];
				for (int i = 0; i < 3; i++)
					arr[5][2][i] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[2][2][i] = arr[5][2][i];
				for (int i = 0; i < 3; i++)
					arr[5][2][i] = arr[3][0][2 - i];
				for (int i = 0; i < 3; i++)
					arr[3][0][2 - i] = arr[4][2][i];
				for (int i = 0; i < 3; i++)
					arr[4][2][i] = copyRow[i];
			}
		}

		public void turnFront(char dir) {
			turnMain(2, dir);
			copyRow[0] = arr[0][2][0];
			copyRow[1] = arr[0][2][1];
			copyRow[2] = arr[0][2][2];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[0][2][i] = arr[4][2 - i][2];
				for (int i = 0; i < 3; i++)
					arr[4][2 - i][2] = arr[1][0][2 - i];
				for (int i = 0; i < 3; i++)
					arr[1][0][2 - i] = arr[5][i][0];
				for (int i = 0; i < 3; i++)
					arr[5][i][0] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[0][2][i] = arr[5][i][0];
				for (int i = 0; i < 3; i++)
					arr[5][i][0] = arr[1][0][2 - i];
				for (int i = 0; i < 3; i++)
					arr[1][0][2 - i] = arr[4][2 - i][2];
				for (int i = 0; i < 3; i++)
					arr[4][2 - i][2] = copyRow[i];
			}
		}

		public void turnBack(char dir) {
			turnMain(3, dir);
			copyRow[0] = arr[0][0][0];
			copyRow[1] = arr[0][0][1];
			copyRow[2] = arr[0][0][2];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[0][0][i] = arr[5][i][2];
				for (int i = 0; i < 3; i++)
					arr[5][i][2] = arr[1][2][2 - i];
				for (int i = 0; i < 3; i++)
					arr[1][2][2 - i] = arr[4][2 - i][0];
				for (int i = 0; i < 3; i++)
					arr[4][2 - i][0] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[0][0][i] = arr[4][2 - i][0];
				for (int i = 0; i < 3; i++)
					arr[4][2 - i][0] = arr[1][2][2 - i];
				for (int i = 0; i < 3; i++)
					arr[1][2][2 - i] = arr[5][i][2];
				for (int i = 0; i < 3; i++)
					arr[5][i][2] = copyRow[i];
			}
		}

		public void turnLeft(char dir) {
			turnMain(4, dir);
			copyRow[0] = arr[0][0][0];
			copyRow[1] = arr[0][1][0];
			copyRow[2] = arr[0][2][0];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[0][i][0] = arr[3][i][0];
				for (int i = 0; i < 3; i++)
					arr[3][i][0] = arr[1][i][0];
				for (int i = 0; i < 3; i++)
					arr[1][i][0] = arr[2][i][0];
				for (int i = 0; i < 3; i++)
					arr[2][i][0] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[0][i][0] = arr[2][i][0];
				for (int i = 0; i < 3; i++)
					arr[2][i][0] = arr[1][i][0];
				for (int i = 0; i < 3; i++)
					arr[1][i][0] = arr[3][i][0];
				for (int i = 0; i < 3; i++)
					arr[3][i][0] = copyRow[i];
			}
		}

		public void turnRight(char dir) {
			turnMain(5, dir);
			copyRow[0] = arr[0][0][2];
			copyRow[1] = arr[0][1][2];
			copyRow[2] = arr[0][2][2];
			if (dir == '+') {
				for (int i = 0; i < 3; i++)
					arr[0][i][2] = arr[2][i][2];
				for (int i = 0; i < 3; i++)
					arr[2][i][2] = arr[1][i][2];
				for (int i = 0; i < 3; i++)
					arr[1][i][2] = arr[3][i][2];
				for (int i = 0; i < 3; i++)
					arr[3][i][2] = copyRow[i];
			} else {
				for (int i = 0; i < 3; i++)
					arr[0][i][2] = arr[3][i][2];
				for (int i = 0; i < 3; i++)
					arr[3][i][2] = arr[1][i][2];
				for (int i = 0; i < 3; i++)
					arr[1][i][2] = arr[2][i][2];
				for (int i = 0; i < 3; i++)
					arr[2][i][2] = copyRow[i];
			}
		}
	}

	static int N, T;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());

		// 로직
		copyArr = new char[3][3];
		copyRow = new char[3];
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(in.readLine());
			String[] splitedLine = in.readLine().split(" ");
			Cube cube = new Cube();
			for (int i = 0; i < N; ++i) {
				char view = splitedLine[i].charAt(0);
				char dir = splitedLine[i].charAt(1);
				switch (view) {
					case 'U':
						cube.turnUp(dir);
						break;
					case 'D':
						cube.turnDown(dir);
						break;
					case 'F':
						cube.turnFront(dir);
						break;
					case 'B':
						cube.turnBack(dir);
						break;
					case 'L':
						cube.turnLeft(dir);
						break;
					case 'R':
						cube.turnRight(dir);
						break;
					default:
						break;
				}
			}
			cube.print();
		}

		// 출력부
		System.out.println(sb);
	}
}