import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> left, right;
        left = new HashMap();
        right = new HashMap();
        for(int i : topping)
            right.put(i, right.getOrDefault(i, 0) + 1);
        
        int answer = 0;
        for(int i = 0; i < topping.length; ++i){
            left.put(topping[i], left.getOrDefault(topping[i], 0) + 1);
            right.compute(topping[i], (k, v) -> (v == null || v <= 1) ? null : v - 1);
            if(left.size() == right.size())
                answer++;
        }
        
        return answer;
    }
}