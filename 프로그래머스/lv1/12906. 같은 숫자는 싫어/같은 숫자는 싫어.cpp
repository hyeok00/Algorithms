#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr)
{
	vector<int> answer;
	int j = 0;
	answer.push_back(arr[0]);
	for (int i = 0; i < arr.size(); i++)
	{
		if (answer[j] == arr[i])
		{
			continue;
		}
		else
		{
			j++;
			answer.push_back(arr[i]);
		}
	}
	return answer;
}