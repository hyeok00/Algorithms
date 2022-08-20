import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public void solution() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedStr = input.readLine().split(" ");

		int size = Integer.parseInt(splitedStr[0]);
		int start = Integer.parseInt(splitedStr[1]);
		int goal = Integer.parseInt(splitedStr[2]);
		int upWeight = Integer.parseInt(splitedStr[3]);
		int downWeight = Integer.parseInt(splitedStr[4]);

		int[] visited = new int[size + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		++visited[start];

		int res = -1;
		while (!q.isEmpty()) {
			int curFloor = q.poll();
			if (curFloor == goal) {
				res = visited[curFloor];
				break;
			}
			int nextUp = curFloor + upWeight;
			int nextDown = curFloor - downWeight;
			if (nextUp <= size && visited[nextUp] == 0) {
				visited[nextUp] = visited[curFloor] + 1;
				q.add(nextUp);
			}
			if (nextDown > 0 && visited[nextDown] == 0) {
				visited[nextDown] = visited[curFloor] + 1;
				q.add(nextDown);
			}
		}
		if (res != -1) {
			System.out.println(res - 1);
		} else {
			System.out.println("use the stairs");
		}
	}

	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}