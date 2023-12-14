import java.util.*; 

class Solution {
    Map<String, Integer> earn; // 수익금을 저장
    Map<String, String> relation; // 나와 추천인 사이의 관계 저장
    
    final int ITEM_PRICE = 100;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        earn = new HashMap();
        relation = new HashMap();
        
        for(int i = 0 ; i < enroll.length; ++i){
            relation.put(enroll[i], referral[i]);
            earn.put(enroll[i], 0);
        }
        
        for(int i = 0 ; i < seller.length; ++i)
            calculateSell(seller[i], amount[i] * ITEM_PRICE);
        
        int[] answer = new int[enroll.length];
        for(int i = 0 ; i < enroll.length; ++i)
            answer[i] = earn.get(enroll[i]);
        
        return answer;
    }
    
    final float CHARGE_RATE = 0.1f; // 수수료 비율
    public void calculateSell(String person, int price){
        int charge = (int)(price *  CHARGE_RATE); // 수수료
        int profit = price - charge; // 나의 이익금
        
        // 판매에 대한 이익금을 추가한다.
        earn.computeIfPresent(person, (key, value) -> value + profit);
        
        String parent = relation.get(person);
        // 추천인이 없다면, 남은 수수료를 가져갈 사람이 없다.
        if(parent.equals("-"))
            return;
        
        if(charge == 0)
            return;
        
        // 추천인이 수수료를 가져간다.
        calculateSell(parent, charge);
    }
}