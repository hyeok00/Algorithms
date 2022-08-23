import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;
	static int[] arr;
	final static int UNION = 0;
	final static int FIND = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int T = Integer.parseInt(in.readLine());
		String[] splitedLine;
		for (int tc = 1; tc <= T; ++tc) {
			splitedLine = in.readLine().split(" ");
			int N = Integer.parseInt(splitedLine[0]);
			int M = Integer.parseInt(splitedLine[1]);

			arr = new int[N + 1];
			for (int i = 0; i < N + 1; ++i) {
				makeSet(i);
			}

			// 로직
			sb.append("#" + tc + " ");
			for (int i = 0; i < M; ++i) {
				splitedLine = in.readLine().split(" ");
				int operation = Integer.parseInt(splitedLine[0]);
				int first = Integer.parseInt(splitedLine[1]);
				int second = Integer.parseInt(splitedLine[2]);

				if (operation == UNION) {
					unionSet(first, second);
				} else {
					if (findSet(first) == findSet(second))
						sb.append("1");
					else
						sb.append("0");
				}
			}
			sb.append("\n");
		}

		// 출력부
		System.out.println(sb);
	}

	public static void makeSet(int x) {
		arr[x] = x;
	}

	public static int findSet(int x) {
		if (arr[x] == x)
			return x;
		else
			return arr[x] = findSet(arr[x]);
	}

	public static void unionSet(int x, int y) {
		if (findSet(x) == findSet(y)) {
			return;
		}
		arr[findSet(x)] = findSet(y);
	}
}
