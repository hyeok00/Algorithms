import java.util.*;

class Route implements Comparable<Route>{
    int start;
    int end;
    
    Route(int a, int b){
        start = a;
        end = b;
    }
    
    public int compareTo(Route o){
        if(this.end == o.end)
            return this.start - o.start;
        return this.end - o.end;
        
    }
}
class Solution {
    public int solution(int[][] routes) {
        PriorityQueue<Route> pq = new PriorityQueue();
        for(int[] data : routes){
            pq.add(new Route(data[0], data[1]));
        }
        int answer = 0;
        int camera = -30001;
        while(!pq.isEmpty()){
            Route cur = pq.poll();
            if(camera < cur.start){
                camera = cur.end;
                answer++;
            }
        }

        return answer;
    }
}