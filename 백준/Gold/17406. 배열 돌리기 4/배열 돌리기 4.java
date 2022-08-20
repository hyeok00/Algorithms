import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static StringBuilder sb;
	static int N, M, R, minValue;
	static int[][] arr;
	static boolean[] visit;
	static int[] permArr;
	static ArrayList<Operation> operList;

	static class Operation {
		int r;
		int c;
		int s;

		public Operation() {
		}

		public Operation(int ir, int ic, int is) {
			r = ir;
			c = ic;
			s = is;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		R = Integer.parseInt(splitedLine[2]);

		arr = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		operList = new ArrayList<>();
		for (int i = 0; i < R; ++i) {
			splitedLine = in.readLine().split(" ");
			operList.add(new Operation(Integer.parseInt(splitedLine[0]) - 1, Integer.parseInt(splitedLine[1]) - 1,
					Integer.parseInt(splitedLine[2])));
		}

		// 로직
		minValue = Integer.MAX_VALUE;
		// 순열에 따라 결과가 다르다..
		visit = new boolean[R];
		permArr = new int[R];

		perm(R, 0);

		// 출력
		sb.append(minValue);
		System.out.println(sb);
	}

	private static void perm(int n, int depth) {
		if (n == depth) {
			rotate();
			return;
		}
		for (int i = 0; i < n; ++i) {
			if (visit[i] == false) {
				visit[i] = true;
				permArr[depth] = i;
				perm(n, depth + 1);
				visit[i] = false;
			}
		}
	}

	private static void rotate() {
		int[][] temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				temp[i][j] = arr[i][j];
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print(temp[i][j]+" ");
//			System.out.println();
//		}

		for (int i = 0; i < R; i++) {
			int curValue = permArr[i];
			for (int j = 0; j < operList.get(curValue).s; j++) {
				Operation o = operList.get(curValue);
				int x1 = o.r - o.s + j;
				int y1 = o.c - o.s + j;

				int x2 = o.r + o.s - j;
				int y2 = o.c + o.s - j;

				nextShape(temp, x1, y1, x2, y2);
			}
		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print(temp[i][j]+" ");
//			System.out.println();
//		}
//		System.out.println();
		getMaxSum(temp);
	}

	private static void nextShape(int[][] temp, int x1, int y1, int x2, int y2) {
		int tmp1, tmp2;

		// 상하좌우 반시계
		tmp1 = temp[x1][y2];
		for (int y = y2; y > y1; y--) {
			temp[x1][y] = temp[x1][y - 1];
		}

		tmp2 = tmp1;
		tmp1 = temp[x2][y2];

		for (int x = x2; x > x1; x--) {
			if (x - 1 == x1) {
				temp[x][y2] = tmp2;
				continue;
			}
			temp[x][y2] = temp[x - 1][y2];
		}

		tmp2 = tmp1;
		tmp1 = temp[x2][y1];

		for (int y = y1; y < y2; y++) {
			if (y + 1 == y2) {
				temp[x2][y] = tmp2;
				continue;
			}

			temp[x2][y] = temp[x2][y + 1];
		}

		tmp2 = tmp1;

		for (int x = x1; x < x2; x++) {
			if (x + 1 == x2) {
				temp[x][y1] = tmp2;
				continue;
			}

			temp[x][y1] = temp[x + 1][y1];
		}
	}

	public static void getMaxSum(int[][] temp) {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += temp[i][j];
			}
			minValue = minValue > sum ? sum : minValue;
		}
	}

}
