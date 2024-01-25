import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] base = scores[0];
        Arrays.sort(scores, (a,b) -> {
            if(a[0] == b[0])
                return a[1] - b[1];
            else
                return b[0] - a[0];
        });
        
        int baseSum = base[0] + base[1];
        int answer = 1;
        int prev = 0;
        for(int[] data : scores){
            if(prev > data[1]){
                if(data[0] == base[0] && data[1] == base[1])
                    return -1;
            } else {
                prev = data[1];
                if(data[0] + data[1] > baseSum)
                    answer++;
            }
        }
        return answer;
    }
}