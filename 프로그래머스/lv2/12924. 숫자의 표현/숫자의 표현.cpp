#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    int k;
    for(int i=1; i<n; i++)
    {
        k=0;
        for(int j=i; j<n; j++)
        {
            k=k+j;
            if(k<n)
                continue;
            if(k==n)
            {
                answer+=1;
                break;
            }
            else
                break;
        }
    }
    return answer+1;
}