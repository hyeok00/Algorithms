import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int N, M, result;
	static int[] parents;

	final static int UNION = 0;
	final static int ISSAMESET = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());

		makeSet(N);
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = i + 1; j < N; ++j) {
				int value = Integer.parseInt(splitedLine[j]);
				if (value == 1) {
					union(i, j);
				}
			}
		}

		boolean flag = true;
		splitedLine = in.readLine().split(" ");
		int root = find(Integer.parseInt(splitedLine[0]) - 1);
		for (int i = 0; i < M; ++i) {
			int value = Integer.parseInt(splitedLine[i]) - 1;
			if (root != find(value))
				flag = false;
		}
		if (flag)
			sb.append("YES");
		else
			sb.append("NO");

		System.out.println(sb);
	}

	private static void union(int first, int second) {
		int aRoot = find(first);
		int bRoot = find(second);

		if (aRoot == bRoot) {
			// none
		} else
			parents[bRoot] = aRoot;
	}

	private static int find(int first) {
		if (parents[first] == first)
			return first;

		return parents[first] = find(parents[first]);
	}

	private static void makeSet(int num) {
		parents = new int[num];
		for (int i = 0; i < num; ++i) {
			parents[i] = i;
		}
	}
}