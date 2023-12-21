import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> countMap = new HashMap();
        // 보석의 종류 파악 + Map에 0개로 기록
        for(String s : gems)
            countMap.computeIfAbsent(s, (k) -> 0);
        
        int numKinds = countMap.size();
        System.out.println(numKinds);
        
        int start = 0;
        int end = Integer.MAX_VALUE;
        int len = gems.length;
        int left = 0;
        int right = 0;
        int count = 0;
        for(right = 0 ; right < len; ++right){
            // 현재 맵에 0개로 넣어 놓았으므로, 항상 존재한다.
            int nums = countMap.get(gems[right]);
            
            // 해당 보석이 0개 였던 경우는 체크해야 한다.
            if(nums == 0){
                count++;
            }
            
            // 카운트만 증가.
            countMap.computeIfPresent(gems[right], (k, v) -> v + 1);
            
            while(true){
                int leftKeyCount = countMap.get(gems[left]);
                // 왼쪽 값이 1개 이상 존재한다면 제거해도 무방함.
                if(leftKeyCount > 1){
                    countMap.computeIfPresent(gems[left], (k, v) -> v - 1);
                    left++;
                } else {
                    break;
                }
            }
            
            // 구간의 길이 확인
            if(count == numKinds && right - left < end - start){
                start = left;
                end = right;
            }
        }
        
        return new int[] {start + 1, end + 1};
    }
}