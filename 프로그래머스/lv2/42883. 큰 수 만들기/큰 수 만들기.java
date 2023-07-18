import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        int size = number.length();
        int count = 0;
        Stack<Integer> s = new Stack();
        for(int i=0; i<size; ++i) {
            int value = number.charAt(i) - '0';
            while(!s.empty() && s.peek() < value) {
                if(count < k) {
                    s.pop();
                    count ++;
                }else
                    break;
            }
            s.add(value);
        }
        if(count == 0){
            for(int i=0; i<k; ++i)
                s.pop();
        }
            
        while(!s.empty()){
            answer = Integer.toString(s.peek()) + answer;
            s.pop();
        }
    
        return answer;
    }
}