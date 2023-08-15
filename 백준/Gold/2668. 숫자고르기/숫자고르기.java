import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[] arr;
	static boolean[] check, visit;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		arr = new int[N + 1];
		check = new boolean[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; ++i)
			arr[i] = Integer.parseInt(in.readLine());

		list = new ArrayList<>();
		for (int i = 1; i <= N; ++i)
			if (check[i] == false)
				dfs(i);

		sb.append(list.size()).append("\n");
		Collections.sort(list);
		for (Integer i : list)
			sb.append(i).append("\n");
		System.out.println(sb);
	}

	public static void dfs(int n) {
		if (visit[n]) {
			check[n] = true;
			list.add(n);
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