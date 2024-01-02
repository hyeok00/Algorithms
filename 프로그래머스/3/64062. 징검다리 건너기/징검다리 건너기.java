import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int len = stones.length;
        // 인덱스 정보만 담는다.
        Deque<Integer> dq = new ArrayDeque<>();
        int max = Integer.MAX_VALUE;
        
        for(int i = 0; i < len; ++i){
            // 윈도우에서 벗어난 원소 제거
            while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            // 새로운 원소가 기존의 원소들보다 크면, 작은 원소들 제거
            while(!dq.isEmpty() && stones[dq.peekLast()] < stones[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
            // 각 윈도우에서 최대값 중 최소가 마지막으로 건너뛰어 갈 수 있는 사람 수이다.
            if(i >= k - 1) {
                max = Math.min(max, stones[dq.peekFirst()]);
            }
        }
        
        return max;
    }
}