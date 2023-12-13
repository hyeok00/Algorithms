class Solution {
    static int[][] dp, arr;
    
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        int len = matrix_sizes.length;
        dp = new int[len+1][len+1];
        arr = matrix_sizes;
       
        answer = search(0, len);
  
        return answer;
    }
    
    public int getMultiplyCount(int start, int end){
        if(dp[start][end] == 0)
            dp[start][end] = search(start, end);
        return dp[start][end];
    }
    
    public int search(int start, int end){
        if(start + 1 == end)
            return 0;
        
        int result = Integer.MAX_VALUE;
        for(int mid = start + 1; mid < end; ++mid){
            int leftCount = getMultiplyCount(start, mid);
            int rightCount = getMultiplyCount(mid, end);
            int current = arr[start][0] * arr[mid][0] * arr[end-1][1];
            result = Math.min(result, leftCount + rightCount + current);
        }
        return result;
    }
}