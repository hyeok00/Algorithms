import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static String[] splitedLine;
	static int R, C, M;
	static int[][] map;
	static int[] dx = { -1, 1 };
	static int[] dy = { 1, -1 };
	static Shark[] sl;
	static Person p;

	final static int UP = 1;
	final static int DOWN = 2;
	final static int RIGHT = 3;
	final static int LEFT = 4;

	static class Person {
		int r;
		int c;
		int sum;

		public Person(int r, int c, int sum) {
			this.r = r;
			this.c = c;
			this.sum = sum;
		}
	}

	static class Shark {
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean isCaptured;

		public Shark(int r, int c, int s, int d, int z, boolean isCaptured) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.isCaptured = isCaptured;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		splitedLine = in.readLine().split(" ");
		R = Integer.parseInt(splitedLine[0]);
		C = Integer.parseInt(splitedLine[1]);
		M = Integer.parseInt(splitedLine[2]);

		p = new Person(-1, -1, 0);
		map = new int[R][C];
		sl = new Shark[M+1];
		for (int i = 0; i < M; ++i) {
			splitedLine = in.readLine().split(" ");
			sl[i+1] = new Shark(Integer.parseInt(splitedLine[0]) - 1, Integer.parseInt(splitedLine[1]) - 1,
					Integer.parseInt(splitedLine[2]), Integer.parseInt(splitedLine[3]),
					Integer.parseInt(splitedLine[4]), false);
			map[sl[i+1].r][sl[i+1].c] = i+1;
		}

		simulation();
		System.out.println(sb);
	}

	private static void simulation() {
		while (true) {
			// Step1. 낚시왕이 움직인다.
			p.c += 1;
			if (p.c == C) {
				sb.append(p.sum);
				break;
			}

			// Step2. 낚시왕이 상어를 잡는다.
			for (int i = 0; i < R; ++i) {
				int targetIndex = map[i][p.c];
				if (targetIndex > 0) {
					sl[targetIndex].isCaptured = true;
					p.sum += sl[targetIndex].z;
					map[i][p.c] = 0;
					break;
				}
			}

			// Step3. 상어가 이동한다
			for (int i = 1; i <= M; ++i) {
				if (sl[i].isCaptured)
					continue;

				map[sl[i].r][sl[i].c] = 0;

				int spare;
				int speed = sl[i].s;
				int direction = sl[i].d;
				if (direction == UP || direction == DOWN) {
					spare = speed % ((R - 1) * 2);
					int k = direction - 1;
					while (spare-- > 0) {
						if (0 > sl[i].r + dx[k % 2] || sl[i].r + dx[k % 2] >= R)
							k += 1;
						sl[i].r = sl[i].r + dx[k % 2];
					}
					sl[i].d = k%2==0? UP:DOWN;
				} else {
					spare = speed % ((C - 1) * 2);
					int k = direction - 3;
					while (spare-- > 0) {
						if (0 > sl[i].c + dy[k % 2] || sl[i].c + dy[k % 2] >= C)
							k += 1;
						sl[i].c = sl[i].c + dy[k % 2];
					}
					sl[i].d = k%2==0? RIGHT:LEFT;
				}
			}

			for (int i = 1; i <= M; ++i) {
				if (sl[i].isCaptured)
					continue;

				if (map[sl[i].r][sl[i].c] == 0) {
					map[sl[i].r][sl[i].c] = i;
				} else {
					int idx = map[sl[i].r][sl[i].c];
					if (sl[idx].z > sl[i].z) {
						sl[i].isCaptured = true;
					} else {
						sl[idx].isCaptured = true;
						map[sl[i].r][sl[i].c] = i;
					}
				}
			}
		}
	}
}