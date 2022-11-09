import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;

	static class Pair implements Comparable<Pair> {
		int first;
		int second;

		public Pair() {
		}

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.first == o.first)
				return this.second - o.first;
			else
				return o.first - this.first;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int maxDays = -1;
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			int price = Integer.parseInt(splitedLine[0]);
			int day = Integer.parseInt(splitedLine[1]);
			maxDays = maxDays < day ? day : maxDays;
			pq.add(new Pair(price, day));
		}

		int[] arr = new int[maxDays + 1];
		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			for (int iter = p.second; iter >= 1; --iter) {
				if (arr[iter] == 0) {
					arr[iter] = p.first;
					break;
				}
			}
		}
		int sum = 0;
		for (int it : arr) {
			sum += it;
		}
		sb.append(sum);
		System.out.println(sb);
	}
}