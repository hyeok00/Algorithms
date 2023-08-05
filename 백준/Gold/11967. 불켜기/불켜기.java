import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static class Pos {
		int x;
		int y;

		Pos() {
		}

		Pos(int a, int b) {
			x = a;
			y = b;
		}
	}

	static int N, M, result;
	static List<Pos>[][] graph;
	static boolean[][] isVisit, adjustArr, isOnLight;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		adjustArr = new boolean[N + 1][N + 1];
		isOnLight = new boolean[N + 1][N + 1];
		isVisit = new boolean[N + 1][N + 1];
		graph = new List[N + 1][N + 1];
		for (int i = 1; i <= N; ++i)
			for (int j = 1; j <= N; ++j)
				graph[i][j] = new ArrayList<>();

		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			graph[Integer.parseInt(splitedLine[0])][Integer.parseInt(splitedLine[1])].add(
				new Pos(Integer.parseInt(splitedLine[2]), Integer.parseInt(splitedLine[3])));
		}

		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(1, 1));
		isVisit[1][1] = true;
		adjustArr[1][1] = true;
		isOnLight[1][1] = true;

		result = 0;
		while (!q.isEmpty()) {

			Pos cur = q.poll();

			// Step1. 인접하여 움직일 수 있는방들을 표시.
			adjustCheck(cur);

			// Step2. 현재 위치에서 불을 켤 수 있는 방을 모두 확인.
			int size = graph[cur.x][cur.y].size();
			for (int i = 0; i < size; ++i) {
				Pos temp = graph[cur.x][cur.y].get(i);
				isOnLight[temp.x][temp.y] = true;
				// 만약 불을 켠곳이 이동가능한 곳이라면 큐에 추가한다.
				if (adjustArr[temp.x][temp.y] == true && isVisit[temp.x][temp.y] == false) {
					isVisit[temp.x][temp.y] = true;
					q.add(temp);
				}
			}

			// 현재위치에서 사방을 둘러보아 가본적이 없고, 불이 켜져있다면 간다.
			for (int i = 0; i < 4; ++i) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				if (isInRange(nextX, nextY) && isVisit[nextX][nextY] == false && isOnLight[nextX][nextY] == true) {
					q.add(new Pos(nextX, nextY));
					isVisit[nextX][nextY] = true;
				}
			}
		}
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				if (isOnLight[i][j] == true)
					result++;
			}
		}
		System.out.println(result);
	}

	private static void adjustCheck(Pos cur) {
		for (int d = 0; d < 4; ++d) {
			int nextX = cur.x + dx[d];
			int nextY = cur.y + dy[d];
			if (isInRange(nextX, nextY)) {
				adjustArr[nextX][nextY] = true;
			}
		}
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N)
			return true;
		return false;
	}

}