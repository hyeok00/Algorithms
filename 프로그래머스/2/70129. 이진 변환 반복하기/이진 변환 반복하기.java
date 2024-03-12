class Solution {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        while(!s.equals("1")){
            int size = s.length();
            int count = (int)s.chars().filter(c -> c == '1').count();
            s = Integer.toBinaryString(count);
            answer[0]++;
            answer[1] += (size-count);
        }
        return answer;
    }
}