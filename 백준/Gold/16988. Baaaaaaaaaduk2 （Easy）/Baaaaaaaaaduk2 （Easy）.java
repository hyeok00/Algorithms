import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M;
	static List<Pair> list;

	static class Pair {
		int first;
		int second;

		Pair() {

		}

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);

		int[][] map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
			}
		}

		list = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				boolean flag = false;
				for (int k = 0; k < 4; ++k) {
					int nextX = i + "0211".charAt(k) - '1';
					int nextY = j + "1102".charAt(k) - '1';
					if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
						if (map[nextX][nextY] == 2 && map[i][j]==0) {
							flag = true;
							break;
						}
					}
				}
				if (flag)
					list.add(new Pair(i, j));
			}
		}

		int max = 0;
		if(list.size()>=2) {
		for (int i = 0; i < list.size(); ++i) {
			for (int j = 0; j < list.size(); ++j) {
				if (i == j)
					continue;
				int result = simulation(i, j, map);
				if(max<=result) {
					max = result;
//					System.out.println(list.get(i).first + " "+list.get(i).second);
//					System.out.println(list.get(j).first + " "+list.get(j).second);
//					System.out.println(max);
				}
//				System.out.println(simulation(i, j, map));
			}
		}}else if(list.size()==1) {
			max = simulation(0, 0, map);
		}else {
			max= 0;
		}

		System.out.println(max);
	}

	private static int simulation(int i, int j, int[][] map) {
		map[list.get(i).first][list.get(i).second] = 1;
		map[list.get(j).first][list.get(j).second] = 1;
		int sum = 0;
		int[][] visit = new int[N][M];
		for (int k = 0; k < 4; ++k) {
			int startX = list.get(i).first + "0211".charAt(k) - '1';
			int startY = list.get(i).second + "1102".charAt(k) - '1';
			if (0 <= startX && startX < N && 0 <= startY && startY < M) {
				if (map[startX][startY] == 2 && visit[startX][startY] == 0) {
					Queue<Pair> q = new ArrayDeque<>();
					q.add(new Pair(startX, startY));
					visit[startX][startY] = 1;
					int count = 1;
					boolean flag = false;
					while (!q.isEmpty()) {
						Pair p = q.poll();
						for (int t = 0; t < 4; ++t) {
							int nextX = p.first + "0211".charAt(t) - '1';
							int nextY = p.second + "1102".charAt(t) - '1';
							if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
								if (map[nextX][nextY] == 0) {
									count = 0;
									flag = true;
									break;
								}
								if (map[nextX][nextY] == 2 && visit[nextX][nextY]==0) {
									count++;
									visit[nextX][nextY] = 1;
									q.add(new Pair(nextX, nextY));
								}
							}
						}
						if (flag)
							break;
					}
					if (count != 0)
						sum += count;
				}
			}
		}

		for (int k = 0; k < 4; ++k) {
			int startX = list.get(j).first + "0211".charAt(k) - '1';
			int startY = list.get(j).second + "1102".charAt(k) - '1';
			if (0 <= startX && startX < N && 0 <= startY && startY < M) {
				if (map[startX][startY] == 2 && visit[startX][startY] == 0) {
					Queue<Pair> q = new ArrayDeque<>();
					q.add(new Pair(startX, startY));
					visit[startX][startY] = 1;
					int count = 1;
					boolean flag = false;
					while (!q.isEmpty()) {
						Pair p = q.poll();
						for (int t = 0; t < 4; ++t) {
							int nextX = p.first + "0211".charAt(t) - '1';
							int nextY = p.second + "1102".charAt(t) - '1';
							if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
								if (map[nextX][nextY] == 0) {
									count = 0;
									flag = true;
									break;
								}
								if (map[nextX][nextY] == 2 && visit[nextX][nextY]==0) {
									count++;
									visit[nextX][nextY] = 1;
									q.add(new Pair(nextX, nextY));
								}
							}
						}
						if (flag)
							break;
					}
					if (count != 0)
						sum += count;
				}
			}
		}
		map[list.get(i).first][list.get(i).second] = 0;
		map[list.get(j).first][list.get(j).second] = 0;
		return sum;
	}
}