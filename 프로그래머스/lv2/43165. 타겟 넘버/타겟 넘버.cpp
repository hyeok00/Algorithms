#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(vector<int> numbers, int target) {
	int answer = 0;

	queue<int> myQueue;

	int count = 1;
	int curCount = 0;
	myQueue.push(0);
	int idx = 0;
	while (idx < (int)(numbers.size()))
	{
		curCount++;

		int cur_num = myQueue.front();
		myQueue.pop();
		myQueue.push(cur_num + numbers[idx]);
		myQueue.push(cur_num - numbers[idx]);

		if (curCount >= count)
		{
			count *= 2;
			curCount = 0;
			idx++;
		}
	}
    int queueSize = myQueue.size();
	for (int i = 0; i <queueSize; ++i)
	{
		if (target == myQueue.front())
		{
			answer++;
		}
		myQueue.pop();
	}
	return answer;
}