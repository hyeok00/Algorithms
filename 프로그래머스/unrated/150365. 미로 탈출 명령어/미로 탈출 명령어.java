import java.util.*;

class Solution {
    class Point{
        int x;
        int y;
        
        Point(int a, int b){
            x = a;
            y = b;
        }
    }
    // d l r u
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    char[] move = {'d','l','r','u'};
    Point end;
    int N,M,K;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        end = new Point(r-1,c-1);
        
        N = n;
        M = m;
        K = k;
        
        String answer = search(new Point(x-1, y-1),"");
    
        return answer;
    }
    
    public String search(Point p, String s) {
        // System.out.println(s);
        
        if(s.length() == K)
            if(p.x == end.x && p.y == end.y)
                return s;
            else
                return "impossible";
            
        String result = "impossible";
        int remainStep = K - s.length();
        for(int d = 0 ; d < 4; ++d) {
            int nextX = p.x + dx[d];
            int nextY = p.y + dy[d];
            if(0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
                int dist = getDistance(end.x, end.y, nextX, nextY);
                if(dist <= remainStep) {
                    if(dist % 2 == 0 && remainStep % 2 == 1) {
                        result = search(new Point(nextX, nextY), s+move[d]);    
                    } else if(dist % 2 == 1 && remainStep % 2 == 0) {
                        result = search(new Point(nextX, nextY), s+move[d]);    
                    } else {
                        
                    }
                }
            }
            if(!result.equals("impossible"))
                break;
        }
        return result;
    }
    
    public int getDistance(int r, int c, int x, int y){
        return Math.abs(r-x) + Math.abs(c-y);
    }
}