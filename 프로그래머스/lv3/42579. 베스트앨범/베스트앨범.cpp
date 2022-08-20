#include <string>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

bool comp_songs(const pair<int, int> &a, const pair<int, int> &b)
{
	if (a.second == b.second)
		return a.first < b.first;
	else
		return a.second > b.second;
}

class Infomation
{
public:
	int totalPlay = 0;	// 해당 장르의 전체 play 수
	vector<pair<int, int>> songs; // 고유번호, 플레이 수
	int len_songs=0;
	void m_sort()
	{
		sort(songs.begin(), songs.end(),comp_songs);
		len_songs = songs.size();
	}
};

bool compare(const pair<string, Infomation>& a, const pair<string, Infomation>& b)
{
	return a.second.totalPlay > b.second.totalPlay;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
	vector<int> answer;

	size_t len = genres.size();	// 전체 길이를 계산하자.
	map<string, Infomation> gen_count;
	// 
	pair< map<string, Infomation>::iterator, bool> ret;	// map insert return value check;
	for (size_t idx = 0; idx < len; ++idx)
	{
		Infomation info;
		info.totalPlay = plays[idx];
		info.songs.push_back({ idx, plays[idx] });
		ret = gen_count.insert({ genres[idx], info });	// 해당 장르가 insert 된적 없을 경우, 바로 처리
		if (false == ret.second)	// 해당 장르가 Key값으로 있었다면, 전체 재생횟수만 더하자.
		{
			gen_count[genres[idx]].totalPlay += plays[idx];
			gen_count[genres[idx]].songs.push_back({ idx, plays[idx] });
		}
	}
	
	vector<pair<string, Infomation>> vec(gen_count.begin(), gen_count.end());
	sort(vec.begin(), vec.end(), compare);
	for (auto &it : vec)
	{
		it.second.m_sort();

		answer.push_back(it.second.songs[0].first);
		if (it.second.len_songs > 1)
		{
			answer.push_back(it.second.songs[1].first);
		}
	}
	
    return answer;
}