import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static int N, K;
	static int[] sensors;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(in.readLine());
		K = stoi(in.readLine());
		sensors = new int[N];

		String[] splitedLine = in.readLine().split(" ");
		for (int i = 0; i < N; ++i)
			sensors[i] = stoi(splitedLine[i]);

		Arrays.sort(sensors);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N - 1; ++i)
			pq.add(sensors[i + 1] - sensors[i]);

		int result = 0;
		int count = pq.size() - K + 1;

		for (int i = 0; i < count; ++i)
			result += pq.poll();

		System.out.println(result);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}