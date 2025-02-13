import java.util.*;

class Solution {
    public int solution(String[] s1, String[] s2) {
        Set<String> set1 = new HashSet();
        Set<String> set2 = new HashSet();
        Set<String> set3 = new HashSet();
        
        for(String s : s1){
            set1.add(s);
            set3.add(s);
        }
        for(String s : s2){
            set2.add(s);
            set3.add(s);
        }
        return set1.size()+set2.size()-set3.size();
    }
}