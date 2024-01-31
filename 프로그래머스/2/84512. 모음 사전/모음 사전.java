import java.util.*;

class Solution {
    int size;
    Set<String> set;
    String[] arr = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        int answer = 0;
        set = new TreeSet();
        
        for(int i = 0 ; i < 5; ++i)
            makeWord(arr[i], 1);
        
        int count = 0;
        for(String s : set){
            count++;
            if(s.equals(word))
                return count;
        }
        return answer;
    }
    
    public void makeWord(String base, int depth){
        set.add(base);
        if(depth == 5){
            return;
        }
        for(int i = 0 ; i < 5; ++i)
            makeWord(base + arr[i], depth + 1);
    }
}