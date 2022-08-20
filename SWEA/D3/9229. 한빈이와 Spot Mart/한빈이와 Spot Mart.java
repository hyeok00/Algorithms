import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();
		// 입력부
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			String[] splitedLine = in.readLine().split(" ");
			int n = Integer.parseInt(splitedLine[0]);
			int maxWeight = Integer.parseInt(splitedLine[1]);
			int[] arr = new int[n];
			splitedLine = in.readLine().split(" ");
			for (int i = 0; i < n; ++i) {
				arr[i] = Integer.parseInt(splitedLine[i]);
			}

			// 로직
			int max = -1;
			for (int i = 0; i < n-1; ++i) {
				for (int j = i + 1; j < n; ++j) {
					int value = arr[i] + arr[j];
					if (value <= maxWeight && max <= value)
						max = value;
				}
			}

			sb.append("#" + tc + " " + max + "\n");
		}

		// 출력
		System.out.println(sb);
	}
}