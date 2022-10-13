import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedStr = in.readLine().split(" ");

		int SIZE = 100000;
		int start = Integer.parseInt(splitedStr[0]);
		int goal = Integer.parseInt(splitedStr[1]);

		int[] visited = new int[SIZE + 1];
		for (int i = 0; i < SIZE + 1; ++i) {
			visited[i] = -1;
		}
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		++visited[start];

		while (!q.isEmpty()) {
			int pos = q.poll();
			if (pos == goal) {
				break;
			}
			int nextUp = pos + 1;
			int nextDown = pos - 1;
			int nextJump = pos * 2;
			if (nextJump <= SIZE && visited[nextJump] == -1) {
				visited[nextJump] = visited[pos];
				q.add(nextJump);
			}
			if (nextJump <= SIZE && visited[nextJump] != -1) { // 점프로 방문시 더 빠른경우
				if (visited[nextJump] > visited[pos])
					visited[nextJump] = visited[pos];
			}
			if (nextDown >= 0 && visited[nextDown] == -1) {
				visited[nextDown] = visited[pos] + 1;
				q.add(nextDown);
			}
			if (nextUp <= SIZE && visited[nextUp] == -1) {
				visited[nextUp] = visited[pos] + 1;
				q.add(nextUp);
			}
		}
		System.out.println(visited[goal]);
	}
}