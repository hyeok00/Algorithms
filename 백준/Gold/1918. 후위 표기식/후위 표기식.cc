#include<iostream>
#include<stack>
#include<string>
using namespace std;

enum MyEnum
{
	PLUS = '+',
	MULTIPLE = '*',
	DIVIDER = '/',
	MINUS = '-',
	OPEN = '(',
	CLOSE = ')'
};

string input;
stack<char> s;

int main() {
	ios::sync_with_stdio(false);
	getline(cin, input);
	for (int i = 0; i < input.length(); ++i) {
		char c = input[i];
		if ('A' <= c && c <= 'Z')
			cout << c;
		else
		{
			if (s.empty())
				s.push(c);
			else
			{
				if (c == OPEN)
					s.push(OPEN);
				else if (c == CLOSE)
				{
					while (!s.empty())
					{
						if (s.top() == OPEN)
						{
							s.pop();
							break;
						}
						cout << s.top();
						s.pop();
					}
				}
				else if (c == PLUS || c == MINUS)
				{
					if (s.top() == MULTIPLE || s.top() == DIVIDER)
					{
						while (!s.empty())
						{
							if (s.top() == PLUS || s.top() == MINUS)
							{
								cout << s.top();
								s.pop();
								break;
							}
							cout << s.top();
							s.pop();
						}
						s.push(c);
					}
					else if (s.top() == OPEN || s.top() == CLOSE)
					{
						s.push(c);
					}
					else
					{
						cout << s.top();
						s.pop();
						s.push(c);
					}

				}
				else {
					if (s.top() == PLUS || s.top() == MINUS)
						s.push(c);
					else if (s.top() == OPEN || s.top() == CLOSE)
					{
						s.push(c);
					}
					else
					{
						cout << s.top();
						s.pop();
						s.push(c);
					}
				}
			}
		}
	}
	while (!s.empty())
	{
		cout << s.top();
		s.pop();
	}
	return 0;
}