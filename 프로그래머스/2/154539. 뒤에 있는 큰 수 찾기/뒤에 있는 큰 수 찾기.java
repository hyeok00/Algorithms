import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        Arrays.fill(answer, -1);
        
        Stack<Integer> s = new Stack();
        for(int i = 0; i < size; ++i){
            while(!s.isEmpty() && numbers[s.peek()] < numbers[i])
                answer[s.pop()] = numbers[i];
            s.add(i);
        }
        return answer;
    }
}