import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, M, R;
	static int[] visit;
	static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);
		R = stoi(splitedLine[2]);

		visit = new int[N + 1];
		graph = new List[N + 1];
		for (int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		// graph 연결 관계 입력
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			graph[stoi(splitedLine[0])].add(stoi(splitedLine[1]));
			graph[stoi(splitedLine[1])].add(stoi(splitedLine[0]));
		}

		// 정점 번호를 오름차순으로 정렬해야 한다. (조건)
		for (int i = 1; i <= N; ++i) {
			Collections.sort(graph[i],Collections.reverseOrder());
		}
		Arrays.fill(visit, -1);
		visit[R] = 0;
		dfs(R, 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; ++i) {
			sb.append(visit[i])
				.append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int n, int depth) {
		int size = graph[n].size();
		for (int i = 0; i < size; ++i) {
			int next = graph[n].get(i);
			if (visit[next] == -1) {
				visit[next] = depth;
				dfs(next, depth + 1);
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}