#include <algorithm>
#include <string>
#include <vector>
#include <queue>
using namespace std;

bool comp(const int& a, const int& b)
{
	return a > b;
}
int solution(vector<int> priorities, int location) {
	int answer = 0;

	// 정리
	queue<pair<int,int>> myQueue;
	for (size_t idx_priority = 0; idx_priority < priorities.size(); ++idx_priority)
	{
		myQueue.push({ priorities[idx_priority],idx_priority }); // 우선순위, idx
	}
	sort(priorities.begin(), priorities.end(), comp); //

	// 시작
	int idx = 0; // priorities vector의 인덱스 관리
	while (1)
	{
		int val = myQueue.front().first;
		int loc = myQueue.front().second;
		myQueue.pop();

		if (loc == location && priorities[idx]==val)
		{
			return ++answer;
		}

		if (priorities[idx] == val)
		{
			// 남아 있는 값중 최대값일 경우 출력
			++idx;
			++answer;
		}
		else
		{
			myQueue.push({val,loc});
		}
	}

	return answer;
}