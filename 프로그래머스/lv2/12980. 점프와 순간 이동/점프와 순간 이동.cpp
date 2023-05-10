#include <iostream>
using namespace std;

int solution(int n)
{
    int ans = 0;
    
    while(n!=0)
    {
        if(n & 1 == 1)
        {
            n--;
            ans++;
        }
        n=n>>1;
    }

    return ans;
}