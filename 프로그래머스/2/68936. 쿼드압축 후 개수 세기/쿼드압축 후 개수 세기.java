class Solution {
    public int[] solution(int[][] arr) {
        int size = arr.length;
        
        String result =getCode(arr, 0, 0, size);
        
        // 압축된 전체 코드를 구했다. 0의 갯수를 구해보자.
        // 1의 갯수는 전체길이에서 0의 갯수를 뺀것이다.
        int count = 0;
        for(int i = 0 ; i < result.length(); ++i)
            if(result.charAt(i)=='0')
                count++;
        
        int[] answer = new int[2];
        answer[0] = count;
        answer[1] = result.length()-count;
        return answer;
    }
    
    public String getCode(int[][] arr, int baseX, int baseY, int size){
        if(size == 1){
            // 더이상 분해할 수 없다. 현재 코드를 리턴
            return Integer.toString(arr[baseX][baseY]);
        }
        
        int newSize = size / 2;
        String[] result = new String[4];
        result[0] = getCode(arr, baseX, baseY, newSize); // 좌상단
        result[1] = getCode(arr, baseX + newSize, baseY, newSize); // 우상단
        result[2] = getCode(arr, baseX, baseY + newSize, newSize); // 좌하단
        result[3] = getCode(arr, baseX + newSize, baseY + newSize, newSize); // 우하단
        
        boolean flag = true;
        String base = result[0];
        for(int i = 0 ; i < 4; ++i){
            if(!result[i].equals(base)){
                flag = false;
                break;
            }
        }
        
        // 4개의 영역이 모두 같고, 길이가 1인 경우 압축이 가능한 경우다.
        if(flag && base.length() == 1){
            return base;
        }else{ // 압축이 불가능할때는 압축 전 코드를 더해서 반환
            return result[0]+result[1]+result[2]+result[3];
        }
    }
}