import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
	static String[] splitedLine;
	static StringBuilder sb;
	static List<Integer>[] inList, outList;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			inList = new ArrayList[M + 1];
			outList = new ArrayList[M + 1];
			for (int i = 0; i <= M; ++i) {
				inList[i] = new ArrayList<>();
				outList[i] = new ArrayList<>();
			}
			for (int i = 1; i <= M; ++i) {
				splitedLine = in.readLine().split(" ");
				int first = Integer.parseInt(splitedLine[0]);
				int second = Integer.parseInt(splitedLine[1]);
				inList[first].add(second);
				outList[second].add(first);
			}

			// 탐색
			int result = 0;
			for (int i = 1; i <= N; ++i) {
				int inCount = search(inList, i, N);
				int outCount = search(outList, i, N);
				if (inCount + outCount == N - 1)
					result++;
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static int search(List<Integer>[] list, int start, int N) {
		boolean[] isVisit = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		isVisit[start] = true;
		int count = 0;

		while (!q.isEmpty()) {
			int current = q.poll();
			int size = list[current].size();
			for (int i = 0; i < size; ++i) {
				int value = list[current].get(i);
				if (false == isVisit[value]) {
					isVisit[value] = true;
					q.add(value);
					count++;
				}
			}
		}

		return count;
	}
}