import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N;

	static class Pair implements Comparable<Pair> {
		int first;
		int second;

		@Override
		public int compareTo(Pair o) {
			if (this.first == o.first)
				return this.second - o.second;
			else
				return this.first - o.first;
		}

		Pair(int a, int b) {
			first = a;
			second = b;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		List<Pair> list = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			String[] splitedLine = in.readLine().split(" ");
			list.add(new Pair(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1])));
		}
		Collections.sort(list);

		for (int i = 0; i < N; ++i) {
			Pair p = list.get(i);
			if (pq.isEmpty()) {
				pq.add(p.second);
			} else {
				int endTime = pq.peek();
				if (p.first < endTime) {
					pq.add(p.second);
				} else {
					pq.poll();
					pq.add(p.second);
				}
			}
		}
		System.out.println(pq.size());
	}
}