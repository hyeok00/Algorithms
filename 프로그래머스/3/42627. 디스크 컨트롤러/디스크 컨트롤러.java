import java.util.*;
class Task implements Comparable<Task>{
    int requestTime; // 최초 요청시간
    int processTime; // 작업 소요시간
    
    Task(int[] data){
        requestTime = data[0];
        processTime = data[1];
    }
     
    public int compareTo(Task o){
        return this.processTime - o.processTime;
    }
}
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b)->{
            if(a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        PriorityQueue<Task> pq = new PriorityQueue();
        int len = jobs.length;
        int answer = 0; // 처리시간 합
        int idx = 0; // jobs의 index
        int time = jobs[idx][0]; // 현재 시간
        pq.add(new Task(jobs[idx++]));
        
        while(!pq.isEmpty()){
            // 가장 처리시간이 빠른 작업을 꺼낸다.
            Task cur = pq.poll();
            
            // 작업을 처리( 시간 처리 + 응답 시간 관리)
            time += cur.processTime;
            answer += (time - cur.requestTime);
            
            // 현재 시간까지 요청이 들어온것을 모두 추가한다
            while(idx < len && jobs[idx][0] <= time)
                pq.add(new Task(jobs[idx++]));
            
            // 앞 전 요청이 완료될때까지 새로운 요청이 없고, 남은 작업이 존재하는 경우
            if(idx < len  && pq.isEmpty()){
                time = jobs[idx][0];
                pq.add(new Task(jobs[idx++]));
            }
        }
        
        return answer / len;
    }
}