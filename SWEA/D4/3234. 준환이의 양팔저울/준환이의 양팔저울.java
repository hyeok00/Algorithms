import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static StringBuilder sb;
	static int T, N, totalSum, result;
	static int[] permArr, weight;
	static boolean[] isVisit, isUsed;

	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());
		//T = 1;
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(in.readLine());
			weight = new int[N];
			permArr = new int[N];
			isVisit = new boolean[N];
			isUsed = new boolean[N];
			totalSum = 0;
			splitedLine = in.readLine().split(" ");

			for (int i = 0; i < N; ++i) {
				weight[i] = Integer.parseInt(splitedLine[i]);
				totalSum += weight[i];
			}

			// 로직
			Arrays.sort(weight);
			result = 0;
			perm(N, 0);

			sb.append("#" + tc + " " + result + "\n");
		}
		// 출력부
		System.out.println(sb);
	}

	private static void perm(int r, int depth) {
		if (r == depth) {
			check(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; ++i) {
			if (isVisit[i] == false) {
				isVisit[i] = true;
				permArr[depth] = weight[i];
				perm(r, depth + 1);
				isVisit[i] = false;
			}
		}
	}

	private static void check(int depth, int left, int right) {
		if (N == depth) {
			result++;
			return;
		}
		if (left + permArr[depth] >= right)
			check(depth + 1, left + permArr[depth], right);
		if (left >= right + permArr[depth])
			check(depth + 1, left, right + permArr[depth]);
	}
}
