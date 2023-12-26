import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i : A)
            pq.add(i);
        
        Arrays.sort(B);
        ArrayDeque<Integer> dq = new ArrayDeque();
        for(int i : B)
            dq.add(i);
        
        int answer = 0;
        while(!pq.isEmpty()){
            int target = pq.poll();
            if(target < dq.peekLast()){
                dq.pollLast();
                answer++;
            }else{
                dq.pollFirst();
            }
        }
        
        return answer;
    }
}