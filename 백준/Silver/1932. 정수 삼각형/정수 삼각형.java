import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		// 입력
		int SIZE = Integer.parseInt(input.readLine());
		int[][] arr = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i) {
			String[] splitedStr = input.readLine().split(" ");
			for (int j = 0; j <= i; ++j) {
				arr[i][j] = Integer.parseInt(splitedStr[j]);
			}
		}

		// 로직

		int inner = SIZE;
		for (int i = SIZE - 1; i > 0; --i) {
			for (int j = 0; j < inner - 1; ++j) {
				// 덧셈은 큰 값을 더할수록 더 큰 값이다.
				if (arr[i][j] > arr[i][j + 1]) {
					arr[i - 1][j] += arr[i][j];
				} else {
					arr[i - 1][j] += arr[i][j + 1];
				}
			}
			inner--;
		}
		sb.append(arr[0][0]);
		System.out.println(sb);
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}