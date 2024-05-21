import java.util.*;

class Solution {
    Set<Integer> set = new HashSet();
    public int solution(int[] elements) {
        for(int i = 0; i < elements.length; ++i)
            for(int j = 0; j < elements.length; ++j)
                set.add(getSum(i, j, elements));
        
        return set.size();
    }
    
    public int getSum(int start, int count, int[] elements){
        int sum = 0;
        for(int i = start; i < start+count; ++i)
            sum += elements[i % elements.length];
        return sum;
        
    }
}