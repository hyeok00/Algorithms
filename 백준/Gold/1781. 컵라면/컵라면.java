import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
	static int N;
	static String[] splitedLine;

	static class Pair implements Comparable<Pair> {
		int first;
		int second;

		Pair(int a, int b) {
			first = a;
			second = b;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.second == o.second) {
				return o.first - this.first;
			} else {
				return o.second - this.second;
			}
		}

		public int getFirst() {
			return first;
		}

		public int getSecond() {
			return second;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		int maxDay = -1;
		List<Pair> lists = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			int deadline = Integer.parseInt(splitedLine[0]);
			maxDay = deadline > maxDay ? deadline : maxDay;
			int reward = Integer.parseInt(splitedLine[1]);
			lists.add(new Pair(deadline, reward));
		}
		List<Pair> sorted = lists.stream()
			.sorted(Comparator.comparing(Pair::getFirst).reversed().thenComparing(Pair::getSecond))
			.collect(Collectors.toList());

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int idx = 0;
		long res = 0;
		for (int i = maxDay; i > 0; --i) {
			while (true) {
				if (idx < sorted.size() && sorted.get(idx).first >= i) {
					pq.add(sorted.get(idx));
					idx++;
				} else
					break;
			}
			if (!pq.isEmpty()) {
				Pair p = pq.poll();
				res += p.second;
			}
		}
		System.out.println(res);
	}
}