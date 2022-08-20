#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds)
{
	vector<int> answer;

	int curIdx = 0;
	size_t len = speeds.size();

	while (curIdx != int(len))
	{
		int val = 0;
		for (size_t idx = curIdx; idx < len; ++idx)
		{
			progresses[idx] += speeds[idx];
			if (progresses[idx] >= 100)
			{
				if (idx == 0)
				{
					curIdx++;
					val++;
				}
				else
				{
					if (progresses[curIdx] >= 100)
					{
						curIdx++;
						val++;
					}
				}
			}
		}
		if (val > 0)
		{
			answer.push_back(val);
		}
	}
	return answer;
}