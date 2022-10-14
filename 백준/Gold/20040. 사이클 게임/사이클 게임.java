import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int[] parents;

	public static void makeNode(int n) {
		parents = new int[n];
		for (int i = 0; i < n; ++i) {
			parents[i] = i;
		}
	}

	public static int findParent(int n) {
		if (parents[n] == n)
			return n;
		return parents[n] = findParent(parents[n]);
	}

	public static boolean unionNode(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		makeNode(N);
		int result = 0;
		for (int i = 0; i < M; ++i) {
			++result;
			splitedLine = in.readLine().split(" ");
			int first = Integer.parseInt(splitedLine[0]);
			int second = Integer.parseInt(splitedLine[1]);

			if (!unionNode(first, second)) {
				System.out.println(result);
				return;
			}
		}
		System.out.println(0);
	}
}