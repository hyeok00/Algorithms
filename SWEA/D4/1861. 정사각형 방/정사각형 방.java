import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class Solution {
    static StringBuilder sb;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int maxDepth;
    static int minStart;
    static int startValue;
    static int N;
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
 
        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(in.readLine());
            arr = new int[N][N];
            visit = new boolean[N][N];
 
            for (int i = 0; i < N; ++i) {
                String[] splitedLine = in.readLine().split(" ");
                for (int j = 0; j < N; ++j) {
                    arr[i][j] = Integer.parseInt(splitedLine[j]);
                }
            }
 
            minStart = Integer.MAX_VALUE;
            startValue = 0;
            maxDepth = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    startValue = arr[i][j];
                    visit[i][j] = true;
                    dfsSearch(i, j, 1);
                    visit[i][j] = false;
                }
            }
            sb.append("#" + tc + " " + minStart + " " + maxDepth + "\n");
        }
        System.out.println(sb);
    }
 
    private static void dfsSearch(int startX, int startY, int depth) {
        if (maxDepth < depth) {
            maxDepth = depth;
            minStart = startValue;
        }else if(maxDepth==depth) {
            if (minStart > startValue)
                minStart = startValue;
        }
 
        for (int i = 0; i < 4; ++i) {
            int nextX = startX + dx[i];
            int nextY = startY + dy[i];
            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) { // 범위 내부이면서
                if (visit[nextX][nextY] == false) { // 방문한 적이 없고
                    int curValue = arr[startX][startY];
                    int nextValue = arr[nextX][nextY];
                    if ((curValue + 1) == nextValue) {
                        // 값의 차이가 1까지만 허용
                        visit[nextX][nextY] = true;
                        dfsSearch(nextX, nextY, depth + 1);
                        visit[nextX][nextY] = false;
                    }
                }
            }
        }
    }
}