import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Solution {
	static StringBuilder sb;
	static int T, N, START, RESULT;
	static ArrayList<Integer>[] al;
	static int[] visit;
	final static int SIZE = 101;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		T = 10;
		sb = new StringBuilder();
		for (int tc = 1; tc <= T; ++tc) {
			// 초기화
			RESULT = 0;
			al = new ArrayList[SIZE];
			for (int i = 0; i < SIZE; ++i) {
				al[i] = new ArrayList<>();
			}
			visit = new int[SIZE];

			// 입력부
			String[] splitedLine = in.readLine().split(" ");
			N = Integer.parseInt(splitedLine[0]);
			START = Integer.parseInt(splitedLine[1]);

			splitedLine = in.readLine().split(" ");
			for (int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(splitedLine[i]);
				int to = Integer.parseInt(splitedLine[i + 1]);
				al[from].add(to);
			}

			// 로직
			bfs(START);
			sb.append("#" + tc + " " + RESULT + "\n");
		}

		// 출력
		System.out.println(sb);
	}

	private static void bfs(int n) {
		Deque<Integer> q = new LinkedList<>();
		q.add(n);

		visit[n] = 1;
		int prevDepth = 0;
		while (!q.isEmpty()) {
			int value = q.peek();
			q.remove();

			int depth = visit[value];
			if (prevDepth < depth) {
				RESULT = 0;
				prevDepth = depth;
			}
			if (prevDepth == depth && value >= RESULT)
				RESULT = value;

			for (int i = 0; i < al[value].size(); ++i) {
				int innerValue = al[value].get(i);
				if (0 == visit[innerValue]) { // 방문한 적이 없는경우
					visit[innerValue] = visit[value] + 1; // 방문처리
					q.add(innerValue);
				}
			}
		}
	}
}