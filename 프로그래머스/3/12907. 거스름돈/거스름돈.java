class Solution {
    public int solution(int n, int[] money) {
        final int DIVIDER = 1000000007;
        int[] dp = new int[n+1];
        
        dp[0] = 1;
        for(int i = 0 ; i < money.length; ++i){
            for(int j = money[i]; j <= n; j++){
                dp[j] = (dp[j] + dp[j-money[i]]) % DIVIDER;
            }
        }
        return dp[n];
    }
}