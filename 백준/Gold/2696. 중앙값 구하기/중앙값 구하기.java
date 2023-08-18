import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static StringBuilder sb;
	static int M;

	static PriorityQueue<Integer> minPq;
	static PriorityQueue<Integer> maxPq;
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = stoi(in.readLine());

		minPq = new PriorityQueue<>();
		maxPq = new PriorityQueue<>(Collections.reverseOrder());
		list = new ArrayList<>();

		for (int tc = 0; tc < T; ++tc) {
			M = stoi(in.readLine());
			String[] splitedLine = in.readLine().split(" ");

			minPq.clear();
			maxPq.clear();
			list.clear();

			for (int i = 1; i <= M; ++i) {
				int cur = getMiddle(stoi(splitedLine[(i - 1) % 10]));
				if (i % 2 == 1) {
					list.add(cur);
				}
				if (i % 10 == 0 && M > 10)
					splitedLine = in.readLine().split(" ");
			}

			sb.append(list.size())
				.append("\n");

			int count = 0;
			for (Integer value : list) {
				sb.append(value)
					.append(" ");
				count++;
				if (count % 10 == 0)
					sb.append("\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int getMiddle(int cur) {
		if (!minPq.isEmpty() && cur > minPq.peek()) {
			maxPq.add(minPq.poll());
			minPq.add(cur);
		} else {
			maxPq.add(cur);
		}

		if (maxPq.size() > minPq.size() + 1) {
			minPq.add(maxPq.poll());
		}
		return maxPq.peek();
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}