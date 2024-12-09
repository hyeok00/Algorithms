import java.util.*;

class Solution {
    Map<String, Integer> map;
    List<Integer> list;
    int size = 1;

    public int[] solution(String msg) {
        map = new HashMap<>();
        for (int i = 0; i <= 'Z' - 'A'; ++i)
            map.put(Character.toString((char) ('A' + i)), size++);

        list = new ArrayList<>();

        find(msg);
        
        return list.stream().mapToInt(e -> e).toArray();
    }

    public void find(String s) {
        for (int i = 0; i < s.length(); i++) {
            String subStr = s.substring(0, i + 1);
            if (!map.containsKey(subStr)) {
                map.put(subStr, size++);
                list.add(map.get(s.substring(0, i)));
                find(s.substring(i)); // 발견한 문자열을 지우고 다음 탐색
                return;
            }
            
            if (i + 1 == s.length())
                list.add(map.get(s.substring(0, i + 1)));
        }
    }
}