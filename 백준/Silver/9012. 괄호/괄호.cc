#include<stack>
#include<iostream>
#include<string>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	int testcase;
	cin >> testcase;
	while (testcase--)
	{
		int isokay = 0;
		string str;
		stack<char> s;
		cin >> str;
		for (int i = 0; i < str.length(); i++)
		{
			if (str[i] == '(')
				s.push(str[i]);
			else
			{
				if (s.empty())
				{
					isokay = 1;
					break;
				}
				s.pop();
			}
		}
		if (isokay==0 && s.empty()==true)
			cout << "YES\n";
		else
			cout << "NO\n";
	}
	return 0;
}