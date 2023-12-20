import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        List<Integer> dist = new ArrayList();
        int cover = (2 * w) + 1;
        int virtualLeft = -1 * w;
        int virtualRight = n + w + 1;
        
        
        dist.add(stations[0] - virtualLeft - cover);
        for(int i = 1 ; i < stations.length; ++i){
            int value = stations[i] - stations[i - 1] - cover;
            answer += getNeedCount(value, cover);
        }
        answer += getNeedCount(stations[0] - virtualLeft - cover, cover);
        answer += getNeedCount(virtualRight - stations[stations.length - 1] - cover, cover);
        
        return answer;
    }
    public int getNeedCount(int value, int cover){
        int count = 0;
        if(value < 0)
            return 0;
        count += (value / cover);
        if(value % cover > 0)
            count++;
        return count;
    }
}