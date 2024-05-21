import java.util.*;
class Solution {
    int max;
    public int solution(int[] arr) {
        Arrays.sort(arr);
        max = arr[arr.length - 1];
        int weight = 1;
        while(true){
            int target = max * weight;
            if(isOkay(max * weight, arr))
                return max * weight;
            weight++;
        }
    }
    public boolean isOkay(int target, int[] arr){
        for(int value : arr){
            if(target % value != 0)
                return false;
        }
        return true;
    }
}