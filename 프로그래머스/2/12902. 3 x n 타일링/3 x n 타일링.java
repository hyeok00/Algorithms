class Solution {
    public int solution(int n) {
        int mod = 1000000007;
        long[] dp = new long[n + 1];
		dp[0] = 1;
		dp[2] = 3;

		for (int i = 4; i <= n; i+=2)
			dp[i] = ((dp[i - 2] * 4 % mod) + mod - dp[i - 4]) % mod;
            
		return (int)dp[n];
    }
}