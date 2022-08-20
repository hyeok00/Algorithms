#include <string>
#include <vector>
#include <queue>
#include <functional>

using namespace std;

int solution(vector<int> scoville, int K)
{
    int answer = 0;
    priority_queue<int, vector<int>, greater<int>> pq;
    for(const auto &it : scoville)
    {
        pq.push(it);
    }
    while(1)
    {
        if(1==pq.size())
        {  
            if(pq.top() >= K)
                return answer;
            else
                return -1;
        }
        if(pq.top()>=K)
            return answer;
        
        int val1 = pq.top();
        pq.pop();
        int val2 = pq.top();
        pq.pop();
        
        pq.push(val1+val2*2);
        ++answer;
    }
}