#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

int solution(vector<vector<string>> clothes)
{
	int answer = 0;

	map<string, int> myMap;
	pair<map<string, int>::iterator, bool > ret;	// insert 함수 return Value 체크
	for (const auto& clothes_it : clothes)
	{
		ret = myMap.insert({ clothes_it[1],1 });	// Map에 해당 종류의 의상이 존재하지 않는다면 최초 1개가 존재하는 것.
		if (false == ret.second) // 해당 종류의 의상이 기존에 존재했다면, 해당 종류의 의상 개수를 증가시킨다.
		{
			++myMap[clothes_it[1]];
		}
	}

	if (myMap.size() == 1) // size가 1인경우, 한종류의 의상밖에 없다.
	{
		answer = myMap.begin()->second;
	}
	else
	{
		int val = 1;
		for (const auto& it : myMap)
		{
			val = val * (it.second + 1);
		}
		answer = val - 1; // 다 벗고 있는 경우는 없다..
	}
	return answer;
}