import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int[] time, cost, inCount;

	static List<Integer>[] to;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = stoi(in.readLine());
		time = new int[N + 1];
		cost = new int[N + 1];
		inCount = new int[N + 1];
		to = new List[N + 1];
		for (int i = 0; i <= N; ++i)
			to[i] = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			int curNum = i + 1;
			String[] splitedLine = in.readLine().split(" ");
			time[curNum] = stoi(splitedLine[0]);
			int iterIdx = 1;
			while (true) {
				int num = stoi(splitedLine[iterIdx]);
				if (num == -1)
					break;

				to[num].add(curNum);
				inCount[curNum]++;
				iterIdx++;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i < N + 1; ++i) {
			if (inCount[i] == 0) {
				cost[i] = time[i];
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < to[cur].size(); ++i) {
				int next = to[cur].get(i);
				cost[next] = Math.max(cost[next], cost[cur] + time[next]);
				inCount[next]--;
				if (inCount[next] == 0)
					q.add(next);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i)
			sb.append(cost[i]).append("\n");

		System.out.println(sb);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}