#include<iostream>
using namespace std;

int sol(int e, int s, int m)
{
	int i = 0;
	int a, b, c;
	a = 0;
	b = 0;
	c = 0;
	while (1)
	{
		i++;
		a++;
		b++;
		c++;

		if (a == e && b == s && c == m)
			return i;

		if (a % 15 == 0)
		{
			a -= 15;
		}
		if (b % 28 == 0)
		{
			b -= 28;
		}
		if (c % 19 == 0)
		{
			c -= 19;
		}
	}
	return i;
}
int main()
{
	ios::sync_with_stdio(false);

	int e, s, m;
	cin >> e >> s >> m;

	cout << sol(e, s, m);
	return 0;
}