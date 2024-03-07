import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, count;
	static List<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		String[] inputs = in.readLine().split(" ");

		list = new List[N];
		for (int i = 0; i < N; ++i)
			list[i] = new ArrayList<>();
		int root = -1;
		for (int i = 0; i < N; ++i) {
			int value = stoi(inputs[i]);
			if (value == -1) {
				root = i;
				continue;
			}
			list[value].add(i);
		}

		int remove = stoi(in.readLine());
		count = 0;
		dfs(root, remove);
		System.out.println(count);
	}

	private static int dfs(int start, int remove) {
		if (start == remove)
			return -1;
		if (list[start].size() == 0) {
			count++;
			return 0;
		}
		for (int i = 0; i < list[start].size(); i++) {
			int value = dfs(list[start].get(i), remove);
			if (value == -1 && list[start].size() == 1)
				count++;
		}
		return 0;
	}

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}