import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;
	static int T, N, minValue;
	static int[] combArr;
	static int[][] synergy;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 입력부
		T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(in.readLine());
			synergy = new int[N][N];
			for (int i = 0; i < N; ++i) {
				String[] splitedLine = in.readLine().split(" ");
				for (int j = 0; j < N; ++j) {
					synergy[i][j] = Integer.parseInt(splitedLine[j]);
				}
			}
			// 로직
			minValue = Integer.MAX_VALUE;
			combArr = new int[N / 2];
			comb(N / 2, 0);

			sb.append("#" + tc + " " + minValue + "\n");
		}
		// 출력부
		System.out.println(sb);
	}

	public static void comb(int r, int depth) {
		if (r == depth) {
			boolean[] check = new boolean[N];
			int foodFirst = 0;
			int foodSecond = 0;
			for (int i = 0; i < depth - 1; ++i) {
				for (int j = i + 1; j < depth; ++j) {
					check[combArr[i]] = true;
					check[combArr[j]] = true;
					//System.out.println(combArr[i] + " " + combArr[j]);
					foodFirst += synergy[combArr[i]][combArr[j]] + synergy[combArr[j]][combArr[i]];
				}
			}
			
			for (int i = 0; i < N - 1; ++i) {
				if (check[i] == false) {
					for (int j = i + 1; j < N; ++j) {
						if (check[j] == false) {
							//System.out.println(i+ " " + j);
							foodSecond += synergy[i][j] + synergy[j][i];
						}
					}
				}
			}
			//System.out.println(foodFirst);
			//System.out.println(foodSecond);
			
			int value = Math.abs(foodFirst - foodSecond);
			minValue = minValue > value ? value : minValue;
			return;
		}
		for (int i = 0; i < N; ++i) {
			boolean isUsed = false;
			for (int j = 0; j < depth; ++j) {
				if (combArr[j] >= i) {
					isUsed = true;
					break;
				}
			}

			if (isUsed == true)
				continue;

			combArr[depth] = i;
			comb(r, depth + 1);
		}
	}
}
