class Solution {
    public int[] solution(int n, int s) {
        if(s<n)
            return new int[] {-1};
        
        int[] arr = new int[n];
        int base= s / n;
        int remain = s % n;
        for(int i = 0 ; i < n; ++i)
            arr[i] = base;
        
        int index = n - 1;
        while(remain > 0){
            arr[index--] += 1;
            remain -= 1;
        }
        
        return arr;
    }
}