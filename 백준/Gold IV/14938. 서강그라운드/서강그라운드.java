import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, M, R;
	static int[] data;
	static int[][] arr;
	static int INF = 123456789;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);
		R = stoi(inputs[2]);

		inputs = in.readLine().split(" ");
		data = new int[N];
		for (int i = 0; i < N; ++i)
			data[i] = stoi(inputs[i]);

		arr = new int[N][N];
		for (int i = 0; i < N; ++i) {
			Arrays.fill(arr[i], INF);
			arr[i][i] = 0;
		}

		for (int i = 0; i < R; ++i) {
			inputs = in.readLine().split(" ");
			int a = stoi(inputs[0]) - 1;
			int b = stoi(inputs[1]) - 1;
			int cost = stoi(inputs[2]);
			arr[a][b] = cost;
			arr[b][a] = cost;
		}

		// 탐색
		for (int k = 0; k < N; ++k) { // 경유
			for (int i = 0; i < N; ++i) { // 출발
				if (k == i) // 출발지와 경유지가 같은 경우는 생략
					continue;

				for (int j = 0; j < N; ++j) { // 도착
					if (k == j || i == j)  // 경유지와 도착지가 같거나, 출발지와 도착지가 같은 경우 생략
						continue;

					// i->j로 가는것보다 i->k->j로 가는 경우가 더 빠른 경우, 최단거리 갱신
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}

		int max = 0;
		for (int i = 0; i < N; ++i) {
			int sum = 0;
			for (int j = 0; j < N; ++j) {
				if (arr[i][j] <= M)
					sum += data[j];
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}