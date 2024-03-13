class Solution {
    
    int solution(int[][] land) {
        int len = land.length;
        int[] dp = new int[4];
        
        for(int i = 0; i < len; ++i){
            int[] prev = dp.clone();
            dp[0] = Math.max(Math.max(prev[1], prev[2]), prev[3]) + land[i][0];
            dp[1] = Math.max(Math.max(prev[0], prev[2]), prev[3]) + land[i][1];
            dp[2] = Math.max(Math.max(prev[0], prev[1]), prev[3]) + land[i][2];
            dp[3] = Math.max(Math.max(prev[0], prev[1]), prev[2]) + land[i][3];
        }
        
        int answer = 0;
        for(int value : dp)
            answer = Math.max(answer, value);
        
        return answer;
    }
}