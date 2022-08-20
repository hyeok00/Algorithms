import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static StringBuilder sb;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			// 입력부
			String[] splitedLine = in.readLine().split(" ");
			int N = Integer.parseInt(splitedLine[0]);
			int M = Integer.parseInt(splitedLine[1]);

			int[][] arr = new int[N][N];
			for (int i = 0; i < N; ++i) {
				splitedLine = in.readLine().split(" ");
				for (int j = 0; j < N; ++j) {
					arr[i][j] = Integer.parseInt(splitedLine[j]);
				}
			}

			// 로직
			int max = -1;

			for (int i = 0; i < N - M + 1; ++i) {
				for (int j = 0; j < N - M + 1; ++j) {
					// 시작지점 결정
					int sum = 0;
					for (int p = 0; p < M; ++p) {
						for (int q = 0; q < M; ++q) {
							sum += arr[i + p][j + q];
						}
					}
					if (max < sum)
						max = sum;
				}
			}
			// 출력
			sb.append("#" + tc + " " + max + "\n");
		}
		System.out.println(sb);
	}
}