import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static int[][] map;
	public static int N, M, RESULT;
	public static String[] splitedLine;
	public static List<Pair> list;
	public static int[] combArr, valueArr;
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};

	public static class Pair {
		int x;
		int y;

		Pair() {
		}

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		splitedLine = in.readLine().split(" ");
		N = Integer.parseInt(splitedLine[0]);
		M = Integer.parseInt(splitedLine[1]);
		map = new int[N][N];
		list = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			splitedLine = in.readLine().split(" ");
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(splitedLine[j]);
				if (map[i][j] == 2)
					list.add(new Pair(i, j));
			}
		}

		// 로직
		RESULT = -1;
		combArr = new int[M];
		valueArr = new int[list.size()];
		for (int i = 0; i < list.size(); ++i)
			valueArr[i] = i;
		comb(0, 0);
		if(RESULT==-1)
			System.out.println(RESULT);
		else
			System.out.println(RESULT-1);
	}

	private static void comb(int start, int depth) {
		if (depth == M) {
			// for (int i = 0; i < M; ++i)
			// 	System.out.print(combArr[i] + " ");
			// System.out.println();
			search();
			return;
		}
		for (int i = start; i < list.size(); ++i) {
			combArr[depth] = valueArr[i];
			comb(i + 1, depth + 1);
		}
	}

	public static void copy(int[][] src, int[][] des) {
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j) {
				if (src[i][j] == 1)
					des[i][j] = -1;
				else
					des[i][j] = 0;
			}
	}

	// -2 : 빈칸
	// -1 : 벽
	// 0 : 바이러스를 최초에 놓은곳
	private static void search() {
		int[][] thisMap = new int[N][N];
		copy(map, thisMap);
		Queue<Pair> q = new ArrayDeque<>();
		for (int i = 0; i < M; ++i) {
			Pair p = list.get(combArr[i]);
			thisMap[p.x][p.y] = 1;
			q.add(p);
		}

		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int d = 0; d < 4; ++d) {
				int nextX = p.x + dx[d];
				int nextY = p.y + dy[d];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
					if (thisMap[nextX][nextY] == 0) {
						thisMap[nextX][nextY] = thisMap[p.x][p.y] + 1;
						q.add(new Pair(nextX,nextY));
					}
				}
			}
		}

		int value = getMaxValue(thisMap);
		if(RESULT == -1 && value == -1){
			RESULT = -1;
		}else if(RESULT>0 && value>0){
			RESULT = Math.min(RESULT,value);
		}else if(RESULT==-1)
			RESULT = value;
		//
		// if(RESULT>0)
		// 	RESULT = Math.min(RESULT,value);
		// else{
		// 	RESULT = value;
		// }
	}

	private static int getMaxValue(int[][] thisMap) {
		int max = -1;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (thisMap[i][j] == 0)
					return -1;
				max = Math.max(max, thisMap[i][j]);
			}
		}
		return max;
	}
}