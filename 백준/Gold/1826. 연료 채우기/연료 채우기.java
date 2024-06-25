import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, L, P;

	static class Pair {
		int first;
		int second;

		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
		N = stoi(in.readLine());

		// 거리순 정렬
		PriorityQueue<Pair> sortByDist = new PriorityQueue<>((a, b) -> a.first - b.first);
		for (int i = 0; i < N; ++i) {
			String[] inputs = in.readLine().split(" ");
			sortByDist.add(new Pair(stoi(inputs[0]), stoi(inputs[1])));
		}
		String[] inputs = in.readLine().split(" ");
		L = stoi(inputs[0]);
		P = stoi(inputs[1]);

		// 충전량이 많은 순
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.second - a.second);

		int curPosition = 0;
		int stopCount = 0;

		while (true) {
			// 현재 이동 가능한 주유소를 모두 pq에 추가한다.
			while (!sortByDist.isEmpty() && sortByDist.peek().first <= curPosition + P)
				pq.add(sortByDist.poll());

			// 이동 가능한 주유소가 없다.
			if (pq.isEmpty())
				break;

			// 도착지에 도달 가능하다.
			if (curPosition + P >= L)
				break;

			Pair cur = pq.poll();
			stopCount++;
			if (curPosition < cur.first) {
				// 가는 길에 있는경우
				P -= (cur.first - curPosition); // 이동비용
				P += cur.second; // 보충
				curPosition = cur.first; // 위치갱신
			} else {
				// 이전에 들렸어야 했던 경우
				P += cur.second;
			}
		}

		if (curPosition + P >= L)
			System.out.println(stopCount);
		else
			System.out.println(-1);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}