import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;
	static List<Long> list;
	static int[] num = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		list = new ArrayList<>();
		dfs(0, 0);
		Collections.sort(list);

		if (N > 1023)
			System.out.println(-1);
		else
			System.out.println(list.get(N - 1));

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static void dfs(long value, int depth) {
		if (!list.contains(value)) {
			list.add(value);
		}

		if (depth >= 10) {
			return;
		}

		dfs((value * 10) + num[depth], depth + 1);
		dfs(value, depth + 1);

	}
}