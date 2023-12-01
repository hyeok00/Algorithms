class Solution {
    public int getRemainMul(int n){
        return (int)(Math.log(n) / Math.log(3));
    }
    
    public int getSimulation(int n , int numMul, int numPlus){
        if(numMul*2 < numPlus)
            return 0;
        if(n == 3 && numMul == 1 && numPlus == 0)
            return 1;
        if(n == 4 && numMul == 1 && numPlus == 1)
            return 1;
        if(n == 5 && numMul == 1 && numPlus == 2)
            return 1;
        
        int count = 0;
        for(int i = 0; i <= numPlus; ++i){
            if((n-i > 0) && (n-i)%3 == 0){
                count += getSimulation((n-i)/3, numMul - 1, numPlus - i);
            }
        }
        
        return count;
    }
    
    public int solution(int n) {
        int numMul = getRemainMul(n);
        int numPlus = numMul * 2;
        
        System.out.println(numMul + " " + numPlus);
        int result = getSimulation(n-2, numMul, numPlus-2);
        return result;
        /*        
        5   *++
        -----------------
        13    **++++
        15    *+*+++
        17    *++*++
        -----------------
        37    **++*++++
        33    ***++++++
        35    **+*+++++
        37    **++*++++
        39    **+++*+++
        41    **++++*++
        
        39    **+++*+++
        45    *+*++*+++
        41    *+**+++++
        43    *+*+*++++
        45    *+*++*+++
        47    *+*+++*++
            
        41    **++++*++
        47    *+*+++*++
        53    *++*++*++
        49    *++**++++
        51    *++*+*+++
        53    *++*++*++
          
        ----------------
        
        (prev)*++
        *(prev)++
        *+(prev)+
        *++(prev)
        
        ----------------
        5   *++
        -----------------
        13    **++++
        15    *+*+++
        17    *++*++
        -----------------
        33    ***++++++
        35    **+*+++++
        37    **++*++++
        39    **+++*+++
        41    **++++*++
        
        41    *+**+++++
        43    *+*+*++++
        45    *+*++*+++
        47    *+*+++*++
        
        49    *++**++++
        51    *++*+*+++
        53    *++*++*++
        ----------------
        
        5 17 53 161 485 1457
        
        35,0    x
        36,1    12,1    4,1 3,0
        37,2    x
        38,3    x
        39,4    13,4    9,0 3,0
        
        12  
        11
        
        13
        12
        11
        9
        
        4 3
        */
    }
}