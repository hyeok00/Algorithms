import java.util.Arrays;

public class Solution {

	public int solution(int[][] targets) {
		int answer = 0;
		Arrays.sort(targets, (a, b) -> {
			if (a[1] == b[1])
				return a[0] - b[0];
			return b[1] - a[1];
		});

		int left = targets[0][0];
		for (int[] data : targets) {
			if (left < data[1]) {
				left = Math.max(data[0], left);
				continue;
			}
			answer++;
			left = data[0];
		}
		return answer+1;
	}
}
