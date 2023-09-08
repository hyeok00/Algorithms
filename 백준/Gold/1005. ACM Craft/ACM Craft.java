import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, K, target;
	static int[] time, cost, inCount;

	static List<Integer>[] to;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = stoi(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; ++tc) {
			String[] splitedLine = in.readLine().split(" ");
			N = stoi(splitedLine[0]);
			K = stoi(splitedLine[1]);
			time = new int[N + 1]; // 각 건물을 건설하는데 걸리는 시간
			cost = new int[N + 1]; // 해당 건물을 건설했을때, 총 걸린 시간
			inCount = new int[N + 1];


			splitedLine = in.readLine().split(" ");
			for (int i = 1; i <= N; ++i)
				time[i] = stoi(splitedLine[i - 1]);

			to = new List[N + 1];
			for (int i = 0; i <= N; ++i)
				to[i] = new ArrayList<>();


			for (int i = 0; i < K; ++i) {
				splitedLine = in.readLine().split(" ");
				int first = stoi(splitedLine[0]);
				int second = stoi(splitedLine[1]);
				inCount[second]++;
				to[first].add(second);
			}

			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i < N + 1; ++i) {
				if (inCount[i] == 0) { // 가장 먼저 지을 수 있는 건물이다.
					cost[i] = time[i]; // 초기 건물의 지었을때의 비용 계산.
					q.add(i);
				}
			}
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int i = 0; i < to[cur].size(); ++i) { // 해당 건물을 짓고 어떤 건물을 다음에 지을 수 있는지 확인
					int next = to[cur].get(i);
					// 다음 건물을 지었을때의 비용을 계산
					// 필요한 상위 건물의 소요 시간 중 최대 + 현재 건물 건설 시간 = 현재 건물의 완성했을 때의 총 소요시간.
					cost[next] = Math.max(cost[next], cost[cur] + time[next]);
					inCount[next]--;
					if(inCount[next]==0)
						q.add(next);
				}
			}

			target = stoi(in.readLine());
			sb.append(cost[target]).append("\n");
		}
		System.out.println(sb);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}