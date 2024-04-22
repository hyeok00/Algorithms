import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;

	static int[][] dist, arr;
	static int[] sum;
	static int MAX = 987654321;

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			String[] inputs = in.readLine().split(" ");
			N = stoi(inputs[0]);
			M = stoi(inputs[1]);

			dist = new int[N][N];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (i == j)
						dist[i][j] = 0;
					else
						dist[i][j] = MAX;
				}
			}

			for (int i = 0; i < M; ++i) {
				inputs = in.readLine().split(" ");
				int a = stoi(inputs[0]) - 1;
				int b = stoi(inputs[1]) - 1;
				int c = stoi(inputs[2]);
				dist[a][b] = c;
				dist[b][a] = c;
			}

			floydwarshall();

			K = stoi(in.readLine());
			inputs = in.readLine().split(" ");
			sum = new int[N];
			for (int i = 0; i < K; ++i) {
				int value = stoi(inputs[i]) - 1;
				for (int j = 0; j < N; ++j) {
					sum[j] += dist[value][j];
				}
			}

			int idx = -1;
			int min = MAX;
			for (int i = 0; i < N; ++i) {
				if (sum[i] < min) {
					idx = i + 1;
					min = sum[i];
				}
			}
			sb.append(idx).append("\n");
		}
		System.out.println(sb);
	}

	private static void floydwarshall() {
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}