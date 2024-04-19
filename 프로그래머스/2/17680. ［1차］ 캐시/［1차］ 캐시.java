import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
		Deque<String> dq = new ArrayDeque<>();
		int size = 0;
		int answer = 0;
		for (String city : cities) {
            String s = city.toLowerCase();
            
            if(cacheSize == 0){
                answer += 5;
                continue;
            }
            
            // 캐시가 존재한다면, 있는지 확인하자.
            if (dq.contains(s)) {
				dq.remove(s);
				dq.addLast(s);
				answer += 1;
                continue;
			} 
            
            // 캐시에 데이터가 없는 경우
            answer += 5;
            dq.addLast(s);
            if(size < cacheSize){
                size++;
                continue;
            }
            dq.pollFirst();
		}
		return answer;
	}
}