class Solution {
    final int INF = 9999999;
    int dist[][];
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];
        
        for(int i = 1; i <= n; ++i){
            for(int j = 1; j <=n; ++j){
                if(i==j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = INF;
            }
        }

        for(int[] data: fares){
            dist[data[0]][data[1]] = data[2];
            dist[data[1]][data[0]] = data[2];
        }
        
        for(int k = 1; k <= n; ++k){
            for(int i = 1 ; i <= n; ++i){
                for(int j = 1 ; j <= n; ++j){
                    if(dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; ++i){
            answer = Math.min(answer, Math.min(dist[s][i] + dist[i][a] + dist[i][b], dist[s][a]+dist[s][b]));
        }
        return answer;
    }
}