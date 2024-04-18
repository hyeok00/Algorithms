class Solution {
    int N;
    int[] arr, price;
    int[][] userInfo;
    int maxUser, maxPrice;
    
    public int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        userInfo = users;
        price = emoticons;
        arr = new int[N];
        maxUser = 0;
        maxPrice = 0;
        perm(0,0);
        
        return new int[]{maxUser, maxPrice};
    }
    public void perm(int idx, int depth){
        if(depth == N){
            int count = 0;
            int cost = 0;
            for(int[] person : userInfo){
                int sum = 0;
                for(int i = 0; i < N; ++i){
                    if(arr[i] >= person[0]){
                        sum += (int)(price[i] * (100-arr[i]) * 0.01);
                    }
                }
                if(sum >= person[1]){
                    count++;
                }else{
                    cost += sum;
                }
            }
            
            if(count >= maxUser){
                if(count > maxUser){
                    maxUser = count;
                    maxPrice = cost;
                }else{
                    if(cost> maxPrice){
                        maxPrice = cost;
                    }
                }
            }

            return;
        }
        
        for(int i = 10; i <=40; i += 10){
            arr[idx] = i;
            perm(idx + 1, depth + 1);
        }
    }
}