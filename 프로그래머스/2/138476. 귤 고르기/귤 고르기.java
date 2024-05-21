import java.util.*;

class Solution {
    Map<Integer, Integer> map = new HashMap();
    public int solution(int k, int[] tangerine) {
        for(int value : tangerine)
            map.put(value, map.getOrDefault(value, 0) + 1);
        
        List<Integer> keys = new ArrayList(map.keySet());
        keys.sort((a,b) -> map.get(b) - map.get(a));
        
        int answer = 0;
        for(int i : keys){
            answer++;
            k -= map.get(i);
            if(k <= 0)
                break;
        }
        return answer;
    }
}