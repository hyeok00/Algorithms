import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static class Pair implements Comparable<Pair> {
		int first;
		int second;

		public Pair() {
		}

		public Pair(String first, String second) {
			this.first = Integer.parseInt(first);
			this.second = Integer.parseInt(second);
		}

		@Override
		public int compareTo(Pair o) {
			if (this.second == o.second)
				return this.first - o.first;
			else
				return this.second - o.second;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			pq.add(new Pair(splitedLine[0], splitedLine[1]));
		}
		int value = -1;
		int count = 0;

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			if (p.first >= value) {
				value = p.second;
				count++;
			}
		}

		sb.append(count);
		System.out.println(sb);
	}
}