import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int[][] map;
	static int RESULT, N;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		// 로직
		visit = new boolean[N];
		RESULT = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			visit[i] = true;
			dfs(i, i, 0, 0);
			visit[i] = false;
		}

		// 출력
		sb.append(RESULT);
		System.out.println(sb);
	}

	private static void dfs(int cur, int start, int depth, int sum) {
		if (sum > RESULT) {
			return;
		}
		if (depth + 1 == N) {
			if (map[cur][start] != 0) {
				RESULT = Math.min(sum + map[cur][start], RESULT);
			}
			return;
		}
		for (int i = 0; i < N; ++i) {
			if (visit[i] == false && map[cur][i] != 0) {
				visit[i] = true;
				dfs(i, start, depth + 1, sum + map[cur][i]);
				visit[i] = false;
			}
		}
	}
}