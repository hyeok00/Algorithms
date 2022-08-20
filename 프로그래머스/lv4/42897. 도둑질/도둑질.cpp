#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int firstArr[1000001]={0,}; // 첫번째 집을 포함하는 경우
int secondArr[1000001]={0,}; // 마지막 집을 포함하는 경우

int solution(vector<int> money) {
    int answer = 0;
    
    int length = money.size();
    // Step1. 첫번째 집을 포함하는 경우
    firstArr[0] = money[0];
    firstArr[1] = (money[0] > money[1]) ? money[0] : money[1] ;
    
    for(int i = 2; i<length-1; ++i)
    {
        if(firstArr[i-2] > firstArr[i-1])
        {
            firstArr[i] = firstArr[i-2] + money[i];
        }
        else
        {
            if(firstArr[i-1] > firstArr[i-2] + money[i])
            {
                firstArr[i] = firstArr[i-1];
            }
            else
            {
                firstArr[i] = firstArr[i-2] + money[i];
            }
        }
    }
    
    // Step2. 마지막 집을 포함하는 경우
    secondArr[1] = money[1];
    secondArr[2] = (money[1] > money[2]) ? money[1] : money[2] ;
    
    for(int i = 3; i<length; ++i)
    {
        if(secondArr[i-2] > secondArr[i-1])
        {
            secondArr[i] = secondArr[i-2] + money[i];
        }
        else
        {
            if(secondArr[i-1] > secondArr[i-2] + money[i])
            {
                secondArr[i] = secondArr[i-1];
            }
            else
            {
                secondArr[i] = secondArr[i-2] + money[i];
            }
        }
    }
    
    // Step3. 위의 두 케이스 중 가장 큰값이 정답이다.
    int firstArr_MaxValue = *max_element(firstArr,firstArr+length);
    int secondArr_MaxValue = *max_element(secondArr,secondArr+length);
    
    if( firstArr_MaxValue > secondArr_MaxValue)
    {
        answer = firstArr_MaxValue;
    }
    else
    {
        answer = secondArr_MaxValue;
    }
    
    return answer;
}