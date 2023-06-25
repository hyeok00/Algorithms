import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	static class Cow implements Comparable<Cow> {
		int arrive;
		int time;

		public Cow(int a, int b) {
			arrive = a;
			time = b;
		}

		@Override
		public int compareTo(Cow o) {
			return this.arrive - o.arrive;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Cow> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			pq.add(new Cow(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1])));
		}

		// 로직
		int cur = 0;
		while (!pq.isEmpty()) {
			Cow c = pq.poll();
			if (cur < c.arrive) {
				cur = c.arrive;
			}
			cur += c.time;

		}
		sb.append(cur);
		// 출력부
		System.out.println(sb);
	}
}