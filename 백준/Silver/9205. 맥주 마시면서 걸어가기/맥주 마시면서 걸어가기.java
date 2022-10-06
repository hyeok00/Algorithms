import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static String[] splitedLine;
	static StringBuilder sb;

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (first != other.first)
				return false;
			if (second != other.second)
				return false;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			int N = Integer.parseInt(in.readLine());
			Pair[] pl = new Pair[N + 2]; // 0 : 집, N+1 : 목적지
			for (int i = 0; i < N + 2; ++i) {
				splitedLine = in.readLine().split(" ");
				pl[i] = new Pair(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]));
			}

			Queue<Pair> q = new LinkedList<>();
			q.add(pl[0]);
			boolean[] isVisit = new boolean[N + 2];
			isVisit[0] = true;

			boolean flag = false;
			while (!q.isEmpty()) {
				Pair current = q.poll();
				if (current.equals(pl[N + 1])) {
					flag = true;
					break;
				}

				for (int k = 1; k < N + 2; ++k) {
					if (current.equals(pl[k])) {
						continue;
					}
					if (isVisit[k] == false && getDistance(current, pl[k]) <= 1000) {
						isVisit[k] = true;
						q.add(pl[k]);
					}
				}
			}
			if (flag)
				sb.append("happy\n");
			else
				sb.append("sad\n");
		}
		System.out.println(sb);
	}

	static int getDistance(Pair p1, Pair p2) {
		return Math.abs(p1.first - p2.first) + Math.abs(p1.second - p2.second);
	}
}