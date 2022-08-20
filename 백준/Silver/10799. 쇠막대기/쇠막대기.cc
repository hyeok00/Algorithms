#include <string>
#include <vector>
#include<iostream>
using namespace std;

int main()
{
	ios::sync_with_stdio(false);
	string arrangement;
	cin >> arrangement;
	int answer = 0; // 총 막대조각의 수
	vector<char> arr;
	char temp = '^';
	char c;
	for (int i = 0; i < arrangement.length(); i++)
	{
		c = arrangement[i];
		if (c == '(')
		{
			arr.push_back(c);
			answer++;
		}
		else // c==) 일때
		{
			if (temp != c) // 레이저 일때
			{
				arr.pop_back();
				answer += arr.size();
				answer--;
			}
			else
			{
				arr.pop_back();
			}
		}
		temp = c;
	}
	cout << answer;
	return 0;
}