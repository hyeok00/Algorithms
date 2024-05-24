class Solution {
    public int[] solution(int n, long left, long right) {
        int diff = (int)(right - left);
        int[] answer = new int[diff + 1];
        
        for(int k = 0; k <= diff; ++k){
            int quotient = (int)((k + left) / n);
            int remainder = (int)((k + left) % n);
            if(quotient >= remainder)
                answer[k] = quotient + 1;
            else
                answer[k] = remainder + 1;
        }
        return answer;
    }
}