import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static String[] splitedLine;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(in.readLine());
			splitedLine = in.readLine().split(" ");
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 0; i < N; ++i) {
				pq.add(Long.parseLong(splitedLine[i]));
			}
			long res = 0;
			while (pq.size() > 1) {
				long first = pq.poll();
				long second = pq.poll();
				pq.add(first + second);
				res += first + second;
			}
			System.out.println(res);
		}

	}
}