import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int[] points = new int[rocks.length + 1];
        for (int i = 0; i < rocks.length; i++) {
            points[i] = rocks[i];
        }
        points[rocks.length] = distance;
        
        int left = 0;
        int right = distance;
        int answer = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int dist = Integer.MAX_VALUE;
            int current = 0, removeCount = 0;
            
            for (int point : points) {
                int diff = point - current;
                // 중간보다 왼쪽에 있는것은 출발지와 비교.
                if (diff < mid) {
                    removeCount++;
                } else { // 중간보다 오른쪽에 있는것은 가장 중간에 가까운것과 비교.
                    current = point;
                    dist = Math.min(dist, diff);
                }
            }
            
            // 삭제된 돌의 수가 더 많다면, 범위를 좁혀 돌을 덜 제거해야 한다.
            if (removeCount > n) {
                right = mid - 1;
            } else {
                answer = dist;
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
/*
0 2 11 14 17 21 25

2 9 3 3 4 4
*/