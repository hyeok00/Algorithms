class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
        n -= 1;
		while (n >= 0) {
			if (deliveries[n] + pickups[n] == 0) {
				--n;
				continue;
			}
			answer += (n + 1) * 2;

			moveTruck(n, cap, deliveries);
			moveTruck(n, cap, pickups);
		}

		return answer;
	}

	private void moveTruck(int n, int remain, int[] arr) {
		while (n >= 0) {
			int min = Math.min(remain, arr[n]);
			remain -= min;
			arr[n] -= min;
			if(remain == 0)
				break;
			--n;
		}
	}
}