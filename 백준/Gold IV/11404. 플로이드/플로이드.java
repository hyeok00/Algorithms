import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	final static int INF = 99999999;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		int[][] dp = new int[N][N];

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int src = Integer.parseInt(splitedLine[0]) - 1;
			int des = Integer.parseInt(splitedLine[1]) - 1;
			int weight = Integer.parseInt(splitedLine[2]);

			if (dp[src][des] == 0) {
				dp[src][des] = weight;
			} else if (dp[src][des] > weight) {
				dp[src][des] = weight;
			} else {

			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (i != j && dp[i][j] == 0)
					dp[i][j] = INF;
			}
		}

		for (int k = 0; k < N; ++k) { // 경유
			for (int i = 0; i < N; ++i) { // 출발
				if (k == i) { // 출발지와 경유지가 같은 경우는 생략
					continue;
				}

				for (int j = 0; j < N; ++j) { // 도착
					if (k == j || i == j) { // 경유지와 도착지가 같거나, 출발지와 도착지가 같은 경우 생략
						continue;
					}

					// i->j로 가는것보다 i->k->j로 가는 경우가 더 빠른 경우, 최단거리 갱신
					if (dp[i][j] > dp[i][k] + dp[k][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if(dp[i][j]>=INF)
					sb.append("0 ");
				else
					sb.append(dp[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}