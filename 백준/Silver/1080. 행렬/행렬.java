import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		char[][] arr = new char[N][M];
		char[][] goal = new char[N][M];

		for (int i = 0; i < N; ++i) {
			arr[i] = in.readLine().toCharArray();
		}
		for (int i = 0; i < N; ++i) {
			goal[i] = in.readLine().toCharArray();
		}

		int count = 0;
		for (int i = 0; i <= N - 3; ++i) {
			for (int j = 0; j <= M - 3; ++j) {
				if (arr[i][j] == goal[i][j])
					continue;
				reverse(arr, i, j);
				count++;
			}
		}
		if (check(arr, goal))
			sb.append(count);
		else
			sb.append("-1");
		System.out.println(sb);
	}

	private static boolean check(char[][] arr, char[][] goal) {
		int N = arr.length;
		int M = arr[0].length;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (arr[i][j] != goal[i][j])
					return false;
			}
		}
		return true;
	}

	private static void reverse(char[][] arr, int x, int y) {
		for (int i = x; i < x + 3; ++i) {
			for (int j = y; j < y + 3; ++j) {
				if (arr[i][j] == '0')
					arr[i][j] = '1';
				else
					arr[i][j] = '0';
			}
		}
	}
}