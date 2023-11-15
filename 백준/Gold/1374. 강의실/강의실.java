import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N;

	static class Lecture implements Comparable<Lecture> {
		int no;
		int start;
		int end;

		Lecture(String[] str) {
			no = stoi(str[0]);
			start = stoi(str[1]);
			end = stoi(str[2]);
		}

		public int compareTo(Lecture o) {
			if (this.start == o.start)
				return this.end - o.end;
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = stoi(in.readLine());
		Lecture[] list = new Lecture[N];

		for (int i = 0; i < N; ++i)
			list[i] = new Lecture(in.readLine().split(" "));

		Arrays.sort(list);

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; ++i) {
			if (pq.isEmpty()) {
				pq.add(list[i].end);
			} else {
				int endTime = pq.peek();
				if (list[i].start < endTime) {
					pq.add(list[i].end);
				} else {
					pq.poll();
					pq.add(list[i].end);
				}
			}
		}
		System.out.println(pq.size());
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}