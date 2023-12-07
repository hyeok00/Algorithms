import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet();
        int count = 0;
        boolean flag = false;
        char last = words[0].charAt(0);
        
        for(String str : words){
            if(set.contains(str) || str.charAt(0) != last){
                flag = true;
                break;
            }
            last = str.charAt(str.length()-1);
            set.add(str);
            count++;
        }
        
        if(!flag)
            return new int[] {0,0};
        
        return new int[] { count % n + 1, count / n + 1 };
    }
}