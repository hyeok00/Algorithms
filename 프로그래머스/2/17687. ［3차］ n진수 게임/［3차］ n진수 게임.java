class Solution {
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder sb = new StringBuilder();
        int max = t * m;
        for(int i = 0; sb.length() <= max; ++i){
            sb.append(Integer.toString(i, n).toUpperCase());
        }
        
        String prepared = sb.toString();
        StringBuilder result = new StringBuilder();
        for(int i = p - 1 ; i < max; i += m)
            result.append(prepared.charAt(i));
        return result.toString();
    }
}