import java.util.*;

class Time implements Comparable<Time>{
    int timetick;
    
    Time(String str){
        String[] splitedLine = str.split(":");
        timetick = Integer.parseInt(splitedLine[0])*60 + Integer.parseInt(splitedLine[1]);
    }
    Time(){};
    Time(int timetick){
        this.timetick = timetick;
    }
    
    public String toFormatting(){
        String hour = Integer.toString(timetick / 60);
        String min = Integer.toString(timetick % 60);
        if(hour.length() == 1)
            hour = "0" + hour;
        if(min.length() == 1)
            min = "0" + min;
        
        return hour + ":" + min;
    }

    public void after(int k){
        timetick += k;
    }
    public int compareTo(Time o){
        return this.timetick - o.timetick;
    }
    
}
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        List<Time> list = new ArrayList();
        for(String str : timetable)
            list.add(new Time(str));
        
        Collections.sort(list);
        
        Time base = new Time("09:00");
        Time result = new Time();
        int idx = 0;
        for (int i = 1; i <= n ; ++i) {  // 총 n대의 셔틀 버스 운행 
            int count = 0;  // 현재 버스의 탑승자 수
            for (int j = idx; j < list.size(); ++j) {
                if (list.get(j).timetick > base.timetick)
                    break;
                
                // 현재 버스보다 먼저 대기하던 사람
                count++;
                idx++;
                if (count == m) // 최대 인원이 모두 탑승한 경우.
                    break;
                
            }
            if (i == n){  // 마지막 버스일 경우 처리
                if(count == m) // 마지막 탑승자 보다 1분 빨리 와야 한다.
                    result.timetick = list.get(idx-1).timetick - 1;
                else
                    result.timetick = base.timetick;
            }
            base.after(t);
        }
        
        return result.toFormatting();
    }
}