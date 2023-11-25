import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        List<Integer> list = new LinkedList();
        for(String s : operations){
            String[] split = s.split(" ");
            char operand = split[0].charAt(0);
            int num = Integer.parseInt(split[1]);
            if(operand == 'I'){
                if(list.isEmpty())
                    list.add(num);
                else{                    
                    int size = list.size();
                    if(size == 1){
                        if(list.get(0) > num)
                            list.add(0, num);
                        else
                            list.add(num);
                    }else{
                        boolean flag = false;
                        for(int i = 0 ; i < size; ++i){
                            if(num < list.get(i)){
                                list.add(i, num);
                                flag = true;
                                break;
                            }
                        }
                        
                        if(!flag)
                            list.add(num);
                    }
                }
            }else{
                if(!list.isEmpty()){
                    if(num == 1)
                        list.remove(list.size() - 1);
                    else
                        list.remove(0);
                }
            }
        }
        
        if(!list.isEmpty())
            return new int[] {list.get(list.size() - 1), list.get(0)};
        else
            return new int[] {0,0};
    }
}