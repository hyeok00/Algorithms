import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Solution {
	static StringBuilder sb;
	static int N, M, T, RESULT;
	static ArrayList<Integer>[] al;
	static boolean[] isVisit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(in.readLine());
		sb = new StringBuilder();
		for (int tc = 1; tc <= T; ++tc) {
			// 입력부
			String[] splitedLine = in.readLine().split(" ");
			N = Integer.parseInt(splitedLine[0]);
			M = Integer.parseInt(splitedLine[1]);

			al = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; ++i) {
				al[i] = new ArrayList<>();
			}
			isVisit = new boolean[N + 1];

			for (int i = 0; i < M; ++i) {
				splitedLine = in.readLine().split(" ");
				int first = Integer.parseInt(splitedLine[0]);
				int second = Integer.parseInt(splitedLine[1]);
				al[first].add(second);
				al[second].add(first);
			}
			// 로직
			RESULT = 0;
			for (int i = 1; i <= N; ++i) {
				if (isVisit[i] == false) {
					RESULT++;
					search(i);
				}
			}

			sb.append("#" + tc + " " + RESULT + "\n");
		}
		// 출력
		System.out.println(sb);
	}

	private static void search(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		isVisit[n] = true;
		while (!q.isEmpty()) {
			int front = q.peek();
			q.remove();

			for (int i = 0; i < al[front].size(); ++i) {
				int value = al[front].get(i);
				if (isVisit[value] == false) {
					isVisit[value] = true;
					q.add(value);
				}
			}
		}
	}
}