#include<iostream>
#include<algorithm>
#include<string>
#include<vector>
using namespace std;

bool comp(const string a, const string b)
{
	if (a.length() == b.length())
	{
		for (int i = 0; i < a.length(); i++)
		{
			if (a[i] == b[i])
				continue;
			else
				return a[i] < b[i];
		}
		return a < b;
	}
	return a.length() < b.length();
}

int main()
{
	string s;
	ios::sync_with_stdio(false);
	vector<string> arr;

	int n;
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> s;
		string temp = "";
		for (int j = 0; j < s.length(); j++)
		{
			if ('0' <= s[j] && s[j] <= '9')
			{
				if (temp[0] == '0')
					temp.erase(0);
				temp += s[j];
				if (s[j + 1]<'0' || s[j + 1]>'9')
				{
					arr.push_back(temp);
					temp = "";
				}
			}
		}
	}
	sort(arr.begin(), arr.end(), comp);

	for (int i = 0; i < arr.size(); i++)
	{
		cout << arr[i] << "\n";
	}
	return 0;
}