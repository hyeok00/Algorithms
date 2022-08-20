#include <string>
#include <vector>
#include <map>
#include <set>
#include <algorithm>
using namespace std;


struct ReportData {
	int count; // 신고 횟수
	set<string>	reporter; // 신고자 명단
	ReportData()
	{

	}
	ReportData(int count, string str) {
		this->count = count;
		this->reporter.insert(str);
	}
};
vector<int> solution(vector<string> id_list, vector<string> report, int k) {
	vector<int> answer;
	std::map<string, int> userMap;
	std::map<string, ReportData> targetDataMap;
	std::pair<std::map<string, ReportData>::iterator, bool> ret;	// insert 함수의 return Value를 체크한다.
    sort(report.begin(), report.end());
	report.erase(unique(report.begin(), report.end()), report.end());
	
	for (const auto& it : id_list)
	{
		userMap.insert({ it,0 });
	}
	
	for (const auto& it : report)
	{
		int idx = it.find(" "); // 공백위치 확인
		const string& reporter = it.substr(0, idx);
		const string& target = it.substr(idx+1, it.length());
		
		ret = targetDataMap.insert({ target,ReportData(1,reporter) });
		if (false == ret.second) //  이미 신고당한 적이 있다면
		{
			targetDataMap[target].count++; // 신고 횟수 증가
			targetDataMap[target].reporter.insert(reporter); // 신고자 추가
		}
	}

	for (const auto& it_user : userMap)
	{
		for (const auto& it_report : targetDataMap)
		{
			if (it_report.second.count >= k) // 신고횟수가 K보다 많은 경우만 고려한다.
			{
				if (it_report.second.reporter.find(it_user.first) == it_report.second.reporter.end()) // 없는 경우
				{

				}
				else // 있는경우
				{
					userMap[it_user.first]++;
				}
			}
		}
	}
	
	for (const auto& it : id_list)
	{
		answer.push_back(userMap[it]);
	}

	return answer;
}