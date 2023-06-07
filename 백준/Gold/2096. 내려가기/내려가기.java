import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		int[][] arr = new int[3][2];

		for (int i = 0; i < N; i++) {
			String[] splitedLine = in.readLine().split(" ");

			int x1 = Integer.parseInt(splitedLine[0]);
			int x2 = Integer.parseInt(splitedLine[1]);
			int x3 = Integer.parseInt(splitedLine[2]);

			if (i == 0) {
				arr[0][1] = x1;
				arr[0][0] = arr[0][1];

				arr[1][1] = x2;
				arr[1][0] = arr[1][1];

				arr[2][1] = x3;
				arr[2][0] = arr[2][1];
			} else {
				int temp1 = arr[0][0];
				int temp3 = arr[2][0];
				arr[0][0] = Math.max(arr[0][0], arr[1][0]) + x1;
				arr[2][0] = Math.max(arr[1][0], arr[2][0]) + x3;
				arr[1][0] = Math.max(Math.max(temp1, temp3), arr[1][0]) + x2;

				temp1 = arr[0][1];
				temp3 = arr[2][1];
				arr[0][1] = Math.min(arr[0][1], arr[1][1]) + x1;
				arr[2][1] = Math.min(arr[1][1], arr[2][1]) + x3;
				arr[1][1] = Math.min(Math.min(temp1, temp3), arr[1][1]) + x2;
			}
		}
		System.out.println(
			Math.max(Math.max(arr[0][0], arr[2][0]), arr[1][0]) + " " + Math.min(Math.min(arr[0][1], arr[2][1]),
				arr[1][1]));
	}
}