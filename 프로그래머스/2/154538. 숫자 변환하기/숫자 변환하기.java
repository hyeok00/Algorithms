import java.util.*;

class Solution {
    static int dp[] = new int[1000001];
    public int solution(int x, int y, int n) {
        Queue<Integer> q = new ArrayDeque();
        q.add(y);
        dp[y] = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            if(cur == x)
                return dp[cur] - 1;
            
            int case1 = cur - n;
            if(case1 >= x && dp[case1] == 0){
                dp[case1] = dp[cur] + 1;
                q.add(case1);
            }
            
            if(cur % 2 == 0){
                int case2 = cur / 2;
                if(case2 >= x && dp[case2] == 0){
                    dp[case2] = dp[cur] + 1;
                    q.add(case2);
                }
            }
            
            if(cur % 3 == 0){
                int case3 = cur / 3;
                if(case3 >= x && dp[case3] == 0){
                    dp[case3] = dp[cur] + 1;
                    q.add(case3);
                }
            }
        }
        return -1;
    }
}