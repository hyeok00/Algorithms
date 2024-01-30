import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int[][] moveCountMap = new int[m][n];
        Queue<int[]> q = new ArrayDeque();
        q.add(new int[] {0, 0});
      
        long[][] dist = new long[m][n];
        for(int i = 0 ; i < m; ++i)
            for(int j = 0; j < n; ++j)
                dist[i][j] = Integer.MAX_VALUE;
        dist[0][0] = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            if(x == m-1 && y == n-1){
                if(dist[x][y] <= s)
                    break;
            }
            for(int d = 0; d < 4; ++d){
                int nextX = x + dx[d];
                int nextY = y + dy[d];
                
                if( 0 <= nextX && nextX < m && 0 <= nextY && nextY < n){
                    // 범위 내부
                    if(time_map[nextX][nextY] == -1)
                        continue;
                    if(dist[nextX][nextY] > dist[x][y] + time_map[nextX][nextY]){
                        dist[nextX][nextY] = dist[x][y] + time_map[nextX][nextY];
                        q.add(new int[] {nextX, nextY});
                        moveCountMap[nextX][nextY] = moveCountMap[x][y] + 1;
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = moveCountMap[m-1][n-1];
        answer[1] = (int)dist[m-1][n-1];
      
        return answer;
    }
    
}
/*
0   2   99
100 100 4
1   2   0

0   1   1   -1  2   4
-1  7   2   1   5   7
*/