import java.util.*;
class Pair implements Comparable<Pair>{
    int first;
    int second;
    
    Pair(int a, int b){
        first = a;
        second = b;
    }
    public int compareTo(Pair o){
        if(this.second == o.second)
            return this.first - o.first;
        return o.second - this.second;
    }
}
class Solution {
    public int[] solution(int e, int[] starts) {
        Pair[] arr = new Pair[e+1];
        for(int i = 0; i <= e; ++i)
            arr[i] = new Pair(i,0);
        
        for(int i = 1; i <= e; ++i)
            for(int j = i; j <=e; j+=i)
                arr[j].second++;
            
        Arrays.sort(arr);
        
        int len = starts.length;
        int[] result = new int[len];
        for(int i = 0 ; i < len; ++i){
            for(int j = 0 ; j < e; ++j){
                if(starts[i] <= arr[j].first){
                    result[i] = arr[j].first;
                    break;
                }
            }
        }

        return result;
    }
}