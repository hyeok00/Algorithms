import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N, M;

	static List<Integer>[] graph;
	static int[] inCount;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);

		inCount = new int[N + 1];
		graph = new List[N + 1];
		for (int i = 0; i < N + 1; ++i)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int nums = stoi(splitedLine[0]);
			for (int j = 1; j < nums; ++j) {
				int start = stoi(splitedLine[j]);
				int end = stoi(splitedLine[j + 1]);
				inCount[end]++;
				graph[start].add(end);
			}
		}

		Queue<Integer> q = new PriorityQueue<>();
		for (int i = 1; i <= N; ++i) {
			if (inCount[i] == 0)
				q.add(i);
		}

		StringBuilder sb = new StringBuilder();
		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();

			sb.append(cur).append("\n");
			count++;
			int size = graph[cur].size();
			for (int i = 0; i < size; ++i) {
				int next = graph[cur].get(i);
				inCount[next]--;
				if (inCount[next] == 0)
					q.add(next);
			}

		}
		if (count != N)
			System.out.println(0);
		else
			System.out.println(sb);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}