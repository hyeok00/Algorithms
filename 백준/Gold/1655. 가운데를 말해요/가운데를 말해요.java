import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());

		PriorityQueue<Integer> minPq = new PriorityQueue<>();
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; ++i) {
			int value = Integer.parseInt(in.readLine());
			if(!minPq.isEmpty() && value > minPq.peek()){
				maxPq.add(minPq.poll());
				minPq.add(value);
			}else{
				maxPq.add(value);
			}

			if (maxPq.size() > minPq.size() + 1) {
				minPq.add(maxPq.poll());
			}

			sb.append(maxPq.peek()).append("\n");
		}
		System.out.println(sb);
	}
}