import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i : works)
            pq.add(i);
        
        while(true){
            if(n==0)
                break;
            if(pq.isEmpty())
                break;
            int value = pq.poll();
            n--;
            value -= 1;
            if(value >0)
                pq.add(value);
        }
        
        long answer = 0;
        while(!pq.isEmpty()){
            int value = pq.poll();
            answer += value * value;
        }
        return answer;
    }
}