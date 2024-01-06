class Solution {
    final int mod = 1234567;
    public long solution(int n) {
        int[] dp = new int[2001];
        dp[1] = 1;
        dp[2] = 2;
        long answer = 0;
        for(int i = 3; i <= n; ++i)
            dp[i] = (dp[i-1] + dp[i-2]) % mod;
        
        return dp[n];
    }
}