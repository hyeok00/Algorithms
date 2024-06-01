class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for(String s : skill_trees){
            int base = -2;
            boolean flag = true;
            for(int i = 0; i < skill.length(); ++i){
                int next = s.indexOf(skill.charAt(i));    
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