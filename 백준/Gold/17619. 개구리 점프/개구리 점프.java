import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, K;
	static StringBuilder sb;

	static int[] parent;

	static class Log implements Comparable<Log> {
		int no;
		int start;
		int end;

		public Log(int c, int a, int b) {
			no = c;
			start = a;
			end = b;
		}

		public void merge(Log l) {
			this.start = Math.min(this.start, l.start);
			this.end = Math.max(this.end, l.end);
			parent[l.no] = this.no;
		}

		public boolean isOverlap(Log l) {
			if (this.start <= l.start && l.start <= this.end) {
				return true;
			}
			if (this.start <= l.end && l.end <= this.end) {
				return true;
			}
			return false;
		}

		@Override
		public int compareTo(Log o) {
			if (this.start == o.start)
				return o.end - this.end;
			return this.start - o.start;
		}
	}

	public static int find(int i) {
		if (i == parent[i])
			return i;
		else
			return parent[i] = find(parent[i]);
	}

	static Log[] data;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		K = Integer.parseInt(splitedLine[1]);

		PriorityQueue<Log> pq = new PriorityQueue<>();

		data = new Log[N + 1];
		parent = new int[N + 1];
		for (int i = 1; i <= N; ++i) {
			parent[i] = i;
			splitedLine = in.readLine().split(" ");
			data[i] = new Log(i, Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1]));
			pq.add(data[i]);
		}

		while (pq.size() > 1) {
			Log first = pq.poll();
			Log second = pq.poll();
			if (first.isOverlap(second)) {
				first.merge(second);
				pq.add(first);
			}
			else
				pq.add(second);
		}

		for (int i = 0; i < K; ++i) {
			splitedLine = in.readLine().split(" ");
			int a = Integer.parseInt(splitedLine[0]);
			int b = Integer.parseInt(splitedLine[1]);
			if (parent[a] == parent[b])
				sb.append("1");
			else
				sb.append("0");
			sb.append("\n");
		}
		System.out.println(sb);
	}
}