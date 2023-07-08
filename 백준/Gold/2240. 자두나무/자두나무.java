import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		int T = Integer.parseInt(splitedLine[0]);
		int W = Integer.parseInt(splitedLine[1]);

		int[] data = new int[T + 1];
		for (int i = 1; i <= T; ++i)
			data[i] = Integer.parseInt(in.readLine());

		int[][][] arr = new int[T + 1][W + 2][2];

		for (int i = 1; i <= T; ++i) {
			for (int j = 1; j <= W + 1; j++) {
				if (data[i] == 1) {
					arr[i][j][0] = Math.max(arr[i - 1][j][0], arr[i - 1][j - 1][1]) + 1;
					arr[i][j][1] = Math.max(arr[i - 1][j][1], arr[i - 1][j - 1][0]);
				} else {
					if (i == 1 && j == 1)
						continue;
					arr[i][j][0] = Math.max(arr[i - 1][j][0], arr[i - 1][j - 1][1]);
					arr[i][j][1] = Math.max(arr[i - 1][j][1], arr[i - 1][j - 1][0]) + 1;
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= W + 1; ++i) {
			max = Math.max(max, Math.max(arr[T][i][0], arr[T][i][1]));
		}
		System.out.println(max);
	}
}