#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    sort(citations.begin(), citations.end());
    size_t vecSize = citations.size();
    int max = vecSize;

    for (const auto &it : citations)
    {
        if (it>= max)
        {
            answer = max;
        }
        else
        {
            max--;
        }
    }
    return answer;
}