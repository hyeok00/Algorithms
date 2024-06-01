class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] cstr = skill.toCharArray();
        for(String s : skill_trees){
            int base = -2;
            boolean flag = true;
            for(char c : cstr){
                int next = s.indexOf(c);    
                if(next == -1)
                    next = Integer.MAX_VALUE;
                if(base > next){
                    flag = false;
                    break;
                }
                base = next;
            }
            if(flag)
                answer++;
        }
        return answer;
    }
}