#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <tuple>

using namespace std;

map<pair<int, int>, int> visitMap;
map<tuple<int, int, int, int>, bool> pathMap;

// 중간 링크를 확인해서 방문했다면, 별도 처리를 하지 말자.

void movepos(const int& val, int& p_x, int& p_y)
{
	switch (val)
	{
	case 0:
		p_y += 2;
		break;
	case 1:
		p_x += 2;
		p_y += 2;
		break;
	case 2:
		p_x += 2;
		break;
	case 3:
		p_x += 2;
		p_y -= 2;
		break;
	case 4:
		p_y -= 2;
		break;
	case 5:
		p_x -= 2;
		p_y -= 2;
		break;
	case 6:
		p_x -= 2;
		break;
	case 7:
		p_x -= 2;
		p_y += 2;
		break;
	}
}

int solution(vector<int> arrows) {
	int answer = 0;

	int cur_x = 0;
	int cur_y = 0;
	visitMap.insert({ { cur_x,cur_y },1 }); // 최초 시작점 방문처리

	for (const auto& it : arrows)
	{
		int pre_x = cur_x;
		int pre_y = cur_y;
		movepos(it, cur_x, cur_y); // 위치 이동

		if (pathMap.find(make_tuple(pre_x, pre_y, cur_x, cur_y)) == pathMap.end())
		{
			pathMap.insert({ make_tuple(pre_x, pre_y, cur_x, cur_y), true });
			pathMap.insert({ make_tuple(cur_x, cur_y, pre_x, pre_y), true });
			// 움직인 적이 없는 경로
			int mid_x = (cur_x + pre_x) / 2; // 중간좌표 체크
			int mid_y = (cur_y + pre_y) / 2;

			std::pair<std::map<pair<int, int>, int>::iterator, bool> ret;	// insert 함수의 return Value를 체크한다.

			ret = visitMap.insert({ {cur_x,cur_y}, 1 });	// 한 번도 방문 되지 않았다면, 1회 방문 처리
			if (false == ret.second)
			{
				// 방문한 적이 있는 경우다.
				// 방문 횟수 증가
				visitMap[{cur_x, cur_y}]++;
			}

			ret = visitMap.insert({ { mid_x, mid_y }, 1 }); // 중간 경로 방문 처리
			if (false == ret.second)
			{
				// 방문한 적이 있는 경우다.
				// 방문 횟수 증가
				visitMap[{mid_x, mid_y}]++;
			}
		}
		else
		{
			// 움직인적이 있는 경로는 별도로 체크할 필요가 없다.
		}
	}

	for (const auto& it : visitMap)
		if (it.second > 1)
			answer+=it.second-1;

	return answer;
}