import java.util.*;

class Solution {
    boolean flag = false;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; ++i){
            String s = Long.toBinaryString(numbers[i]);
            int size = s.length();
            int weight = 1;
            int base = 2;
            while(true){
                int mul = base * weight;
                if(size > mul - 1){
                    weight *= 2;
                }
                else if(size == mul - 1){
                    break;
                }else{
                    s = "0" + s;
                    size += 1;
                }
            }
            
            flag = false;
            
            check(s);
        
            if(flag)
                answer[i] = 0;
            else
                answer[i] = 1;
            
            // System.out.println();
        }
        
        return answer;
    }
    public void check(String s){
        int size = s.length();
        if(size == 1){
            if(s.charAt(0)=='0')
                flag = true;
            return;
        }
        
        int middle = size / 2;
        int left = (middle/2);
        int right = size - (middle/2) -1 ;
        
        // System.out.println(s);
        // System.out.println(left + " " + middle + " " + right);
        
        // 1 3 7
        if(s.charAt(middle) == '1') {
            String leftStr = s.substring(0,middle);
            String rightStr = s.substring(middle+1);
            // System.out.println(leftStr);
            // System.out.println(rightStr);
            
            if(leftStr.length() > 1){
                check(leftStr);
                check(rightStr);
            }
        }else{
            if(s.charAt(left) == '0' && s.charAt(right) == '0'){
                String leftStr = s.substring(0,middle);
                String rightStr = s.substring(middle+1);
                if(leftStr.length() > 1){
                    check(leftStr);
                    check(rightStr);
                }
            }
            else{
                flag = true;    
            }
        }
        
        
        String leftStr = s.substring(0,middle);
        String rightStr = s.substring(middle+1);
        
        //System.out.println(leftStr + " " + s.charAt(middle)+" "+rightStr);       
    }
}