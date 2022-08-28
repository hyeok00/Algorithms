import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		String[] splitedLine = in.readLine().split(" ");
		int N = Integer.parseInt(splitedLine[0]);
		int M = Integer.parseInt(splitedLine[1]);

		// 초기화
		ArrayList<Integer>[] al = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; ++i)
			al[i] = new ArrayList<>();

		int[] count = new int[N + 1];
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			int first = Integer.parseInt(splitedLine[0]);
			int second = Integer.parseInt(splitedLine[1]);
			al[first].add(second);
			++count[second];
		}

		// 로직
		// Step1. 진입 차수가 0인 노드를 넣는다
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N + 1; ++i) {
			if (count[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node + " ");

			// Step2. 인접 노드의 진입 차수 1 감소
			for (int i = 0; i < al[node].size(); ++i) {
				int target = al[node].get(i);
				--count[target];
				if (count[target] == 0)
					q.add(target);
			}
		}
		System.out.println(sb);
	}
}