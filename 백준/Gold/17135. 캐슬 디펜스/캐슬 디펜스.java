import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int x;
	int y;

	public Pair() {
	}

	public Pair(int a, int b) {
		x = a;
		y = b;
	}
}

public class Main {
	static int N, M, RANGE, ENEMY, KILLCOUNT;
	static StringBuilder sb;
	static int[][] map;
	static int[] combArr;
	final static int MAXARCHER = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 입력부
		String[] spiltedLine = in.readLine().split(" ");
		N = Integer.parseInt(spiltedLine[0]);
		M = Integer.parseInt(spiltedLine[1]);
		RANGE = Integer.parseInt(spiltedLine[2]);

		map = new int[N][M];
		ENEMY = 0;
		for (int i = 0; i < N; ++i) {
			spiltedLine = in.readLine().split(" ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(spiltedLine[j]);
				if (map[i][j] == 1)
					++ENEMY;
			}
		}

		// 로직
		KILLCOUNT= 0;
		combArr = new int[MAXARCHER];
		comb(0);

		// 출력
		sb.append(KILLCOUNT);
		System.out.println(sb);
	}

	private static void comb(int depth) {
		if (MAXARCHER == depth) {
//			for (int it : combArr)
//				System.out.print(it + " ");
//			System.out.println();

			simulation();
			// 시뮬
			return;
		}

		for (int i = 0; i < M; i++) {
			boolean isVisit = false;
			for (int j = 0; j < depth; ++j) {
				if (combArr[j] >= i) {
					isVisit = true;
					break;
				}
			}
			if (isVisit)
				continue;

			combArr[depth] = i;
			comb(depth + 1);
		}
	}

	private static void simulation() {
		int[][] testMap = new int[N][M];
		deepCopy(map, testMap);
		int kill = 0;
		int currentEnemy = ENEMY;
		int curRow = N - 1;
		Pair[] targetPos = new Pair[3];
		for(int i=0; i<MAXARCHER; ++i) {
			targetPos[i]=new Pair(-1,-1);
		}
		while (true) {
			//System.out.println("current Enemy : " + currentEnemy);
			if (0 == currentEnemy) {
				break;
			}
			// Step1. 타겟선정
			for (int i = 0; i < MAXARCHER; ++i) {
				int[][] visit = new int[N][M];
				Queue<Pair> q = new LinkedList<>();
				q.add(new Pair(curRow, combArr[i]));
				visit[curRow][combArr[i]] = 1;
				while (!q.isEmpty()) {
					Pair p = q.peek();
					q.remove();
					if (checkDistance(curRow, combArr[i], p.x, p.y)) { // 사거리 안만 탐색
						if (testMap[p.x][p.y] == 1) // 적인 경우
						{
							targetPos[i].x = p.x;
							targetPos[i].y = p.y;
							break;
						}

						// 왼쪽
						if (0 <= p.y - 1 && visit[p.x][p.y - 1] == 0) {
							visit[p.x][p.y - 1] = visit[p.x][p.y] + 1;
							q.add(new Pair(p.x, p.y - 1));
						}
						// 위
						if (0 <= p.x - 1 && 0 <= p.y && visit[p.x - 1][p.y] == 0) {
							visit[p.x - 1][p.y] = visit[p.x][p.y] + 1;
							q.add(new Pair(p.x - 1, p.y));
						}
						// 오른쪽
						if (p.y + 1 < M && visit[p.x][p.y + 1] == 0) {
							visit[p.x][p.y + 1] = visit[p.x][p.y] + 1;
							q.add(new Pair(p.x, p.y + 1));
						}
					}
				}
			}
			// Step2. 타겟 제거
			for (int i = 0; i < MAXARCHER; ++i) {
				if (targetPos[i].x!= -1 && targetPos[i].y != -1 && 1 == testMap[targetPos[i].x][targetPos[i].y]) {
					testMap[targetPos[i].x][targetPos[i].y] = 0;
					kill++;
					currentEnemy--;
				}
			}
			// Step3. 지도 변경
			for (int i = 0; i < M; ++i) {
				if (testMap[curRow][i] == 1)
					--currentEnemy;
			}
			--curRow;
		}
		if (KILLCOUNT <= kill)
			KILLCOUNT = kill;
	}

	public static boolean checkDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2) <= RANGE-1;
	}

	public static void deepCopy(int[][] src, int[][] des) {
		for (int i = 0; i < src.length; ++i) {
			for (int j = 0; j < src[i].length; ++j)
				des[i][j] = src[i][j];
		}
	}
}
