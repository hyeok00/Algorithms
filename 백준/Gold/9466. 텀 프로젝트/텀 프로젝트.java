import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N, count;
	static int[] arr;
	static boolean[] check, visit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(in.readLine());
			arr = new int[N + 1];
			check = new boolean[N + 1];
			visit = new boolean[N + 1];
			String[] splitedLine = in.readLine().split(" ");
			for (int i = 1; i <= N; ++i)
				arr[i] = Integer.parseInt(splitedLine[i - 1]);

			count = 0;
			for (int i = 1; i <= N; ++i)
				if (check[i] == false)
					dfs(i);
			sb.append(N-count).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int n) {
		if (visit[n]) {
			check[n] = true;
			count++;
		} else {
			visit[n] = true;
		}

		if (!check[arr[n]]) {
			dfs(arr[n]);
		}

		visit[n] = false;
		check[n] = true;
	}
}