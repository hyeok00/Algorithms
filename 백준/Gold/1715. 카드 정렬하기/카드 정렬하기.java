import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(in.readLine()));
		}
		int count = 0;
		while (pq.size() > 1) {
			int prev = pq.poll();
			int current = pq.poll();
			count += prev + current;
			pq.add(prev + current);
		}
		//count += pq.poll();
		System.out.println(count);
	}
}