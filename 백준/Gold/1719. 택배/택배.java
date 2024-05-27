import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, M;
	static int[][] arr, result;
	static int INF = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);
		arr = new int[N + 1][N + 1];
		result = new int[N + 1][N + 1];

		for (int i = 0; i <= N; ++i) {
			for (int j = 0; j <= N; ++j) {
				if (i == j)
					arr[i][j] = 0;
				else
					arr[i][j] = INF;
			}
		}
		for (int i = 0; i < M; ++i) {
			inputs = in.readLine().split(" ");
			int a = stoi(inputs[0]);
			int b = stoi(inputs[1]);
			int c = stoi(inputs[2]);
			arr[a][b] = c;
			arr[b][a] = c;
			result[a][b] = b;
			result[b][a] = a;
		}

		for (int k = 1; k <= N; ++k) {
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					if (i == j)
						continue;
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
						result[i][j] = result[i][k];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				if (result[i][j] == 0)
					sb.append("-");
				else
					sb.append(result[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}