import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int len = cores.length;

        if (n <= len) 
            return n;

        // 우선 N - 1개의 작업이 완료되는 최소 시간을 구한다.
        int minCore = Arrays.stream(cores).min().getAsInt();
        int left = 1;
        int right = n * minCore;

        while (left <= right) {
            int midTime = (left + right) / 2;
            int completeProcess = len;

            // 완료된 작업의 수 체크
            for (int core : cores) {
                completeProcess += midTime / core;
            }

            if (completeProcess < n) {
                left = midTime + 1;
            } else {
                right = midTime - 1;
            }
        }
        // N - 1개의 작업이 완료되는 최소 시간을 계산 완료
        // System.out.println(left);
        
        int done = len;
        for (int core : cores) {
            done += (left - 1) / core;
        }

        // 가장 빠른 코어부터, left 시간에 마지막 작업이 시작하는지 확인.
        int last = 0;
        for (int i = 0; i < len; ++i) {
            if (left % cores[i] == 0) {
                done++;
            }
            if (done == n) {
                last = i;
                break;
            }
        }

        return last + 1;
    }
}