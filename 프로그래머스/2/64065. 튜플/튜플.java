import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<Integer> list = new ArrayList();
        List<String> data = new ArrayList();
        
        int size = s.length();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for(int i = 1 ; i < size-1; ++i){
            char c = s.charAt(i);
            if(c == '{'){
                flag = true;
            }else if(c == '}'){
                flag = false;
                data.add(sb.toString());
                sb.setLength(0);
            }else{
                if(flag){
                    sb.append(c);
                }
            }
        }
        
        Collections.sort(data, (o1, o2)-> {return o1.length()-o2.length();});
        
        boolean[] arr = new boolean[100001];
        for(String str : data){
            String[] splitedLine = str.split(",");
            for(String numberString : splitedLine){
                int num = Integer.parseInt(numberString);
                if(!arr[num]){
                    list.add(num);
                    arr[num] = true;
                }
            }
        }
        
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}