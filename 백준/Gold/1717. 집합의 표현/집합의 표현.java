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

		splitedLine = in.readLine().split(" ");
		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		makeSet(N);
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int operate = stoi(splitedLine[0]);
			int first = stoi(splitedLine[1]);
			int second = stoi(splitedLine[2]);

			if (operate == UNION) {
				union(first, second);
			} else if (operate == ISSAMESET) {
				if (find(first) == find(second))
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
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
		parents = new int[num + 1];
		for (int i = 0; i <= num; ++i) {
			parents[i] = i;
		}
	}

	private static int stoi(String string) {
		return Integer.parseInt(string);
	}
}