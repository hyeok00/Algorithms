import java.util.*;

class Solution {
    public long solution(int n, int[] times) { 
        Arrays.sort(times);
        int len = times.length;
		long min = (long)n * times[0] / len;
		long max = (long)n * times[len - 1];
        while(min <= max){
            long count = 0;
            long mid = (min + max) / 2;
            // mid 시간 기준으로 완료된 사람 수를 구한다.
            for(int i : times){
                count += mid / i;
            }
            // 완료된 사람이 부족한 경우 재 탐색
            if(count < n){
                min = mid + 1;
            }else{
                // 완료된 사람이 많은 경우, 시간이 너무 많이 주어진 경우
                // 줄 일 수 있는 최대로 줄인다.
                max = mid - 1;
            }
        }
        return min;
    }
}