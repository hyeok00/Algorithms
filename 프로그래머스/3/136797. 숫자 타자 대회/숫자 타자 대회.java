class Solution {
	int[][] weight;
	int[][][] dp;
	final int MAX = 987654321;
	char[] arr;
	int len;

	public int solution(String numbers) {
		weight = new int[][] {
			{1, 7, 6, 7, 5, 4, 5, 3, 2, 3}, {7, 1, 2, 4, 2, 3, 5, 4, 5, 6}, {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
			{7, 4, 2, 1, 5, 3, 2, 6, 5, 4}, {5, 2, 3, 5, 1, 2, 4, 2, 3, 5}, {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
			{5, 5, 3, 2, 4, 2, 1, 5, 3, 2}, {3, 4, 5, 6, 2, 3, 5, 1, 2, 4}, {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
			{3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
		};
		len = numbers.length();
		dp = new int[len + 1][10][10];
		arr = numbers.toCharArray();
		for (int i = 0; i <= len; ++i)
			for (int j = 0; j < 10; ++j)
				for (int k = 0; k < 10; ++k)
					dp[i][j][k] = MAX;

		return getMoveCount(0, 4, 6);
	}

	private int getMoveCount(int idx, int left, int right) {
		if (idx == len)
			return 0;

		if (dp[idx][left][right] != MAX)
			return dp[idx][left][right];

		int target = arr[idx] - '0';

		int moveLeft = MAX;
		if (target != right)
			moveLeft = getMoveCount(idx + 1, target, right) + weight[left][target];

		int moveRight = MAX;
		if (target != left)
			moveRight = getMoveCount(idx + 1, left, target) + weight[right][target];
		
		dp[idx][left][right] = Math.min(moveLeft, moveRight);
		return dp[idx][left][right];
	}
}