#include <vector>
#include <set>
using namespace std;

int solution(vector<int> nums)
{
	int answer = 0;
	set<int> myset;
	for (const auto& it : nums)
	{
		myset.insert(it);
	}
	int half = nums.size() / 2;
	if (myset.size() < half)
		answer = myset.size();
	else
		answer = half;
	return answer;
}