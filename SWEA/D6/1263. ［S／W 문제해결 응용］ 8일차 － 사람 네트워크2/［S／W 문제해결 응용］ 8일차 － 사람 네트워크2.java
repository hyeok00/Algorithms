import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb;
	static String[] splitedLine;

	final static int INF = 10000;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (i != j && map[i][j] == 0) {
						map[i][j] = INF;
					}
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
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}

			int result = INF;
			for (int i = 0; i < N; ++i) {
				int sum = 0;
				for (int j = 0; j < N; ++j) {
					sum += map[i][j];
				}
				if(result>sum)
					result=sum;
			}

			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}
}