class Solution {
    public int solution(int n) {
        int answer = n+1;
        int target = getCount(n);
        while(target != getCount(answer++)){}
        
        return answer-1;
    }
    public int getCount(int n){
        int count = 0;
        int base = 1;
        while(base < n){
            if((base & n) == base)
                count++;
            base = base << 1;
        }
        return count;
    }
}