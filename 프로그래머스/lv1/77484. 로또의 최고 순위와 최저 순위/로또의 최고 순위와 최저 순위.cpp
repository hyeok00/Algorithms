#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    vector<int> answer;
    
    int cnt = 0;
    int zero = 0;
    for (auto cur_num : lottos)
    {
        if(cur_num == 0)
        {
            zero++;
            continue;
        }
        auto it = find(win_nums.begin(), win_nums.end(), cur_num);
        if (it != win_nums.end())
        {
            cnt++;
        }
    }
    if(cnt ==0 && zero ==0)
    {
        answer.push_back(6);
    }
    else
    {
        answer.push_back(6-(cnt+zero-1));
    }
    if(zero == 6 || cnt == 0)
    {
        answer.push_back(6-cnt);
    }
    else if(zero ==0 && cnt == 6 )
    {
        answer.push_back(1);
    }
    else
    {
        answer.push_back(6-cnt+1);
    }
    return answer;
}