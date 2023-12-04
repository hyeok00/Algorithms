import java.util.*;

class Solution {
    Set<Integer> resultSet;
    boolean[] visit;
    
    public boolean isBan(String src, String comp){
        int len1 = src.length();
        int len2 = comp.length();
        
        if(len1 != len2)
            return false;
        
        for(int i = 0; i < len1; ++i){
            if(src.charAt(i) == comp.charAt(i) || comp.charAt(i) == '*')
                continue;
            else
                return false;
        }
        return true;
    }
    
    public void search(String[] user_id, String[] banned_id, int value, int depth){
        if(depth >= banned_id.length){
            resultSet.add(value);
            return;
        }
        for(int i = 0 ; i < user_id.length; ++i){
            if(visit[i]==true)
                continue;
            if(isBan(user_id[i],banned_id[depth])){
                visit[i] = true;
                int newValue = value | (1 << i);
                search(user_id, banned_id, newValue, depth + 1);
                visit[i] = false;
            }
        }
    }
    public int solution(String[] user_id, String[] banned_id) {
        resultSet = new HashSet();
        visit = new boolean[user_id.length + 1];
        
        search(user_id, banned_id, 0, 0);
        
        return resultSet.size();
    }
}