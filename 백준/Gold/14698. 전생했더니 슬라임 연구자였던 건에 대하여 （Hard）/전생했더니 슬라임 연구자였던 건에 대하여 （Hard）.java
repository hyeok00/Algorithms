import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int N, T;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());

		// 로직
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(in.readLine());
			String[] splitedLine = in.readLine().split(" ");
			PriorityQueue<Long> pq = new PriorityQueue<>();
			for (int i = 0; i < N; ++i) {
				pq.add(Long.parseLong(splitedLine[i]));
			}
			long res = 1;
			while (pq.size() > 1) {
				long first = pq.poll();
				long second = pq.poll();
				long newOne = first * second;
				pq.add(newOne);
				res = (res * (newOne % 1000000007)) % 1000000007;
			}
			sb.append(res).append("\n");
		}
		// 출력부
		System.out.println(sb);
	}
}