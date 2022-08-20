import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputString = in.readLine().split(" ");
		int N = Integer.parseInt(inputString[0]);
		int M = Integer.parseInt(inputString[1]);
		sb = new StringBuilder();

		int[][] arr = new int[N][N + 1];

		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			int sum = 0;
			for (int j = 0; j < N; ++j) {
				sum += Integer.parseInt(splitedLine[j]);
				arr[i][j + 1] = sum;
			}
		}

		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				
			}
		}
		// 출력부
		for (int i = 0; i < M; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			int startX = Integer.parseInt(splitedLine[0]);
			int startY = Integer.parseInt(splitedLine[1]);
			int endX = Integer.parseInt(splitedLine[2]);
			int endY = Integer.parseInt(splitedLine[3]);

			int res = 0;
			for (int k = startX - 1; k <= endX - 1; ++k) {
				res = res + arr[k][endY] - arr[k][startY - 1];
			}
			sb.append(res + "\n");
		}
		System.out.println(sb);
	}
}