#include <string>
#include <vector>
#include <sstream>
#include <map>
using namespace std;

void function(map<string, string> &data, vector<pair<string, string>>& log, const string &str)
{
	stringstream ss(str);
	string type, uid, name;

	getline(ss, type, ' '); // Enter , Leave, Change
	if (type == "Leave")
	{
		getline(ss, uid); // nickname
	}
	else
	{
		getline(ss, uid, ' '); // uid
		getline(ss, name); // nickname
	}
	log.push_back({ type,uid });

	if (type == "Enter")
	{
        data[uid]=name;
	}
	else if (type == "Leave")
	{
        
	}
	else // Change
	{
		data[uid] = name;
	}
}

vector<string> solution(vector<string> record) {
	vector<string> answer;

	vector<pair<string, string>> log; // type, uid
	map<string, string> user; // uid, name

	for (const auto& record_it : record)
	{
		function(user, log, record_it);
	}

	for (const auto& log_it : log)
	{
		if (log_it.first == "Enter")
		{
			answer.push_back(user[log_it.second] + "님이 들어왔습니다.");
		}
		else if (log_it.first == "Leave")
		{
			answer.push_back(user[log_it.second] + "님이 나갔습니다.");
		}
		else
		{
			// nothing
		}
	}

	return answer;
}