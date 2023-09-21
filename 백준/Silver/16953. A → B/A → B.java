import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		int A = stoi(splitedLine[0]);
		int B = stoi(splitedLine[1]);

		Set<Integer> done = new HashSet<>();
		Queue<Integer> q = new ArrayDeque<>();

		q.add(B);
		done.add(B);
		int depth = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int value = q.poll();

				if (value == A) {
					System.out.println(depth);
					return;
				}

				if(value %2==0) {
					int case1Value = value / 2;
					if (!done.contains(case1Value)) {
						q.add(case1Value);
						done.add(case1Value);
					}
				}

				if(value %10 ==1) {
					int case2Value = value / 10;
					if (!done.contains(case2Value)) {
						q.add(case2Value);
						done.add(case2Value);
					}
				}
			}
			depth++;
		}
		System.out.println(-1);

	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}
}