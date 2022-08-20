#include <string>
#include <vector>
#include <map>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
	int answer = 0;
    
	// 데이터 정의
	map<int, bool> myMap;
	map<int, bool> spareMap;
	for (int i = 0; i < n; ++i)
	{
		myMap.insert({ i + 1, true });
		spareMap.insert({ i + 1, false });
	}
	for (const auto& it : lost)
	{
		myMap[it] = false;
	}
		for (const auto& it : reserve)
	{
		spareMap[it] = true;
		if ((myMap[it] == false) && (spareMap[it] == true)) // 내옷이 여분이라면 내옷을 입고 빌려주지 않는다.
		{
			myMap[it] = true;
			spareMap[it] = false;
		}
	}

	for (int i = 0; i < n; ++i)
	{
		if (myMap[i + 1] == false) // 없을 경우, 빌릴 수 있는지 확인한다.
		{
			if ((i != 0) && (spareMap[i] == true)) // 앞사람에게 빌릴 수 있다면 앞사람
			{
				spareMap[i] = false;
				myMap[i + 1] = true;
			}
			else if ((i != n-1) && (spareMap[i + 2] == true)) // 뒷사람에게 빌릴 수 있다면 뒷사람
			{
				spareMap[i+2] = false;
				myMap[i + 1] = true;
			}
			else
			{
				// nothing
			}
		}
	}
		

	for (const auto& it : myMap)
	{
		if (it.second == true)
			answer++;
	}
	return answer;
}