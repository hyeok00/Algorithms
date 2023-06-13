import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, K;

	public static class Pair implements Comparable<Pair> {
		public int first;
		public int second;

		@Override
		public int compareTo(Pair o) {
			return this.first - o.first;
		}

		Pair(int a, int b) {
			first = a;
			second = b;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		K = Integer.parseInt(splitedLine[1]);
		List<Pair> list = new ArrayList<>();
		List<Integer> bag = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			list.add(new Pair(Integer.parseInt(splitedLine[0]), Integer.parseInt(splitedLine[1])));
		}
		for (int i = 0; i < K; ++i) {
			bag.add(Integer.parseInt(in.readLine()));
		}
		Collections.sort(list);
		Collections.sort(bag);

		long res = 0;
		int idx = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (int i = 0; i < K; ++i) {
			int maxWeight = bag.get(i);
			while (idx < N && list.get(idx).first <= maxWeight) {
				pq.add(list.get(idx).second);
				idx++;
			}
			if (!pq.isEmpty()) {
				res += pq.poll();
			}
		}
		System.out.println(res);
	}
}