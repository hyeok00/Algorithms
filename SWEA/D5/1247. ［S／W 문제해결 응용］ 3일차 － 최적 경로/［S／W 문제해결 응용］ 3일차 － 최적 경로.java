import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb;
	static int T, N, RESULT;
	static int[] permArr;
	static boolean[] used;
	static String[] splitedLine;
	static Pair office, home;
	static Pair[] customers;

	static class Pair {
		int x;
		int y;

		public Pair(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			RESULT = Integer.MAX_VALUE;
			N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			office = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			permArr = new int[N];
			used = new boolean[N];
			customers = new Pair[N];
			for (int i = 0; i < N; ++i) {
				customers[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 로직
			perm(0);
			sb.append("#" + tc + " " + RESULT + "\n");
		}
		// 출력부
		System.out.println(sb);
	}

	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void perm(int depth) {
		if (depth == N) {
			int distance = 0;
			distance += getDistance(office.x, office.y, 
					customers[permArr[0]].x, customers[permArr[0]].y);
			for (int i = 0; i < depth - 1; ++i) {
				distance += getDistance(customers[permArr[i]].x, customers[permArr[i]].y,
						customers[permArr[i + 1]].x,customers[permArr[i + 1]].y);
			}
			distance += getDistance(customers[permArr[depth - 1]].x, customers[permArr[depth - 1]].y,
					home.x, home.y);

			RESULT = RESULT < distance ? RESULT : distance;
			return;
		}
		for (int i = 0; i < N; ++i) {
			if (used[i] == false) {
				used[i] = true;
				permArr[depth] = i;
				perm(depth + 1);
				used[i] = false;
			}
		}
	}
}
