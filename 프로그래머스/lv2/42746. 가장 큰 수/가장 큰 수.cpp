#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool compare(const string &a, const string &b)
{
    return a+b > b+a;
}

string solution(vector<int> numbers) {
	string answer = "";

	std::vector<string> myvec;
    for(const auto &it : numbers)
    {
        myvec.push_back(to_string(it));
    }
    sort(myvec.begin(), myvec.end(),compare);
	
	for (const auto &it : myvec)
	{
        if(answer=="0" && it =="0")
            continue;
            
		answer += it;
	}
    
	return answer;
}