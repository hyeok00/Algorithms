import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);
		int[] graph = new int[101];
		boolean[] isVisit = new boolean[101];

		for (int i = 0; i < N + M; ++i) {
			splitedLine = in.readLine().split(" ");
			int start = Integer.parseInt(splitedLine[0]);
			int end = Integer.parseInt(splitedLine[1]);
			graph[start] = end;
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		isVisit[1] = true;

		int depth = 0;
		boolean flag = false;
		while (!q.isEmpty()) {
			depth++;
			int size = q.size();
			while (size-- > 0) {
				int curPosition = q.poll();
				for (int i = 1; i <= 6; ++i) {
					int nextPosition = curPosition + i;
					if (nextPosition > 100)
						continue;
					if (nextPosition == 100) {
						flag = true;
						break;
					}
					if (isVisit[nextPosition] == false) {
						if (graph[nextPosition] != 0) {
							isVisit[nextPosition] = true;
							isVisit[graph[nextPosition]] = true;
							q.add(graph[nextPosition]);
						} else {
							isVisit[nextPosition] = true;
							q.add(nextPosition);
						}
					}
				}
			}
			if (flag)
				break;
		}
		System.out.println(depth);
	}
}