import java.util.*;

class Solution {
    Deque<Character> dq = new ArrayDeque();
    public int solution(String s) {
        for(int i = 0; i < s.length(); ++i)
            dq.add(s.charAt(i));
        
        int answer = 0;
        for(int i = 0; i < s.length(); ++i){
            if(isOkay())
                answer++;
            dq.addLast(dq.pollFirst());
        }
        return answer;
    }
    
    public boolean isOkay(){
        Deque<Character> s = new ArrayDeque();
        for(char c : dq){
            if(isOpen(c)){
                s.addLast(c);
                continue;
            }
            if(s.isEmpty())
                return false;
            if(!isPair(s.peekLast(), c))
                return false;
            s.pollLast();
        }
        if(!s.isEmpty())
            return false;
        return true;
    }
    
    public boolean isPair(char a, char b){
        if(a == '[' && b == ']')
            return true;
        if(a == '{' && b == '}')
            return true;
        if(a == '(' && b == ')')
            return true;
        return false;
    }
    
    public boolean isOpen(char c){
        if(c == '[' || c== '{' || c== '(')
            return true;
        return false;
    }
}