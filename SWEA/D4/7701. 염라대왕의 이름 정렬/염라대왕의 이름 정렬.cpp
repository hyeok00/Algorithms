#include<iostream>
#include<string>
#include<set>
using namespace std;

int T, N;
string temp;

struct myString {
	string str;
	myString(string s) {
		str = s;
	}
};

struct compare {
	bool operator()(const myString &s1, const myString& s2)
	{
		if (s1.str.length() == s2.str.length())
			return s1.str < s2.str;
		else
			return s1.str.length() < s2.str.length();
	}
};

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int tc = 1; tc <= T; ++tc)
	{
		cin >> N;
		set<myString, compare> mySet;

		for (int i = 0; i < N; ++i)
		{
			cin >> temp;
			mySet.insert(myString(temp));
		}

		cout << "#" << tc << "\n";
		for (auto &it : mySet)
			cout << it.str << "\n";
	}
	return 0;
}