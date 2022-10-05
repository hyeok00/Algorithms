import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M;
	static String[] splitedLine;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		map = new int[N][M];

		int count = 0;
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				if(map[i][j]==1)
					count++;
			}
		}
		simulation(0, count);
	}

	private static void simulation(int depth, int prevCount) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(depth, depth));

		boolean[][] isVisit = new boolean[N][M];
		isVisit[depth][depth] = true;
		while (!q.isEmpty()) {
			Pair curruent = q.poll();

			for (int i = 0; i < 4; ++i) {
				int nextX = curruent.first + dx[i];
				int nextY = curruent.second + dy[i];
				if (depth <= nextX && nextX < N && depth <= nextY && nextY < M) {
					if (false == isVisit[nextX][nextY]) {
						if (map[nextX][nextY] == 0) {
							isVisit[nextX][nextY] = true;
							q.add(new Pair(nextX, nextY));
						} else if (map[nextX][nextY] == 1) {
							isVisit[nextX][nextY] = true;
							map[nextX][nextY] = 0;
						}
					}
				}
			} // End For
		} // End While

		int count = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] == 1)
					count++;
			}
		}
		
		if(count==0) {
			StringBuilder sb = new StringBuilder();
			sb.append(depth+1);
			sb.append("\n");
			sb.append(prevCount);
			System.out.println(sb);
			return;
		}
		simulation(depth + 1, count);
	}
}