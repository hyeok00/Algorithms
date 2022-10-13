import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	static StringBuilder sb;
	static String[] splitedLine;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(in.readLine());
			int oddCount = 0;
			int hasMaxDistanceIndex = -1;
			int maxDistance = 0;
			for (int i = 0; i < N; ++i) {
				splitedLine = in.readLine().split(" ");
				int x = Math.abs(Integer.parseInt(splitedLine[0]));
				int y = Math.abs(Integer.parseInt(splitedLine[1]));
				int distance = x + y;
				if (distance % 2 == 1)
					oddCount++;
				if (maxDistance < distance) {
					maxDistance = distance;
					hasMaxDistanceIndex = i;
				}
			}
			if (N-oddCount !=0 && N-oddCount!=N) {
				sb.append("#" + tc + " -1\n");
			} else {
				int available = 0;
				int count = 0;
				while (true) {
					available += count;
					if (maxDistance <= available && (available - maxDistance) % 2 == 0)
						break;
					++count;
				}
				sb.append("#" + tc + " " + count + "\n");
			}
		}
		System.out.println(sb);
	}
}